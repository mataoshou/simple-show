package base.db;

public class Rem_item

{
	private int id;
	private String name;
	private float score;
	private int type;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public float getScore(){
		return this.score;
	}
	
	public void setScore(float score){
		this.score = score;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setType(int type){
		this.type = type;
	}
}
