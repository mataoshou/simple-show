package sample.analyze;

public class ResultItem {
	String content;
	int[] status;  //存储字符所有的权重
	
	public ResultItem()
	{
		
	}
	public ResultItem(String content)
	{
		this.content = content;
		this.status = new int[content.length()];
	}
}
