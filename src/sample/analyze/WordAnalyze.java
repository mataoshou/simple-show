package sample.analyze;

import tool.CommonTool;

public class WordAnalyze implements IAnalyze {

	@Override
	public ResultItem analyzeTxt(String content, String tag) {
		ResultItem item = new ResultItem(content);
		String[] tags =CommonTool.getTools().StringUtils.distinct(tag.split(" |,|，"));
		
		
		for(int i=0;i<tags.length;i++)
		{
			int index = 0;
			while(true)
			{
				int begin = content.indexOf(tags[i],index);
				if(begin<0)break;
				for(int j=0;j<tags[i].length();j++)
				{
					item.status[begin+j] += 2;
				}
				index =begin + tags[i].length();
			}
		}
		
		return item;
	}
	
	public static void main(String[] args)
	{
		WordAnalyze analyze =new WordAnalyze();
		ResultItem item = analyze.analyzeTxt("aassqqwwewwew", "qq ww,ere,we,ss,ww");
		
		System.out.println(CommonTool.getTools().StringUtils.link(item.status, " , "));
	}

}
