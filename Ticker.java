

import java.util.ArrayList;


public class Ticker implements CharSequence{
	ArrayList<Character> characters;
	
	public Ticker(CharSequence chars) 
	{
		characters = new ArrayList<Character>();
		for(int i = 0; i<chars.length(); i++)
		{
			char c = chars.charAt(i);
			if(!isValid(c)) {
				throw new RuntimeException("invalid character");
			}
			else
			{
			c = validate(c);
			characters.add(Character.valueOf(c));
			}
			
		}
		if(characters.size()==0)
			throw new RuntimeException("ticker cannot be empty");
	}
	public static boolean validTicker(CharSequence chars)
	{
		if(chars.length()==0)
			return false;
		for(int i = 0; i<chars.length(); i++)
		{
			char c = chars.charAt(i);
			if(!isValid(c)) {
				return false;
			}
		}
		return true;
	}

	public Ticker(char[] chars)
	{
		characters = new ArrayList<Character>();
		for(int i=0;i<chars.length;i++)
		{
			char c = chars[i];
			if(!isValid(c)) {
				throw new RuntimeException("invalid character");
			}
			else
			{
			c = validate(c);
			characters.add(Character.valueOf(c));
			}
		}
	}
	
	private char validate(char c)
	{
		c = Character.toUpperCase(c);
		
		return c;
	}
	
	private static boolean isValid(char c)
	{
		if(Character.isWhitespace(c))
			return false;
		if(Character.isSpaceChar(c))
			return false;
		if(Character.getType(c) == Character.DECIMAL_DIGIT_NUMBER)
			return false;
		if(!Character.isLetter(c) && c!='-')
			return false;
		
		return true;	
	}
	
	public String toString()
	{
		String output = new String();
		
		for(int i = 0; i< characters.size(); i++){
			output = output + characters.get(i).toString();
		}
		return output;
	}
	

	@Override
	public int length() {

		return characters.size();
	}

	@Override
	public char charAt(int index) {

		return (characters.get(index)).charValue();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
			String string = toString();
			return string.subSequence(start, end);
	}
	

}
