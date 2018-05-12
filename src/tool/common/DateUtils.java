package tool.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
	SimpleDateFormat format_normal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat format_path = new SimpleDateFormat("yyyy/MM/dd");
	
	public String dateToString(Date d)
	{
		String str = format_normal.format(d);
		return str;
	}

	public Date stringToDate(String str) throws ParseException
	{
		Date d = format_normal.parse(str);
		return d;
	}
	
	public String getTimePath()
	{
		String str = format_path.format(new Date());
		return str;
	}
	
	

}
