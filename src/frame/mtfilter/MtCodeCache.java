package frame.mtfilter;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;
//状态信息缓存
public class MtCodeCache {
	private Map<Integer,String> m_map = new HashMap();
	
	
	{
		m_map.put(0, "no reason");
	}
	
	private static MtCodeCache one = null;
	public static MtCodeCache i()
	{
		if(one == null)
		{
			one = new MtCodeCache();
		}
		return one;
	}
	
	public void put(int key,String value)
	{
		m_map.put(key, value);
	}
	
	public String getVal(int key)
	{
		String val = m_map.get(key);
		if(val == null) return String.valueOf(key);
		return val;
	}
	
	public void clean()
	{
		m_map.clear();
	}
}
