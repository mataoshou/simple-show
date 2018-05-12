package tool.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileStore
{
	//获取文章内容
	public static String getContent(File f,String charset) throws IOException
	{
		if(f.exists())
		{
			FileInputStream input=new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int n=0;
			byte[] b=new byte[1024*10];
			while(true)
			{
				n=input.read(b);
				if(n<0)break;
				out.write(b, 0, n);
			}
			return out.toString(charset);
		}
		return null;
	}
	
	public static void putString(File f,String str,String charset)
	{
		if(f.exists())
		{
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(f);
				out.write(str.getBytes(charset));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
