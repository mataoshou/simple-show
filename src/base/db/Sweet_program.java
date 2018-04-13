package base.db;

import java.sql.Timestamp;
public class Sweet_program

{
	private int id;
	private String programName;
	private int parent;
	private Timestamp timeCreate;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getProgramName(){
		return this.programName;
	}
	
	public void setProgramName(String programName){
		this.programName = programName;
	}
	
	public int getParent(){
		return this.parent;
	}
	
	public void setParent(int parent){
		this.parent = parent;
	}
	
	public Timestamp getTimeCreate(){
		return this.timeCreate;
	}
	
	public void setTimeCreate(Timestamp timeCreate){
		this.timeCreate = timeCreate;
	}
}
