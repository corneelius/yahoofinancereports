import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;




public class ReportLoader {

	public interface TagHandler
	{
		public void handleContent(String data, Tag[] innerTags, Tag[] enclosingTags); //return null if data is useless
		public boolean acceptsTag(Tag tag);
		public ReportLoader.Report getReport(); //returns null if no report was generated
	}
	public interface Report
	{
		public String getId();

	}

	boolean encounteredATag=false;
	int index;
	String[] singletonTagsRaw = {"area", "base", "br", "col", "command", "embed", "hr", "img", "input", "link", "meta", "param", "source"};
	ArrayList<TagHandler> tagHandlers;

	public Report[] getReports(String www, ArrayList<TagHandler> tagHandlers) throws IOException
	{
		this.tagHandlers=tagHandlers;
		Stack<Tag> enclosingTags = new Stack<Tag>();

		URL url = new URL(www);
		InputStream is = url.openStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		String line;

		while ((line = r.readLine()) != null)
		{
			builder.append(line);
		}
		String content=builder.toString();


		/////////////


		contentLoop : for(index=0; index<content.length(); index++ /*  make sure not to increment index too far in switch block */)
		{

			@SuppressWarnings("unused")
			char c = content.charAt(index);

			if(enclosingTags.isEmpty() && encounteredATag)
				break contentLoop;

			switch(content.charAt(index))
			{
			case '<':
			{
				if(handleTag(content, enclosingTags))
				{
					index++;
					if(processData(enclosingTags.peek(), content, enclosingTags)) 
						enclosingTags.pop();
					else
						index--;
				}
			}break;

			}
		}

		ArrayList<Report> reports = new ArrayList<Report>(tagHandlers.size());
		for(int i=0; i<tagHandlers.size(); i++)
		{
			Report report = tagHandlers.get(i).getReport();
			if(report!=null)
			{
				reports.add(report);
			}
		}
		return reports.toArray(new Report[reports.size()]);
	}
	private boolean processData(Tag tag, String content, Stack<Tag> enclosingTags)
	{

		TagHandler handler = getTagHandler(tag);
		if(handler==null)
		{
			return false;
		}	
		else
		{
			Stack<Tag> privateStack = new Stack<Tag>();
			privateStack.push(tag);
			while(true)
			{
				switch(content.charAt(index))
				{

				case '<':
				{
					if(handleTag(content, privateStack))
					{
						index++;
						int x64 = index; //index after >
						processData(privateStack.peek(), content, enclosingTags);
						index = x64;
					}
					else
					{
						index++;
					}

					if(privateStack.isEmpty())
						return true;
				}break;

				case ' ':
				{
					index++;
				}break;
				default:
				{
					int x2 = content.indexOf('<', index);
					handler.handleContent(content.substring(index, x2), stackToReversedArray(privateStack), stackToReversedArray(enclosingTags));
					index=x2;
				}

				}

			}
		}
	}

	private boolean handleTag(String content, Stack<Tag> stack)
	{
		Hashtable<String, String> attributes = new Hashtable<String, String>();
		index++;
		final int initialIndex=index;
		tagLoop : while(true)
		{
			char c=content.charAt(index);
			switch(c)
			{
			case '!':
			{
				skipToClosingBracket(content);
				return false;
			}

			case ' ':
			{
				//attributes, must be opening tag (taken care of in "case >: " ) 
				index++;
				int indexOfClosingBracket = content.indexOf('>', index);
				do
				{
					int x45 = content.indexOf('=', index);
					if(x45>indexOfClosingBracket) break;
					String key = content.substring(index, x45);
					index += key.length()+1;
					key = key.trim();

					int x23 = content.indexOf('\"', index);	
					if(x23>indexOfClosingBracket) break;
					String value = content.substring(x23+1, content.indexOf('\"', x23+1));
					index += value.length()+2;
					value = value.trim();
					attributes.put(key, value);
				}while(content.charAt(index) != '>');
				index=indexOfClosingBracket; //just in case there are spaces after attributes
			}break;

			case '/':
			{
				//just check if ending tag
				if(content.charAt(index-1) == '<')
				{
					stack.pop(); //should not throw EmptyStackException
					skipToClosingBracket(content);
					return false;
				}
			}//do not break, returned

			case '>':
			{
				//tag, already checked if closing tag, don't care if empty tag
				if(content.charAt(index-1) != '/' && content.charAt(initialIndex) != '/')
				{
					//could have attributes, so use extractTag()
					//be careful, it could lack closing tag (singleton)
					index=initialIndex;
					String tag = extractTag(content); //pushes index back to '>'
					if(!isSingletonTag(tag))
					{
						stack.push(new Tag(tag, attributes));
						encounteredATag=true;
						return true;
					}
					else
					{
						return false;
					}
				}
			}//do not break, returned

			default:
			{
				index++;
			}

			}
		}
	}

	private Tag[] stackToReversedArray(Stack<Tag> stack)
	{
		Tag[] array = new Tag[stack.size()];
		for(int i=stack.size()-1; i>=0; i--)
		{
			array[array.length-i-1] = stack.get(i).copy(); //clone
		}
		return array;
	}
	private void skipToClosingBracket(String content)
	{
		char c;
		do
		{
			index++;
			c = content.charAt(index);
		}while(c!='>');

		//index on >
	}
	private TagHandler getTagHandler(Tag tag)
	{
		for(TagHandler tg : tagHandlers)
		{
			if(tg.acceptsTag(tag))
				return tg;
		}
		return null;
	}
	private boolean isSingletonTag(String tag)
	{
		for(String string : singletonTagsRaw)
		{
			if(tag.equals(string))
				return true;
		}
		return false;
	}

	private String extractTag(String content)
	{
		//index must be positioned after first <
		char c;
		int indexOfFirstSpace=-1;
		int initialIndex = index;
		do
		{

			c=content.charAt(index);
			if(c==' ' && indexOfFirstSpace == -1)
			{
				indexOfFirstSpace=index;
			}
			index++;
		}while(c != '>');

		index--; //index will be incremented in contentLoop - on >
		if(indexOfFirstSpace == -1)
		{
			return content.substring(initialIndex, index);
		}
		else
		{
			return content.substring(initialIndex, indexOfFirstSpace);
		}

		//leaves index on '>'
	}

}
