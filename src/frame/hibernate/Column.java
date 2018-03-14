package frame.hibernate;

public class Column
{
	private String name;
	private String type;
	private String mark;
	
	private String java_type;
	public String getJava_type()
	{
		return java_type;
	}
	public void setJava_type(String java_type)
	{
		this.java_type = java_type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
}
