package com.lemon.loginchat_interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.lemon.login_interface.B_login_layout_package;
import com.lemon.login_interface.Universal_A_function_useless_prompts_forms;

public class B_group_chat_layout_package {
	//扩大作用域
	public static JTextArea view;
	
	//顶部
	public static JPanel createTopPanel(JFrame jframe, String number) {
		//1.创建JPanel面板
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);														//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setBounds(2, 2, 1000, 50);											//设置面板尺寸
		
		
		//2.给顶部栏上色
		String imageIP = "images\\聊天顶部背景.jpg";				
		ImageIcon topbackground = new ImageIcon(imageIP);							//设置登录头像
		JLabel topjlabel = new JLabel(B_login_layout_package.editImages(topbackground, imageIP, 1000, 50));				
		topjlabel.setBounds(0, 0, 1000, 50);
		jpanel.add(topjlabel);
		
		
		//3.在右上角添加按钮 ——> 关闭的按钮
		ImageIcon iconlx = new ImageIcon("images\\白蓝X.png");
		ImageIcon iconlxx = new ImageIcon("images\\白红X.png");
		JButton outButton = B_login_layout_package.operateSystemuttons(iconlx, "images\\白蓝X.png", 
																iconlxx, "images\\白红X.png", 50, 50);		//该方法会返回一个Jbutton对象
		outButton.setBounds(950, 0, 50, 50);
		outButton.setName("关闭"); 													//设置按钮名字
		outButton.addActionListener(new ActionListener() {							//为按钮注册动作监听器 功能：关闭窗体
			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();													//关闭窗口		
			}
		});	
		B_login_layout_package.popupPrompt(jframe, outButton);								//加入鼠标监听事件：提示按钮功能
		topjlabel.add(outButton);													//将按钮加入面板组件
		
		
		//4.在右上角添加按钮 ——> 最大化的按钮
		ImageIcon iconmax = new ImageIcon("images\\白蓝最大化.png");
		ImageIcon iconmaxx = new ImageIcon("images\\白蓝最大化浅.png");
		JButton miaxButton = B_login_layout_package.operateSystemuttons(iconmax, "images\\白蓝最大化.png",
																iconmaxx, "images\\白蓝最大化浅.png", 50, 50);
		miaxButton.setBounds(900, 0, 50, 50);
		miaxButton.setName("最大化"); 															//设置按钮名字
		miaxButton.addActionListener(e -> Universal_A_function_useless_prompts_forms.createAndShowGUI(jframe));		//为按钮注册监听器 功能未实现提示
		B_login_layout_package.popupPrompt(jframe, miaxButton);											//加入监听事件：提示按钮功能
		topjlabel.add(miaxButton);
		
		
		//5.在右上角添加按钮 ——> 最小化的按钮
		ImageIcon iconmin = new ImageIcon("images\\白蓝最小化.png");
		ImageIcon iconminn = new ImageIcon("images\\白蓝最小化浅.png");
		JButton minButton = B_login_layout_package.operateSystemuttons(iconmin, "images\\白蓝最小化.png",
																iconminn, "images\\白蓝最小化浅.png", 50, 50);
		minButton.setBounds(850, 0, 50, 50);
		minButton.setName("最小化"); 														//设置按钮名字
		minButton.addActionListener(e -> jframe.setExtendedState(Frame.ICONIFIED));		//为按钮注册监听器 功能：最小化窗体
		B_login_layout_package.popupPrompt(jframe, minButton);									//加入监听事件：提示按钮功能
		topjlabel.add(minButton);														//将按钮加入面板组件
			
		//6.在右上角添加按钮 ——> 窗口功能的按钮
		ImageIcon iconfun = new ImageIcon("images\\白蓝功能.png");
		ImageIcon iconfunn = new ImageIcon("images\\白蓝功能浅.png");
		JButton ableButton = B_login_layout_package.operateSystemuttons(iconfun, "images\\白蓝功能.png", 
																iconfunn, "images\\白蓝功能浅.png", 50, 50);		//该方法会返回一个Jbutton对象
		ableButton.setBounds(800, 0, 50, 50);
		ableButton.setName("窗口功能设置"); 												//设置按钮名字
		B_login_layout_package.popupPrompt(jframe, ableButton);									//加入监听事件：提示按钮功能
		//待实现的功能监听
		//……
		ableButton.addActionListener(e -> Universal_E_chat_layout_function_options.createAndShowGUI(jframe, ableButton, e, number));
		topjlabel.add(ableButton);														//将按钮加入面板组件
		
		//7.添加窗口名标签组件
		JLabel winname = new JLabel("Lemon");
		winname.setBounds(450, 0, 100, 50);
		winname.setFont(new Font("黑体", Font.BOLD, 32));
		winname.setForeground(Color.white);
		topjlabel.add(winname);
		
		//8.添加窗口图标组件
		ImageIcon logo = new ImageIcon("images\\图标彩.png");
		JLabel winlogo = new JLabel(B_login_layout_package.editImages(logo, "images\\图标彩.png", 36, 36));
		winlogo.setBounds(405, 7, 36, 36);
		topjlabel.add(winlogo);
		
		
		//9.获取可拖动功能（仅Topanel面板）
		jpanel.addMouseMotionListener(new MouseMotionAdapter() {
			int old_x;
			int old_y;
			@Override
			public void mouseDragged(MouseEvent e) {
				jframe.setLocation(jframe.getLocation().x + e.getX() - old_x, 
									jframe.getLocation().y + e.getY() - old_y);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				old_x = e.getX();
				old_y = e.getY();
			}
		});
		
		
		return jpanel;
	}
	

	
	
	
	
	
	//中间
	public static JPanel createCenterPanel(JFrame jframe, String number) {
		//1.创建一个中间面板框
		JPanel midjpanel = new JPanel();
		midjpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		midjpanel.setBounds(2, 52, 770, 470);							//设置面板尺寸
		midjpanel.setBackground(new Color(210, 210, 210));
		
		
		//2.创建一个图片标签组件做消息背景
		ImageIcon writeboard = new ImageIcon("images\\消息显示框.png");
		JLabel vessel = new JLabel(B_login_layout_package.editImages(writeboard, "images\\消息显示框.png", 770, 470));
		vessel.setBounds(0, 0, 770, 470);
		midjpanel.add(vessel);
		
		
		//3.创将一个JTextArea文本域
		try {
			view = new JTextArea(writeData(number));					//创建的同时将以前的聊天记录打印到文本域
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		view.setEditable(false);
		view.setFont(new Font("黑体", Font.BOLD, 24));
		view.setForeground(new Color(0, 168, 243));			//青蓝色
		
		
		
		//4.创建带有滚动条的空面板
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(0, 1, 723, 468);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		jScrollPane.setViewportView(view);
		vessel.add(jScrollPane);
		
		
		return midjpanel;
	}

	
	
	
	
	//右侧
	public static JPanel createRightPanel(JFrame jframe, String number) {
		//1.创建一个右上角面板框
		JPanel cantonjpanel = new JPanel();
		cantonjpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		cantonjpanel.setBounds(777, 52, 225, 650);							//设置面板尺寸
		cantonjpanel.setBackground(new Color(210, 210, 210));
		
		
		//2.创建一个图片标签组件做文件上传框
		ImageIcon upright = new ImageIcon("images\\右上角框.png");
		JLabel vessel = new JLabel(B_login_layout_package.editImages(upright, "images\\右上角框.png", 225, 300));
		vessel.setBounds(0, 0, 225, 300);

		
		//3.1创建一个图片按钮组件做文件上传栏
		ImageIcon file1 = new ImageIcon("images\\上传的文件.png");
		JButton upload = new JButton("已上传的文件", B_login_layout_package.editImages(file1, "images\\上传的文件.png", 225, 40));
		upload.setBounds(2, 0, 225, 40);
		upload.setFocusPainted(false);
		upload.setBorderPainted(false);
		upload.setHorizontalTextPosition(SwingConstants.CENTER);		//将文字放在图片中间（否则显示不出文字“登     录”）
		upload.setForeground(Color.white);
		upload.setFont(new Font("黑体", Font.BOLD, 18));
		upload.setContentAreaFilled(false);							//设置不显示按钮区域
		
		//3.2创建一个图片按钮组件做文件上传栏的底部色
		ImageIcon file2 = new ImageIcon("images\\上传的文件蓝.png");
		JButton download = new JButton(B_login_layout_package.editImages(file2, "images\\上传的文件蓝.png", 225, 40));
		download.setBounds(2, 260, 225, 40);
		download.setFocusPainted(false);
		download.setBorderPainted(false);
		download.setContentAreaFilled(false);							//设置不显示按钮区域
				
				
		//加入组件的的顺序要注意（先加入的在最表面）
		cantonjpanel.add(upload);
		cantonjpanel.add(download);
		cantonjpanel.add(vessel);
		
		//4.创将一个JTextArea文本域
		JTextArea fileview = new JTextArea();
		fileview.setEditable(false);
		fileview.setFont(new Font("黑体", Font.BOLD, 24));
		//想文本域中加入已上传文件名，调用字符串获取方法
		//…………
		
		//5.为文件上传栏加入滑轮面板组件
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(3, 40, 255, 220);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		jScrollPane.setViewportView(fileview);
		vessel.add(jScrollPane);
		
		
		//6.创建一个图片标签组件做好友列表框
		ImageIcon downright = new ImageIcon("images\\右下角框.png");
		JLabel vessell = new JLabel(B_login_layout_package.editImages(downright, "images\\右下角框.png", 225, 345));
		vessell.setBounds(0, 305, 225, 345);
		
		//7.1创建一个图片按钮组件做好友在线人数显示
		ImageIcon list = new ImageIcon("images\\上传的文件.png");
		String online = "群成员 5/" + String.valueOf(countSum());
		JButton friend = new JButton(online, B_login_layout_package.editImages(list, "images\\上传的文件.png", 225, 40));
		friend.setBounds(2, 305, 225, 40);
		friend.setFocusPainted(false);
		friend.setBorderPainted(false);
		friend.setHorizontalTextPosition(SwingConstants.CENTER);		//将文字放在图片中间（否则显示不出文字“登     录”）
		friend.setForeground(Color.white);
		friend.setFont(new Font("黑体", Font.BOLD, 20));
		friend.setContentAreaFilled(false);							//设置不显示按钮区域
		
		//创建一个刷新按钮组件刷新好友在线人数显示
		JButton man = new JButton("刷新");
		man.setBounds(167, 300, 70, 50);
		man.setName("刷新好友在线状态");
		man.setFocusPainted(false);
		man.setBorderPainted(false);
		man.setHorizontalTextPosition(SwingConstants.CENTER);						//将文字放在图片中间（否则显示不出文字“登     录”）
		man.setForeground(new Color(255, 250, 120));								//黄色
		man.setFont(new Font("黑体", Font.PLAIN, 16));		
		B_login_layout_package.popupPrompt(jframe, man); 									//提示功能
		man.setContentAreaFilled(false);											//设置不显示按钮区域
		man.addMouseListener(new MouseAdapter() {									//添加鼠标监听事件，重新设置此组件的前景颜色：深蓝色
			@Override
			public void mouseExited(MouseEvent e) {
				man.setForeground(new Color(255, 250, 120));						//返回按钮原颜色：黄色
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				man.setForeground(new Color(0, 20, 255));							//返回按钮原颜色：深蓝色
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				jframe.dispose();													//关闭JVM虚拟机
				//在打开个人主界面
				//……	
				SwingUtilities.invokeLater(() -> A_group_chat_entrance.multiplayerChat(number));	//打开窗口
			}
			
			
		});
		
		
		//7.2创建一个图片按钮组件做文件上传栏的底部色
		ImageIcon list2 = new ImageIcon("images\\上传的文件蓝.png");
		JButton bottom = new JButton(B_login_layout_package.editImages(list2, "images\\上传的文件蓝.png", 225, 40));
		bottom.setBounds(2, 610, 225, 40);
		bottom.setFocusPainted(false);
		bottom.setBorderPainted(false);
		bottom.setContentAreaFilled(false);							//设置不显示按钮区域

		//加入组件的的顺序要注意（先加入的在最表面）
		cantonjpanel.add(man);
		cantonjpanel.add(friend);
		cantonjpanel.add(bottom);
		cantonjpanel.add(vessell);

		
		//9.为文件上传栏加入滑轮面板组件
		JScrollPane Pane = new JScrollPane();
		Pane.setBounds(3, 40, 255, 265);
		Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	
		Pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		Pane.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		//Pane.setViewportView(friendlist);
		//创建五个测试账号的文字标签组件
		
		String[] str = {"1111111111", "6666666666", "9999999999", "5211314521", "0123456789"};
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.white);
		for(int i = 0; i < 5; i ++) {
			jp.add(makeJlabel(str[i], i));			
		}
		Pane.setViewportView(jp);
		vessell.add(Pane);
		
		return cantonjpanel;
	}

	
	
	
	
	
	//底部
	public static JPanel createBottomPanel(JFrame jframe, String number) {
		JPanel basejpanel = new JPanel();
		basejpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		basejpanel.setBounds(2, 527, 770, 175);							//设置面板尺寸
		basejpanel.setBackground(new Color(210, 210, 210));
		
		
		//2.创建一个图片标签组件做消息背景
		ImageIcon writeboard = new ImageIcon("images\\消息输出框.png");
		JLabel vessel = new JLabel(B_login_layout_package.editImages(writeboard, "images\\消息输出框.png", 770, 175));
		vessel.setBounds(0, 0, 770, 175);

		basejpanel.add(vessel);
		
		//3.创将一个JTextArea文本域
		JTextArea write = new JTextArea();
		write.setFont(new Font("黑体", Font.PLAIN, 20));
		
		
		//4.创建带有滚动条的空面板
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(0, 1, 723, 128);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		jScrollPane.setViewportView(write);
		
		
		
		//5加入窗体关闭和发送按钮
		//5.1设置关闭按钮
		JButton btn1 = new JButton("关闭(C)");
		btn1.setBounds(490, 130, 95, 35);
		btn1.setForeground(Color.black); 							//设置字体颜色：白色
		btn1.setBackground(Color.white);							//设置背景颜色：白色
		btn1.setFont(new Font("黑体", Font.BOLD, 16));	 			//设置字体
		//设置边框为灰色
		btn1.setBorder(new LineBorder(new Color(160, 160, 160), 1, true));
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn1.setForeground(Color.white); 							//设置字体颜色：黑色
				btn1.setBackground(new Color(210, 210, 210));							//设置背景颜色：灰色
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn1.setForeground(Color.black); 							//设置字体颜色：白色
				btn1.setBackground(Color.white);							//设置背景颜色：白色
			}
		});
		btn1.addActionListener(new ActionListener() {							//为按钮注册动作监听器 功能：关闭窗体和修改服务端状态
			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();													//关闭窗口
				//在打开个人主界面
				//……	
				
			}
		});	
		
		//5.2设置发送按钮
		JButton btn2 = new JButton("发送(S)");
		btn2.setBounds(605, 130, 95, 35);
		btn2.setForeground(Color.white); 								//设置字体颜色：白色
		btn2.setBackground(new Color(140, 140, 140));					//设置背景颜色：灰色
		btn2.setFont(new Font("黑体", Font.BOLD, 16));	 				//设置字体
		//设置按钮不要边框
		btn2.setFocusPainted(false);
		btn2.setBorderPainted(false);
		btn2.addMouseListener(new MouseAdapter() {							//为“确定”按钮设置监听器
			@Override
			public void mouseEntered(MouseEvent e) {
				btn2.setBackground(new Color(210, 210, 210));				//设置背景颜色：白色；字体颜色不变
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn2.setBackground(new Color(140, 140, 140));				//将背景颜色设回灰色
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {						//点击开启客户端发送消息
				sendMessage(write, number);									//发消息判断
				write.setText(""); 											//清空消息输入框的内容

			}			
			
		});
		
		
		//5.3设置“>”按钮
		JButton btn3 = new JButton(">");
		btn3.setBounds(700, 130, 43, 35);
		btn3.setForeground(Color.white); 								//设置字体颜色：白色
		btn3.setBackground(new Color(140, 140, 140));					//设置背景颜色：灰色
		btn3.setFont(new Font("宋体", Font.BOLD, 16));	 				//设置字体
		//设置按钮不要边框
		btn3.setFocusPainted(false);
		btn3.setBorderPainted(false);
		btn3.addMouseListener(new MouseAdapter() {							//为“确定”按钮设置监听器
			@Override
			public void mouseEntered(MouseEvent e) {
				btn3.setBackground(new Color(210, 210, 210));							//设置背景颜色：白色；字体颜色不变
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn3.setBackground(new Color(140, 140, 140));				//将背景颜色设回灰色
			}
		});
		
		vessel.add(btn1);
		vessel.add(btn2);
		vessel.add(btn3);
		vessel.add(jScrollPane);
		
		
		
	
		
		return basejpanel;
	}

	

	
	/**
	 * 实现功能区
	 * @throws IOException 
	 * 
	 */
	//开启客户端和判断发送数据是否为空并返回
	public static void sendMessage(JTextArea write, String number) {
		String str = write.getText();
		String data = number + str;
		if(str != null && str.length() != 0) {
			Universal_B_chat_client.Client(data, number);
		}
	}

	

	//好友在线显示
	public static JButton makeJlabel(String str, int i) {
		
		//1.1创建集合Properties对象
		Properties prop1 = new Properties();
		//1.2从流中读取文件内容
		try {
			prop1.load(new FileReader("Files\\IdAndPassword"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//判断在线状态
		String src = "离线";
		if(prop1.getProperty(str + "在线状态").equals("true")) {
			src = "在线";
		}
		
		//2.1.创建另一个集合Properties对象
		Properties prop2 = new Properties();
		//2.2.从流中读取文件内容
		try {
			prop2.load(new FileReader("Users information\\" + str + "\\编辑资料\\基本资料"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel jl = new JLabel(src);
		jl.setBounds(0, 0, 50, 40);
		jl.setFont(new Font("黑体", Font.BOLD, 15));
		jl.setBackground(Color.white);
		if(src == "离线")
			jl.setForeground(new Color(0, 100, 215));			//蓝色
		else 
			jl.setForeground(new Color(50, 240, 210));			//绿色
		
		String arr = "    " + prop2.getProperty("昵称") + "(" + str + ")";

		
		JButton jbutton = new JButton(arr);
		jbutton.setBounds(0, i*40, 225, 40);
		jbutton.setFont(new Font("黑体", Font.BOLD, 19));
		jbutton.setForeground(new Color(255, 140, 210));			//粉色
		jbutton.setBackground(Color.white);
		//设置按钮不要边框
		jbutton.setFocusPainted(false);
		jbutton.setBorderPainted(false);
		jbutton.add(jl);
		
		jbutton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				jbutton.setBackground(new Color(225, 225, 225));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jbutton.setBackground(Color.white);
			}
			
		});
		
		return jbutton;
	}
	
	
	
	//计算好友在线人数
	public static int countSum() {
		int sum = 0;
		//1.创建集合Properties对象
		Properties prop = new Properties();
		
		//2.从流中读取文件内容
		try {
			prop.load(new FileReader("Files\\IdAndPassword"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//2.模拟数据库查询
		String[] numbers = {"1111111111", "6666666666", "9999999999", "0123456789", "5211314521"};
		for(int i = 0; i < 5; i ++) {
			if(prop.getProperty(numbers[i] + "在线状态").equals("true"))			//判断账号密码及在线状态是否均符合 
			sum ++;
			}
		
		return sum;
	}
	
	
	

	
	//将聊天输入的数据打印在显示框里
	public static void dataPrintf(char[] data, String number) {
		String a = null;
		
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
		
		view.append(call + " " + day.toLocaleString() + "\n");
		
		view.append(a + "\n");

	}

	
	
	
	//把历史聊天记录写到显示文本域，返回字符串
	public static String writeData(String number) throws IOException {
		String data = "";
			
			String path = "Users information\\" + number  + "\\聊天记录\\Chat datas";

			//创建一个字符流输入对象
			FileReader fr = new FileReader(path);

			int j = 0;
			while((j = fr.read()) != -1) {
				data += String.valueOf((char)j);
			}
			
				fr.close();
		
		return data;
	}
	
	

	
}










