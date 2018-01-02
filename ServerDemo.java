/*
演示客户端和服务端：
1，
	客户端：浏览器
	服务端：自定义

2，
	客户端：浏览器
	服务端：Tomcat服务器。

3，
	客户端：自定义
	服务端：Tomcat服务器。
*/

import java.net.*;
import java.io.*;
class ServerDemo 
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10001);

		Socket s = ss.accept();
		System.out.println(s.getInetAddress().getHostAddress());
	
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);

		out.println("客户端你好");

		s.close();
		ss.close();
	}
}
