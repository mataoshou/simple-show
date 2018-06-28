package sample.analyze;

import java.util.ArrayList;
import java.util.List;

import tool.CommonTool;
//匹配特殊字符分割的字符串
public class WordAnalyze implements IAnalyze {

	@Override
	public ResultItem analyzeTxt(String content, String tag) {
		ResultItem item = new ResultItem(content);
		String[] tags =CommonTool.getTools().StringUtils.distinct(tag.split(" |,|，"));
		
		tags = extendTag(tags);
		
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
	
	public String[] extendTag(String[] tags)
	{
		List<String> list = new ArrayList();
		for(String s : tags)
		{
			int length = s.length();
			if(length > 2)
			{
				char[] cs = s.toCharArray();
				for(int i=0;i<length-1;i++)
				{
					list.add(String.copyValueOf(cs, i, 2));
				}
			}
			else{
				list.add(s);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static void main(String[] args)
	{
		WordAnalyze analyze =new WordAnalyze();
		ResultItem item = analyze.analyzeTxt("昨天去哪里了", "昨天区里还原 取个啊");
		
		System.out.println(CommonTool.getTools().StringUtils.link(item.status, " , "));
	}

}
