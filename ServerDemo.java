/*
��ʾ�ͻ��˺ͷ���ˣ�
1��
	�ͻ��ˣ������
	����ˣ��Զ���

2��
	�ͻ��ˣ������
	����ˣ�Tomcat��������

3��
	�ͻ��ˣ��Զ���
	����ˣ�Tomcat��������
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

		out.println("�ͻ������");

		s.close();
		ss.close();
	}
}
