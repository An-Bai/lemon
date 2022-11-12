package com.lemon.loginchat_interface;

import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;


public class Universal_B_chat_client {
	//固定聊天服务端端口号为3344
	public static int myport;
	
	public static void Client(String content, String number){		
		myport = getport(number);

		//创建发送端的码头，随机端口号(当然也可以自己添加端口号)
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//编写要发送的数据
		byte[] buf  = null;
		buf = content.getBytes();

		
		//创建发送端集装箱对象
		DatagramPacket packet = null;
		try {
			packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), 3344);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//将数据发送出去
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.close();
	
	
	}

	
	
	public static void keepReceive(String number) throws IOException {		
		myport = getport(number);
		
		//创建接收端的码头，指定端口号
		DatagramSocket socket = new DatagramSocket(myport);
		
		//创建接收端的集装箱对象
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
		

			while(true) {
				
				try {
					byte[] data = null;
					char[] c = null;
					String ber = null;
					int length = 0;
					//将数据从对方的集装箱装入自己的集装箱
					socket.receive(packet);	
					
					data = packet.getData();
					
					length = packet.getLength();
					
					ber = new String(data, 0, length);
					
					c =  ber.toCharArray();
					
					B_group_chat_layout_package.dataPrintf(c, number);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}

	
	}

	
	
	//获取用户的个人端口号
	public static int getport(String number) {
		int port = 0;
		
		//1.创建集合Properties对象
		Properties prop = new Properties();
				
		//2.从流中读取文件内容
		try {
			prop.load(new FileReader("Users information\\" + number + "\\编辑资料\\基本资料"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		port = Integer.valueOf(prop.getProperty("myport"));
		

		return port;
	}


}









