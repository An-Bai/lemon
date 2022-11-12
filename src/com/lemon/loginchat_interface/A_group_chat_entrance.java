package com.lemon.loginchat_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class A_group_chat_entrance {
	
	//固定聊天服务端端口号为3344
	final static int serveport = 3344;

	
	//测试时，定义所需要的传入的数据
	private static Properties prop;				//数据库信息（模拟）


	public static void multiplayerChat(String number) {
		
		//1.创建JFrame顶级窗体
		JFrame jf = new JFrame("多人聊天");
		jf.setBounds(455, 185, 1010, 710);		//分辨率1920 × 1080的中间位置
		jf.setResizable(false); 				//禁止修改窗体大小
		jf.setUndecorated(true);				//设置窗口边框不显示
		jf.setVisible(true);					//设置窗体可见
		
		jf.setLayout(new BorderLayout()); 		//设置Jframe窗体为流式布局
		
		
		
		//2.1加入一个背景JPanel面板组件作为外部边框
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(1010, 710));			//设置面板尺寸
		//模拟设置阴影框（以不同程度的灰色为基调，这是外部框，内部框由各布局装饰）
		jpanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(250, 250, 250), 1, true), 
															new LineBorder(new Color(230, 230, 230), 1, true)));
		jpanel.setBackground(new Color(200, 200, 200));

		
		//2.2创建背景JPanell面板组件作为内部边框
		JPanel jpanell = new JPanel();
		jpanell.setLayout(null);									//不使用Panel默认的布局，使用下方自定义布局
		jpanell.setBounds(3, 3, 1004, 704);;							//设置面板尺寸
		//设置阴影框（内部框）
		jpanell.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(180, 180, 180), 1, true), 
															new LineBorder(Color.black, 1, true)));
		jpanell.setBackground(new Color(210, 210, 210));							//设置面板背景为白色

		
		
		//3.使用Jpanel面板布局
		//顶部
		JPanel topPanel = B_group_chat_layout_package.createTopPanel(jf, number);
		jpanell.add(topPanel, BorderLayout.PAGE_START);
		
		//中间
		JPanel CenterPanel = B_group_chat_layout_package.createCenterPanel(jf, number);
		jpanell.add(CenterPanel, BorderLayout.CENTER);	
				
		//右侧
		JPanel RightPanel = B_group_chat_layout_package.createRightPanel(jf, number);
		jpanell.add(RightPanel, BorderLayout.LINE_END);
				
		//底部
		JPanel BottomPanel = B_group_chat_layout_package.createBottomPanel(jf, number);
		jpanell.add(BottomPanel, BorderLayout.PAGE_END);
		
		

		jpanel.add(jpanell);
		jf.add(jpanel);
		
		
		
		/**一进入聊天界面就开启客户端
		 * 使用UDP协议开启聊天功能的客户端
		 */
		
		//这里判断一下服务器是否开启，决定是否需要创建对象（if……）
		if(getState(number) == false) {
		System.out.println("已开启" + number + "客户端");
		//开启聊天功能的客户端（随时接收消息部分）
		new Thread(() -> {
			try {
				Universal_B_chat_client.keepReceive(number);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
		
		//将客户端状态改为true
		prop.setProperty(number + "客户端开启状态", "true");
		//同步信息
		try {
			prop.store(new FileWriter("Files\\IdAndPassword"), "password");
		} catch (IOException e) {
			e.printStackTrace();
		}		//第二个参数是一个描述信息
		
	}
			
		
}
	
	

	
	
	
	//定义获取客户端是否已开启
	public static boolean getState(String number) {
	//1.创建集合Properties对象
	prop = new Properties();
	
	//2.从流中读取文件内容
	try {
		prop.load(new FileReader("Files\\IdAndPassword"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	try {
		prop.store(new FileWriter("Files\\IdAndPassword"), "password");
	} catch (IOException e) {
		e.printStackTrace();
	}		//第二个参数是一个描述信息
	
	boolean flag = prop.getProperty(number + "客户端开启状态").equals("true");
	
	return flag;
}
	
	
}









