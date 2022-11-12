package com.lemon.personal_homepage_interface;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lemon.loginchat_interface.Universal_A_chat_server;


public class A_personal_homepage_entrance {
	private static Properties prop;

	public static void homepageDesign(String number) {
		
		//1.创建账户个人文件夹（模拟数据库储存用户信息）
		File directory = new File(".");						//此new对象用于获取当前路径
		String path = null;
		try {
			path = directory.getCanonicalPath();			//获取标准的路径——上一级文件夹路径；而getAbsolutePath()获取的是本级路径。
		} catch (IOException e) {							//getCanonicalPath()需要catch...try，而getAbsolutePath()不需要
			e.printStackTrace();
			
		}
		
		File fileNumber = new File(path + "\\Users information\\" + number);
		if(!fileNumber.exists()) {					//判断该fileNumber目录是否存在
			fileNumber.mkdir();						//若不存在则创建
		}
		
		
		
		//2.创建JFrame顶级窗体
		JFrame jf = new JFrame("Lemon2");
		jf.setBounds(1300, 120, 440, 640);		//分辨率1920 × 1080的中间位置
		jf.setResizable(false); 				//禁止修改窗体大小
		jf.setUndecorated(true);				//设置窗口边框不显示
		jf.setVisible(true);					//设置窗体可见
		
		jf.setLayout(new BorderLayout()); 		//设置Jframe窗体为流式布局

	
		
		//3.使用Jpanel面板布局
		//顶部
		JPanel topPanel = B_personal_homepage_layout_package.createTopPanel(jf, number);
		jf.add(topPanel, BorderLayout.PAGE_START);
			
		//中间
		JPanel CenterPanel = B_personal_homepage_layout_package.createCenterPanel(jf, number);
		jf.add(CenterPanel, BorderLayout.CENTER);	
		
		
		
		//这里判断一下服务器是否开启，决定是否需要创建对象（if……）
		if(getState() == false) {
			System.out.println("聊天服务器开启");
			//开启聊天功能的服务端
			new Thread(() -> {
				try {
					Universal_A_chat_server.Server();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
			
			prop.setProperty("服务端开启状态", "true");
			//同步信息
			try {
				prop.store(new FileWriter("Files\\IdAndPassword"), "password");
			} catch (IOException e) {
				e.printStackTrace();
			}		//第二个参数是一个描述信息
					
		}

				
				
				
	}

	
	
	
	
	
	
	private static boolean getState() {
		//1.创建集合Properties对象
		prop = new Properties();
		
		//2.从流中读取文件内容
		try {
			prop.load(new FileReader("Files\\IdAndPassword"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean flag = prop.getProperty("服务端开启状态").equals("true");
		
		
		return flag;
	}
	
}














