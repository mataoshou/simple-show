package tool.sql;

public class Column
{
	private boolean primary;
	private boolean isNull;
	private String name;
	private String type;
	private String mark;
	private String java_type;
	private Integer length=0;
	
	public boolean isNull()
	{
		return isNull;
	}
	public void setNull(boolean isNull)
	{
		this.isNull = isNull;
	}
	public boolean isPrimary()
	{
		return primary;
	}
	public void setPrimary(boolean primary)
	{
		this.primary = primary;
	}
	public Integer getLength()
	{
		return length;
	}
	public void setLength(Integer length)
	{
		this.length = length;
	}
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
