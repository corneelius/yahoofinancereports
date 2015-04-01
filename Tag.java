import java.util.Hashtable;


public class Tag {
	private Hashtable<String, String> attributes;
	private String name;
	public Tag(String name, Hashtable<String, String> attributes)
	{
		this.name=name;
		
		if(attributes==null)
			this.attributes = new Hashtable<String, String>();
		else
			this.attributes=attributes;
	}
	public void addAttribute(String key, String value)
	{
		attributes.put(key, value);
	}
	public boolean containsAttribute(String key)
	{
		return attributes.contains(key);
	}
	public String getAttributeValue(String key)
	{
		return attributes.get(key);
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		return name;
	}
	public Tag copy()
	{
		return new Tag(name, attributes);
	}

}
