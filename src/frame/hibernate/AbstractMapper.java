package frame.hibernate;

import java.util.List;

import tool.sql.SqlBuild;

public class AbstractMapper {
	//插入
	public AbstractMapper insert() throws Exception
	{
		DBUtil.save(this);
		return this;
	}
	
	public AbstractMapper update() throws Exception
	{
		DBUtil.update(this);
		return this;
	}
	
	public AbstractMapper get(Number id) throws Exception
	{
		String sql = String.format("FROM %s where id = %s",this.getClass().getName(),id);
		System.out.println(sql);
		return (AbstractMapper) DBUtil.get(sql, false);
	}
	
	public List<AbstractMapper> list(SqlBuild build) throws Exception
	{
		String sql = String.format("FROM %s ",this.getClass().getName(),build.toWhere());
		return DBUtil.list(sql, false);
	}
}
