package sample.database;

import java.util.ArrayList;
import java.util.List;

public class InsetData
{
	public static void main(String[] args) throws InterruptedException{
		
		InsertTask.setTables(InsertUtils.buildTable(50, "sweet_tree"));
		int count=0;
		InsertFinishTask.i().start();
//		List<InsertTask> list=new ArrayList();
		while(count<10000)
		{
			while(InsertTask.isFree())
			{
				System.out.println("有空闲表");
				String table = InsertTask.getTable();
				if(table.trim().length()>0){
					InsertTask insert= new InsertTask();
					insert.table = table;
					insert.setI(count++);
					insert.start();
					System.out.println("...............启动第"+count+"个线程...............");
					
				}
//				Thread.sleep(10);
			}
//			Thread.sleep(5);
//			System.out.println(count);
		}
//		for(int i=0;i<10000;i++)
//		{
//			for(int j=0;j<10000;j++)
//			{
//				SweetTree tree =new SweetTree();
//				tree.setParent(Shift.leftZeroShift(String.valueOf(i),5));
//				tree.setCode(Shift.leftZeroShift(String.valueOf(i),5)+Shift.leftZeroShift(String.valueOf(j),5));
//				tree.setName("tree"+i+j);
//				try
//				{
//					DBUtil.save(tree);
//				} catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//			System.out.println(".......第"+i+"组完成...........");
//		}
	}
}
