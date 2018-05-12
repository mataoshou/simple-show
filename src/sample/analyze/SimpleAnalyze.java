package sample.analyze;

import tool.CommonTool;
//匹配的权重是  1  不处理连续的情况
public class SimpleAnalyze implements IAnalyze {

	@Override
	public ResultItem analyzeTxt(String content, String tag) {
		ResultItem item =new ResultItem(content);
		
		char[] ts= tag.toCharArray();
		char[] ds = content.toCharArray();
		for(int i=0;i<ds.length;i++)
		{
			for(char c: ts)
			{
				if(ds[i] == c)
				{
					item.status[i] =1;
					break;
				}
			}
		}
		
		return item;
	}
	
	
	public static void main(String[] args)
	{
		 IAnalyze an = new SimpleAnalyze();
		 ResultItem item =  an.analyzeTxt("昨天夜里去哪里", "昨天  哪里");
		 
		 System.out.println(CommonTool.getTools().StringUtils.link(item.status, " , "));
		 
	}

}
