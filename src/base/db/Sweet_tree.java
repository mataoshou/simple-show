package base.db;

public class Sweet_tree

{
	private int id;
	private String code;
	private String parent;
	private String name;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getParent(){
		return this.parent;
	}
	
	public void setParent(String parent){
		this.parent = parent;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
