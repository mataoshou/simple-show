package sample.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tool.CommonTool;
import frame.hibernate.DBUtil;

public class CleanData {
	File root = null;// 文件根目录
	List<String> dbs = new ArrayList();// 数据库 列表
	List<String> tables = new ArrayList();// 数据库表列表
	Logger logger = Logger.getLogger(getClass());
	
	// 初始化
	private void init(File r,List db)
	{
		root = r;// 根目录
		dbs= db;
		
	}
	
	public void begin() throws Exception
	{
		CommonTool.getTools().BaseFileUtil.delete(root);
		for(String db : dbs)
		{
			tablesList(db);
			File f = new File(root,db);
			f.getParentFile().mkdirs();
			buildCleanSql(f,db);
			System.out.println("导出数据库："+db);
		}
	}
	
	public void tablesList(String db) throws Exception
	{
		// /获取所有的数据库表
		String sql = "SELECT table_name " + "FROM information_schema.tables "
				+ "WHERE  table_schema = '##1'".replace("##1", db);// 所在数据库

		tables = DBUtil.list(sql, true);

	}
	
	public void buildCleanSql(File f,String db)
	{
		try {
			FileOutputStream out = new FileOutputStream(f);
			String str = String.format("USE `%s`;\r\n", db);
			out.write(str.getBytes());
			for(String table :tables)
			{
				String sql = "DELETE FROM "+table +";\r\n";
				out.write(sql.getBytes());
				System.out.println(sql);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		List db = new ArrayList();
		db.add("shineon_vms");
		db.add("shineon_cc");
		db.add("shineon_censor");
		db.add("shineon_clue");
		db.add("shineon_cms");
		db.add("shineon_crawl");
		db.add("shineon_eqm");
		db.add("shineon_ims");
		db.add("shineon_media");
		db.add("shineon_ncs");
		db.add("shineon_news");
		db.add("shineon_topic");
		db.add("shineon_uauth");
		db.add("shineon_vms");
		CleanData clean  =new CleanData();
		clean.init(new File("c:/db"), db);
		clean.begin();
	}
}
