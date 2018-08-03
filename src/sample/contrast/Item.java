package sample.contrast;

import tool.CommonTool;
import tool.common.ArrayUtils;

//文本单位对比
public class Item {
	private String left;

	private String right;
	private int[][][] status;

	public int[] l_path;
	public int[] r_path;
	
	public Item(String l, String r) {
		this.left = l;
		this.right = r;
		status = new int[r.length()][l.length()][2];
	}
	//重新初始化
	public void init(String l, String r) {
		this.left = l;
		this.right = r;
		status = new int[r.length()][l.length()][2];
	}
	//文本对比
	public void compare() {
		char[] l_cs = left.toCharArray();
		char[] r_cs = right.toCharArray();

		int length = r_cs.length;

		// while(count <= length)
		// {
		for (int i = 0; i < length; i++) {
			int count = 0;
			char c = r_cs[i];
			while (true) {
				int index = left.indexOf(c, count);
				if (index < 0)
					break;
				status[i][index][0] = 1;
				if (index > 0 && i > 0) {
					if (status[i - 1][index - 1][0] > 0)
						status[i][index][0] = status[i - 1][index - 1][0] + 1;
				}
				count = index + 1;
			}
		}
	}
	//显示数组集
	public void show() {
		for (int i = 0; i < status.length; i++) {
			int[][] line = status[i];
			for(int j=0;j<line.length;j++)
			{
				System.out.print(line[j][0] +",");
			}
			System.out.println();
//			System.out.println(CommonTool.getTools().StringUtils.link(
//					status[i], ","));
		}
		System.out.println("---------------左侧的位置------------");
		System.out.println(CommonTool.getTools().StringUtils.link(l_path, ","));
		System.out.println("---------------右侧的位置------------");
		System.out.println(CommonTool.getTools().StringUtils.link(r_path, ","));
	}

	// 计算重合度
	public void builpath() {
		setScore();
		int length = status.length;
		l_path = new int[left.length()];
		r_path = new int[right.length()];
		for (int i = length - 1; i >= 0; i--) {
			int no = getMax(status[i]);

			if (status[i][no][0] > 0) {
				l_path[no] = i + 1;
				r_path[i] = no + 1;
			}
		}

		dataWash();
	}
	
	public void setScore()
	{
		for(int i=0;i<status.length;i++)
		{
			int[][] line = status[i];
			for(int j=0;j<line.length;j++)
			{
				if(line[j][0]!=0)
				{
					int length = getLength(line,j) - j+1;
					if(length>1)
					{
						line[j][1] = length *2;
					}
					else{
						line[j][0] = 0;
					}
				}
			}
		}
	}
	
	public int getLength(int[][] line,int index)
	{
		int count=index;
		int val =line[index][0];
		for(int i=index+1;i<line.length;i++)
		{
			if(line[i][0] == (val+1))
			{
				val = line[i][0];
				count =i;
				continue;
			}
			break;
		}
		return count;
	}
	
	public int getMax(int[][] status)
	{
		int max = 0;
		int no = 0;
		for(int i =0;i<status.length;i++)
		{
			if(status[i][1]>max) max =status[i][1];
			no = i;
		}
		return no;
	}
	//清洗异常数据
	public void dataWash() {
		
//		System.out.println("---------------左侧的位置  清洗前------------");
//		System.out.println(CommonTool.getTools().StringUtils.link(l_path, ","));
		
		for (int i = 0; i < l_path.length; i++) {
			if (l_path[i] >0) 
			{
				int index =getLast(l_path,i);
				if(index>i)
				{
					i=index;
					continue;
				}
				else{
					r_path[l_path[i]-1] = 0;
					l_path[i]=0;
				}
				
				
			}
		}
		
		
//		System.out.println("---------------右侧的位置  清洗后------------");
//		System.out.println(CommonTool.getTools().StringUtils.link(r_path, ","));
	}
	
	public int getLast(int[] arr,int index)
	{
		int val = arr[index];
		for(int i=index+1;i<arr.length;i++)
		{
			
			if(arr[i]==0||(val+1)!=arr[i])
			{
				i--;
				return i;
			}
			val = arr[i];
			
		}
		return arr.length-1;
	}

	public static void main(String[] args) {
		Item item = new Item("为什么索斯盖特会任命凯恩作为英格兰队长？在结束和哥伦比亚的比赛后，这个最初的疑问将不复存在", 
				"当英格兰通过点球大战，以总分5比4击败哥伦比亚晋级八强后。国际足联官方宣布，凯恩当选本场比赛最佳球员，这也是他本届世界杯第三次当选单场比赛最佳球员。");
		item.compare();

		item.builpath();
		item.show();
	}
}
