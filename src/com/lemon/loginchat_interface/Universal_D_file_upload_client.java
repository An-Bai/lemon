package com.lemon.loginchat_interface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Universal_D_file_upload_client {
	final static int fileport = 3344;
	
	//文件上传
	public static void upLoad(ServerSocket server) throws IOException{
		Socket socket = new Socket("127.0.0.1", fileport);
		
		//1.获取用于写出数据到服务端的流对象
		OutputStream os = socket.getOutputStream();
		
		//2.手动创建字节输入流，关联本地的文件
		String ip = "D:\\Java\\text\\drift_bottle.jpg";
		FileInputStream fis = new FileInputStream(ip);
		
		//3.数据读写
		byte[] bys = new byte[1024];
		int len;
		
		while((len = fis.read(bys)) != -1) {
			os.write(bys, 0, len);
		}
		fis.close();
		
		//通过Socket的shutdownOutput()方法切断输出流，相对应的服务端才会认为数据以及发送完毕了，服务端（不是客户端）的此while才会结束
		socket.shutdownOutput();
		
		//4.读取服务端回写的状态
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		System.out.println(br.readLine());
		
		socket.close();
	}
	
	
	//文件接收
	public static void downLoad(ServerSocket server) throws IOException{
		Socket socket = new Socket("127.0.0.1", fileport);
		
		//1.获取用于写出数据到服务端的流对象
		OutputStream os = socket.getOutputStream();
		
		//2.手动创建字节输入流，关联本地的文件
		String ip = "D:\\Java\\text\\drift_bottle.jpg";
		FileInputStream fis = new FileInputStream(ip);
		
		//3.数据读写
		byte[] bys = new byte[1024];
		int len;
		
		while((len = fis.read(bys)) != -1) {
			os.write(bys, 0, len);
		}
		fis.close();
		
		//通过Socket的shutdownOutput()方法切断输出流，相对应的服务端才会认为数据以及发送完毕了，服务端（不是客户端）的此while才会结束
		socket.shutdownOutput();
		
		//4.读取服务端回写的状态
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		System.out.println(br.readLine());
		
		socket.close();
	}
		
	
}
