package frame.hibernate;

import java.util.List;

import tool.sql.SqlBuild;

public class ModuleUtils<T>
{
	private String table_name =null;
	private String class_name = null;
	
	public ModuleUtils()
	{
		
	};
	public ModuleUtils(String class_name,String table_name)
	{
		this.table_name = table_name;
		this.class_name = class_name;
	}
	public void setMapperClass(String class_name,String table_name)
	{
		this.table_name = table_name;
		this.class_name = class_name;
	}
	/**
	 *检查是否设置关联表的mapper对象
	 */
	public void classCheck() throws Exception
	{
		if(class_name == null) throw new Exception("未初始化关联表的class名称");
	}
	
	/**
	 *检查是否设置查询表的名称
	 */
	public void tableCheck() throws Exception
	{
		if(table_name == null) throw new Exception("未初始化查询的表名称");
	}
	/**
	 *根据id获取单个对象
	 */
	public T get(Number id) throws Exception{
		classCheck();
		String get_sql = String.format("FROM %s where id = %s", class_name, id);
		return (T) DBUtil.get(get_sql, false);
	}
	/**
	 *获取前N条数据 
	 */
	public List<T> listN(SqlBuild build ,int n) throws Exception{
		classCheck();
		String where = build.toWhere();
		if(where.length()<0) throw new Exception("未添加查询条件");
		String list_sql = String.format("FROM %s %s", class_name,where);
		return DBUtil.listN(list_sql, false, n);
	}
	
	/**
	 *获取所有符合条件的数据
	 */
	public List<T> list(SqlBuild build) throws Exception{
		classCheck();
		String where = build.toWhere();
		if(where.length()<=0) throw new Exception("未添加查询条件");
		String list_sql = String.format("FROM %s %s", class_name,where);
		return DBUtil.list(list_sql, false);
	}
	/**
	 *获取所有符合条件的数据 id
	 */
	public List getIds(SqlBuild build) throws Exception
	{
		tableCheck();
		String where = build.toWhere();
		if(where.length()<=0)throw new Exception("未添加查询条件");
		String sql_ids = String.format("SELECT id FROM %s %s", table_name,where);
		return DBUtil.list(sql_ids, false);
	}
	/**
	 *更新对象 
	 */
	public T update(T t) throws Exception
	{
		DBUtil.update(t);
		return t;
	}
	/**
	 *保存对象 
	 */
	public T save(T t) throws Exception
	{
		DBUtil.save(t);
		return t;
	}
	/**
	 *删除 
	 */
	public void delete(T t) throws Exception
	{
		DBUtil.delete(t);
	}
	/**
	 *执行sql语句 
	 */
	public int execute(String sql) throws Exception
	{
		return DBUtil.execute(sql, true);
	}
	
	
	
	public static void main(String[] args)
	{
		ModuleUtils<String> bu = new ModuleUtils();
		try
		{
			bu.get(1);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
