package sample.analyze;

import java.io.File;
import java.util.HashMap;

import tool.CommonTool;

public class SimpleChineseAnalyze {
	private File root;//索引存储地址
	
	public SimpleChineseAnalyze(File root)
	{
		this.root = root;
	}
	public SimpleChineseAnalyze(String path)
	{
		File f = new File(path);
		this.root = f;
	}
	
	public void initAnalyze()
	{
		//创建文件
		String  fileName = CommonTool.getTools().DateUtils.getTimePath();
		CommonTool.getTools().Convert.keytoList(new HashMap());
		System.out.println(fileName);
		File f = new File(root , fileName);
		if(!f.exists())
		{
			f.mkdirs();
		}
		//生成结构
		
		//
		
	}
	
	public static void main(String[] args)
	{
		SimpleChineseAnalyze analyze = new SimpleChineseAnalyze("c:/");
		analyze.initAnalyze();
	}
	
	public void addIndex()
	{
		
	}
	
	public void hitContent()
	{
		
	}
	
	
	class Index
	{
		String key;
		String content;
		String showIndex;
	}
}
