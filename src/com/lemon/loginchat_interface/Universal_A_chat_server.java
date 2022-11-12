package com.lemon.loginchat_interface;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Properties;


public class Universal_A_chat_server{
	final private static int[] sums = {1111, 6666, 9999, 1314, 1234};
	final private static String[] numbers = {"1111111111", "6666666666", "9999999999", "0123456789", "5211314521"};
	
	public static void Server() throws Exception {
	
	//创建接收端的码头，指定固定端口号——3344
	DatagramSocket socket = new DatagramSocket(3344);
	
	//创建接收端的集装箱对象
	DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
	

	
		while(true) {
			char[] c = null;
			byte[] data = null;
			String s = null;
			int length = 0;
			try {
				socket.receive(packet);

				data = packet.getData();
				length = packet.getLength();
					
				s = new String(data, 0, length);
				c = s.toCharArray();
			
					
				for(int i = 0; i < 5; i ++) {
				//创建发送端集装箱对象
				DatagramPacket packet1 = new DatagramPacket(data, length, InetAddress.getByName("127.0.0.1"), sums[i]);
			
				//将数据发送出去
				socket.send(packet1);
				}
	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			for(int i = 0; i < 5; i ++) {
				recordData(c, numbers[i]);
		
			}
			
		
		
		}
	
	}
	
	
	
	/**
	 * 实现功能区
	 * 
	 */


//记录聊天数据
public static void recordData(char[] data, String number) throws IOException {
	String a = null;
	String arr = null;
	
	a = String.valueOf(data, 10, data.length - 10);
	
	//获取昵称
	Properties prop = new Properties();											//创建集合Properties对象
	try {																		//从流中读取文件内容
		prop.load(new FileReader("Users information\\" + String.valueOf(data, 0, 10)  + "\\编辑资料\\基本资料"));
	} catch (IOException e) {
		e.printStackTrace();
	}	
	String call = prop.getProperty("昵称");
	
	Date day = new Date();	
	//获取到的字符串数据
	arr = call + " " + day.toLocaleString() + "\n" + a + "\n";
	
		
	String base = "";

		String path = "Users information\\" + number  + "\\聊天记录\\Chat datas";
		//创建一个字符流输入对象
		FileReader fr = new FileReader(path);			//FileReader和FileWriter要分开写才能读到文件里面的数据，无语……
		int j;
		while((j = fr.read()) != -1) {
			System.out.println("没有被执行！？");
			base += String.valueOf((char)j);
		}
		
		System.out.println(base + "没有！？");
		FileWriter fw = new FileWriter(path);
		
			fw.write(base + arr);
		
		fw.close();
		fr.close();

}

}
