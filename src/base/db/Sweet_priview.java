package base.db;

public class Sweet_priview

{
	private int itemId;
	private float p_max;
	private float p_min;
	private boolean safe;
	private boolean iscontinue;
	private float begin;
	private float end;
	
	public int getItemId(){
		return this.itemId;
	}
	
	public void setItemId(int itemId){
		this.itemId = itemId;
	}
	
	public float getP_max(){
		return this.p_max;
	}
	
	public void setP_max(float p_max){
		this.p_max = p_max;
	}
	
	public float getP_min(){
		return this.p_min;
	}
	
	public void setP_min(float p_min){
		this.p_min = p_min;
	}
	
	public boolean getSafe(){
		return this.safe;
	}
	
	public void setSafe(boolean safe){
		this.safe = safe;
	}
	
	public boolean getIscontinue(){
		return this.iscontinue;
	}
	
	public void setIscontinue(boolean iscontinue){
		this.iscontinue = iscontinue;
	}
	
	public float getBegin(){
		return this.begin;
	}
	
	public void setBegin(float begin){
		this.begin = begin;
	}
	
	public float getEnd(){
		return this.end;
	}
	
	public void setEnd(float end){
		this.end = end;
	}
}
