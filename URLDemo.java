import java.net.*;
class URLDemo
{
	public static void main(String[] args) throws MalformedURLException
	{
		URL url = new URL("http://wangyiyun.com");

		sop("getProtocol():"+url.getProtocol);
		sop("getHost():"+url.getHost());
		sop("getPost():"+url.getPort());
		sop("getPath():"+url.getPath());
		sop("getFile():"+url.getFile());
		sop("getQuery():"+url.getQuery());
	}
	
	public static void sop(Object obj)
	{
		System.out.println(obj);
	}
/*
String getFile()
		��ȡ��URL���ļ�����
String getHost()
		��ȡ��URL����������(�������)
String getPath()
		��ȡ��URL��·�����֡�
String getPort()
		��ȡ��URL�Ķ˿ںš�
String getProtocol()
		��ȡ��URL��Э�����ơ�
String getQuery()
		��ȡ��URL�Ĳ�ѯ��
*/
}
