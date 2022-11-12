package com.lemon.loginchat_interface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Universal_C_file_upload_server{
	static int count = 1;

	//文件上传
	public static void fileUpload(ServerSocket server) throws IOException{
		System.out.println("文件上传服务端开启");
		while(true) {
			//1.响应请求
			Socket socket = server.accept();
			new Thread() {
				public void run() {
					try {
						//2.判断储存文件的文件夹是否存在
						File dir = new File("D:\\Lemon");
						
						//3.如果不存在，则创建
						if(!dir.exists()) {
							dir.mkdir();
						}
						
						//4.获取IP地址，准备拼接文件名称
						String ip = socket.getInetAddress().getHostAddress();
						File files = new File(dir, ip + "(" + count + ")" + ".jpg");
						
						//5。判断文件是否存在
						if(files.exists()) {
							count ++;
							files = new File(dir, ip + "(" + count + ")" + ".jpg");
						}
						
						//6.获取输入流，读取客户算发送过来的字节
						InputStream is = socket.getInputStream();
						
						FileOutputStream fos = new FileOutputStream(files);
						
						byte[] bys = new byte[1024];
						int len;
						
						while((len = is.read(bys)) != -1) {
							fos.write(bys, 0, len);
						}
						
						fos.close();
						
						//7.回写客户端数据，上传成功
						PrintStream ps = new PrintStream(socket.getOutputStream());
						ps.println(count);
						
						socket.close();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		}
	}
	
	
	//文件下载
	public static void fileDownload(ServerSocket server, int count) throws IOException{
		while(true) {
			//1.响应请求
			Socket socket = server.accept();
			new Thread() {
				public void run() {
					try {
						File dir = new File("D:\\Lemon");

						//2.获取要下载的IP地址
						String ip = socket.getInetAddress().getHostAddress();
						File files = new File(dir, ip + "(" + count + ")" + ".jpg");
						
						//3.判断文件是否存在
						if(!files.exists()) {
							//4.回写客户端数据，上传成功
							PrintStream ps = new PrintStream(socket.getOutputStream());
							ps.println("该文件夹已失效");
							
							socket.close();
						}else {						
							//5.获取输入流，读取已上传的文件字节
							InputStream is = socket.getInputStream();
							
							FileOutputStream fos = new FileOutputStream(files);
							
							byte[] bys = new byte[1024];
							int len;
							
							while((len = is.read(bys)) != -1) {
								fos.write(bys, 0, len);
							}
							
							fos.close();
							
							//6.回写客户端数据，上传成功
							PrintStream ps = new PrintStream(socket.getOutputStream());
							ps.println("上传成功！");							
							socket.close();
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		}
	}
	
	
}









