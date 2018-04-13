package base.db;

public class Range

{
	private int id;
	private String RangeName;
	private int type;
	private String ids;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getRangeName(){
		return this.RangeName;
	}
	
	public void setRangeName(String RangeName){
		this.RangeName = RangeName;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public String getIds(){
		return this.ids;
	}
	
	public void setIds(String ids){
		this.ids = ids;
	}
}
