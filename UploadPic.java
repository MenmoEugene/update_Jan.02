
/*
客户端：
1，服务端点。
2，读取客户端已有的图片数据。
3，通过socket输出流将数据发给服务端。
4，读取服务端反馈信息。
5，关闭。
*/
import java.io.*;
import java.net.*;
class PicClient
{
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("192.168.1.108",10046);

		FileInputStream fis = new FileInputStream("E:\\Ziliao\\Java\\practice\\1.jpg");

		OutputStream out = s.getOutputStream();

		byte[] buf = new byte[1024];

		int len = 0;

		while((len=fis.read(buf))!=-1)
		{
			out.write(buf,0,len);
		}
		//告诉服务端数据已写完。
		s.shutdownOutput();

		InputStream in = s.getInputStream();
		byte[] bufIn = new byte[1024];

		int num = in.read(bufIn);
		System.out.println(new String(bufIn,0,num));

		fis.close();
		s.close();
	}
}

/*
服务端

这个服务端有个局限性。当A客户端连接上以后，被服务端获取到。服务端执行静态流程。
这时B客户端连接，只有等待。
因为服务端还没有处理完A客户端的请求，还没有循环回来执行下次accept方法。所以，
暂时获取不到B客户端对象。

那么为了可以让多个客户端同时并发访问服务端。
所以服务端最后就是将每个客户端封装到一个单独的线程中，这样，就可以同时处理多个客户端请求。

如何定义线程呢？

只要明确了每一个客户端要在服务端执行的代码即可。将该代码存入到run方法中。
*/


class PicServer
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10046);

		Socket s = ss.accept();

		InputStream in = s.getInputStream();

		FileOutputStream fos = new FileOutputStream("server.bmp");

		byte[] buf = new byte[1024];

		int len = 0;
		while((len=in.read(buf))!=-1)
		{
			fos.write(buf,0,len);
		}

		OutputStream out = s.getOutputStream();

		out.write("上传成功".getBytes());

		fos.close();

		s.close();

		ss.close();
	}
}