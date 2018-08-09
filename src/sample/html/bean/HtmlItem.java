package sample.html.bean;

public class HtmlItem {
	public int score = 0;
	public String content ="";
	public int length = 0;
	
	public int finalScore = 0;
	
	
	public boolean unDelete =false;
	
	public HtmlItem(String content)
	{
		this.content = content;
		this.length = content.length();
		this.score = 0;
		this.finalScore = 0;
	}
}
