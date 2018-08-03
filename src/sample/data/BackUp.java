package sample.data;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BackUp {
	public void back()
	{
        try {
            Runtime rt = Runtime.getRuntime();
 
            String cmd ="D:\\shineon\\mysql\\mysql5\\bin\\mysqldump -h localhost -u root -pshineon@1234 shineon_vms >d://2.txt";
            // 调用 调用mysql的安装目录的命令
            Process child = rt.exec(cmd);
            System.out.println(cmd);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
 
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            System.out.println(outStr);
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream("c:\\test.sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
 
            System.out.println("");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	public static void main(String[] args)
	{
//		BackUp back = new BackUp();
//		back.back();
		String str ="[HD]20180705​张鉴武到万载调研精准扶贫工作.mp4";
		System.out.println(str);
	}
}
