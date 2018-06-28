package frame.bean;

public class SqlItem {
	private String column;
	
	private Object value;
	
	private String type;
	
	
	public SqlItem(String column,Object value)
	{
		this.column = column;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}
}
