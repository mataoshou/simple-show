package base.db;

import java.sql.Timestamp;
public class Sweet_item

{
	private int id;
	private String name;
	private String code;
	private float max;
	private Timestamp maxTime;
	private float min;
	private Timestamp minTime;
	private float safe;
	private boolean flagDelete;
	private Timestamp timeDelete;
	
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
	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public float getMax(){
		return this.max;
	}
	
	public void setMax(float max){
		this.max = max;
	}
	
	public Timestamp getMaxTime(){
		return this.maxTime;
	}
	
	public void setMaxTime(Timestamp maxTime){
		this.maxTime = maxTime;
	}
	
	public float getMin(){
		return this.min;
	}
	
	public void setMin(float min){
		this.min = min;
	}
	
	public Timestamp getMinTime(){
		return this.minTime;
	}
	
	public void setMinTime(Timestamp minTime){
		this.minTime = minTime;
	}
	
	public float getSafe(){
		return this.safe;
	}
	
	public void setSafe(float safe){
		this.safe = safe;
	}
	
	public boolean getFlagDelete(){
		return this.flagDelete;
	}
	
	public void setFlagDelete(boolean flagDelete){
		this.flagDelete = flagDelete;
	}
	
	public Timestamp getTimeDelete(){
		return this.timeDelete;
	}
	
	public void setTimeDelete(Timestamp timeDelete){
		this.timeDelete = timeDelete;
	}
}
