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
		获取此URL的文件名。
String getHost()
		获取此URL的主机名。(如果适用)
String getPath()
		获取此URL的路径部分。
String getPort()
		获取此URL的端口号。
String getProtocol()
		获取此URL的协议名称。
String getQuery()
		获取此URL的查询部
*/
}
