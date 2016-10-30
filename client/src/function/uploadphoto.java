package function;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.widget.Toast;

public class uploadphoto {

	public uploadphoto() {
		// TODO Auto-generated constructor stub
	}
	 public boolean uploadFile(String urlx,String fileName,String uploadFile)
	    {
	        String end = "\r\n";
	        String twoHyphens = "--";
	        String boundary = "*****";
	        try
	        {
	            URL url =new URL(urlx);
	            HttpURLConnection con = (HttpURLConnection) url.openConnection();
	          /* Output to the connection. Default is false,
	             set to true because post method must write something to the connection */
	            con.setDoOutput(true);
	          /* Read from the connection. Default is true.*/
	            con.setDoInput(true);
	          /* Post cannot use caches */
	            con.setUseCaches(false);
	          /* Set the post method. Default is GET*/
	            con.setRequestMethod("POST");
	          /* 设置请求属性 */
	            con.setRequestProperty("Connection", "Keep-Alive");
	            con.setRequestProperty("Charset", "UTF-8");
	            con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	          /*设置StrictMode 否则HTTPURLConnection连接失败，因为这是在主进程中进行网络连接*/
	            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
	          /* 设置DataOutputStream，getOutputStream中默认调用connect()*/
	            DataOutputStream ds = new DataOutputStream(con.getOutputStream());  //output to the connection
	            ds.writeBytes(twoHyphens + boundary + end);
	            ds.writeBytes("Content-Disposition: form-data; " +
	                    "name=\"file\";filename=\"" +
	                    fileName + "\"" + end);
	            ds.writeBytes(end);
	          /* 取得文件的FileInputStream */
	            
	            FileInputStream fStream = new FileInputStream( new File(Environment.getExternalStorageDirectory(),"/myHead/head.jpg"));
	            
	        
	          /* 设置每次写入8192bytes */
	            int bufferSize =fStream.available();
	            byte[] buffer = new byte[bufferSize];   //8k
	            int length = -1;
	          /* 从文件读取数据至缓冲区 */
	            if (fStream != null) {
	            while ((length = fStream.read(buffer)) != -1)
	            {
	            /* 将资料写入DataOutputStream中 */
	                ds.write(buffer, 0, length);
	            }
	            }
	            ds.writeBytes(end);
	            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
	          /* 关闭流，写入的东西自动生成Http正文*/
	            fStream.close();
	          /* 关闭DataOutputStream */
	            ds.close();
	          /* 从返回的输入流读取响应信息 */
	            InputStream is = con.getInputStream();  //input from the connection 正式建立HTTP连接
	            int ch;
	            StringBuffer b = new StringBuffer();
	            while ((ch = is.read()) != -1)
	            {
	                b.append((char) ch);
	            }
	          /* 显示网页响应内容 */
	           return true;//Post成功
	        } catch (Exception e
)
	        {
	            /* 显示异常信息 */
	           return false;//Post失败
	        }
	    }

}

