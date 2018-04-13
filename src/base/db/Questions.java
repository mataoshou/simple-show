package base.db;

public class Questions

{
	private int id;
	private String qcontent;
	private int qtype;
	private String qlevel;
	private String qrange;
	private String qoptions;
	private String qanswer;
	private String qmark;
	private int qansNum;
	private int qerrorNum;
	private String qsource;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getQcontent(){
		return this.qcontent;
	}
	
	public void setQcontent(String qcontent){
		this.qcontent = qcontent;
	}
	
	public int getQtype(){
		return this.qtype;
	}
	
	public void setQtype(int qtype){
		this.qtype = qtype;
	}
	
	public String getQlevel(){
		return this.qlevel;
	}
	
	public void setQlevel(String qlevel){
		this.qlevel = qlevel;
	}
	
	public String getQrange(){
		return this.qrange;
	}
	
	public void setQrange(String qrange){
		this.qrange = qrange;
	}
	
	public String getQoptions(){
		return this.qoptions;
	}
	
	public void setQoptions(String qoptions){
		this.qoptions = qoptions;
	}
	
	public String getQanswer(){
		return this.qanswer;
	}
	
	public void setQanswer(String qanswer){
		this.qanswer = qanswer;
	}
	
	public String getQmark(){
		return this.qmark;
	}
	
	public void setQmark(String qmark){
		this.qmark = qmark;
	}
	
	public int getQansNum(){
		return this.qansNum;
	}
	
	public void setQansNum(int qansNum){
		this.qansNum = qansNum;
	}
	
	public int getQerrorNum(){
		return this.qerrorNum;
	}
	
	public void setQerrorNum(int qerrorNum){
		this.qerrorNum = qerrorNum;
	}
	
	public String getQsource(){
		return this.qsource;
	}
	
	public void setQsource(String qsource){
		this.qsource = qsource;
	}
}
