
/*
�ͻ��ˣ�
1������˵㡣
2����ȡ�ͻ������е�ͼƬ���ݡ�
3��ͨ��socket����������ݷ�������ˡ�
4����ȡ����˷�����Ϣ��
5���رա�
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
		//���߷����������д�ꡣ
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
�����

���������и������ԡ���A�ͻ����������Ժ󣬱�����˻�ȡ���������ִ�о�̬���̡�
��ʱB�ͻ������ӣ�ֻ�еȴ���
��Ϊ����˻�û�д�����A�ͻ��˵����󣬻�û��ѭ������ִ���´�accept���������ԣ�
��ʱ��ȡ����B�ͻ��˶���

��ôΪ�˿����ö���ͻ���ͬʱ�������ʷ���ˡ�
���Է���������ǽ�ÿ���ͻ��˷�װ��һ���������߳��У��������Ϳ���ͬʱ�������ͻ�������

��ζ����߳��أ�

ֻҪ��ȷ��ÿһ���ͻ���Ҫ�ڷ����ִ�еĴ��뼴�ɡ����ô�����뵽run�����С�
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

		out.write("�ϴ��ɹ�".getBytes());

		fos.close();

		s.close();

		ss.close();
	}
}