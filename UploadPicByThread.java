
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
		if(args.length!=1)
		{
			System.out.println("��ѡ��һ��jpg��ʽͼƬ");
			return ;
		}

		File file = new File(args[0]);
		if(!(file.exists() && file.isFile()))
		{
			System.out.println("���ļ������⣬Ҫô�����ڣ�Ҫô�����ļ�");
			return ;
		}
		if(!file.getName().endsWith(".jpg"))
		{
			System.out.println("ͼƬ��ʽ��������ѡ��");
			return ;
		}
		if(file.length()>1024*1024*5)
		{
			System.out.println("�ļ�����û������");
			return ;
		}


		Socket s = new Socket("192.168.1.108",10009);

		FileInputStream fis = new FileInputStream(file);

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

class PicThread implements Runnable
{
	private Socket s;
	PicThread(Socket s)
	{
		this.s =s;
	}
	public void run()
	{
		String ip = s.getInetAddress().getHostAddress();
		try
		{
			int count =0;
			System.out.println(ip+"...connected");
			InputStream in = s.getInputStream();

			File file = new File(ip+"("+(count++)+")"+".jpg");//192.168.1.254(1).jpg

			while(file.exists())
				file = new File(ip+"("+(count++)+")"+".jpg");




			FileOutputStream fos = new FileOutputStream(file);

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
		}
			catch (Exception e)
			{
				throw new RuntimeException(ip+"�ϴ�ʧ��");
			}
		
	}
}

class PicServer
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10009);

		while(true)
		{
		Socket s = ss.accept();

		new Thread(new PicThread(s)).start();

		}

//		ss.close();
	}
}