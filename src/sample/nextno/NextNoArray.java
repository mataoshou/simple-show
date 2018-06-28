package sample.nextno;

import java.util.Random;

import tool.CommonTool;

public class NextNoArray {
	
	double[][] array;
	double[] result;
	int height = 0;
	int width = 0;
	
	public void input(double[][] array)
	{
		this.array = array;
		height = array.length;
		width = array[0].length;
	}
	
	public void analyze()
	{
		result = new double[width];
		
		for(int i=0;i<width;i++)
		{
			double[] tmp =new double[height];
			for(int j=0;j<height;j++)
			{
				tmp[j] = array[j][i];
			}
			result[i] = getAverage(tmp,height);
		}
		
		System.out.println("纵行数组平均值："+ CommonTool.getTools().StringUtils.link(result, ","));
		
	}
	
	public double getNext(int no ,int ave)
	{
		boolean sign = true;
		int low = 0;
		int high = 0;
		for(int i=0;i<width;i++)
		{
			double val = array[i][no];
			if(val>ave) high++;
			if(val<ave) low++;
		}
		if(low > high) sign = false;
		
		
		return 0;
	}
	
	public double getAverage(double[] is,int end)
	{
		double result = 0;
		for(int i=0;i<end;i++)
		{
			result += is[i];
		}
		return result/end;
	}
	
	public void getCenter()
	{
//		RuntimeException
	}
	
	
	public static void main(String[] args)
	{
		Random r = new Random();
		
		double[][] array = new double[10][10];
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				array[i][j] = r.nextInt(100);
			}
		}
		
		NextNoArray noa = new NextNoArray();
		noa.input(array);
		noa.analyze();
	}
}
