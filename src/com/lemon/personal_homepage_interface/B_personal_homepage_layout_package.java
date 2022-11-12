package com.lemon.personal_homepage_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.lemon.login_interface.B_login_layout_package;
import com.lemon.loginchat_interface.A_group_chat_entrance;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class B_personal_homepage_layout_package {
	
	private static Properties prop;
	
	//界面顶部
	public static JPanel createTopPanel(JFrame jframe, String number) {
		
		//0.调用createContainer(number)方法返回一个包装有用户信息的Properties容器
		 try {
			prop = createContainer(number);				
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//1.1创建背景JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(440, 270));			//设置面板尺寸
		
		//1.2创建背景JPanell面板组件
		JPanel jpanell = new JPanel();
		jpanell.setLayout(null);									//不使用Panel默认的布局，使用下方自定义布局
		jpanell.setBounds(3, 3, 434, 267);;							//设置面板尺寸
		
		//2.1模拟设置阴影框（以不同程度的灰色为基调，前后共四层）
		jpanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(250, 250, 250), 1, true), 
															new LineBorder(new Color(230, 230, 230), 1, true)));
		jpanel.setBackground(new Color(200, 200, 200));	
		
		//2.2设置阴影框
		jpanell.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(180, 180, 180), 1, true), 
															new LineBorder(Color.black, 1, true)));
		jpanell.setBackground(Color.white);							//设置面板背景为白色
		
		//将jpanell面板组件加入到jpanel面板组件中
		jpanel.add(jpanell);
		
		//3.设置背景标签组件
		String imageIP = "images/白蓝底色图.jpg";				
		ImageIcon background = new ImageIcon(imageIP);								//设置登录头像
		JLabel jLabel = new JLabel(B_login_layout_package.editImages(background, imageIP, 430, 265));				
		jLabel.setBounds(2, 2, 430, 265);
		jpanell.add(jLabel);
		
		//3.设置头像标签组件
		String imageID = Universal_A_avatar_and_background_manage.Avatar(number);						//获取指定头像图片路径			
		ImageIcon Avatar = new ImageIcon(imageID);									//设置登录头像
		JLabel jLabelavatar = new JLabel(B_login_layout_package.editImages(Avatar, imageID, 100, 100));				
		jLabelavatar.setBounds(20, 20, 100, 100);
		jLabelavatar.setBorder(new LineBorder(new Color(255, 174, 200), 2, true));		//设置标签组件边框颜色：粉色
		jLabel.add(jLabelavatar);														//将头像标签组件加入背景标签组件
		
		//4.在右上角添加按钮 ——> 关闭的按钮
		ImageIcon iconlx = new ImageIcon("images/主界面关闭蓝.png");
		ImageIcon iconlxx = new ImageIcon("images/主界面关闭红.png");
		JButton outButton = B_login_layout_package.operateSystemuttons(iconlx, "images/主界面关闭蓝.png", 
																iconlxx, "images/主界面关闭红.png", 40, 40);		//该方法会返回一个Jbutton对象
		outButton.setBounds(392, 0, 38, 38);
		outButton.setName("关闭"); 														//设置按钮名字
		outButton.addActionListener(e -> {												//为按钮注册监听器 功能：关闭窗体
		changeCondition(number);														//修改个人客户端状态和在线状态，将状态改为“false”
		changeServer(); 																//如果没人在线，关闭服务器
					
		System.exit(0);																	//最后才关闭JVM
		});	
		B_login_layout_package.popupPrompt(jframe, outButton);									//加入监听事件：提示按钮功能
		jLabel.add(outButton);															//将按钮加入面板组件
			
		
		
		//5.在右上角添加按钮 ——> 最小化的按钮
		ImageIcon iconmin = new ImageIcon("images/主页最小化.png");
		ImageIcon iconminn = new ImageIcon("images/主页最小化阴影.png");
		JButton minButton = B_login_layout_package.operateSystemuttons(iconmin, "images/主页最小化.png",
																iconminn, "images/主页最小化阴影.png", 40, 40);
		minButton.setBounds(354, 0, 38, 38);
		minButton.setName("最小化"); 												//设置按钮名字
		minButton.addActionListener(e -> jframe.setExtendedState(Frame.ICONIFIED));	//为按钮注册监听器 功能：最小化窗体
		B_login_layout_package.popupPrompt(jframe, minButton);							//加入监听事件：提示按钮功能
		jLabel.add(minButton);													//将按钮加入面板组件
		
		
		//6.获取可拖动功能（仅Topanel面板）
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
		
		//7.创建编辑资料的JButton按钮
		JButton button1 = new JButton("编辑资料");
		button1.setForeground(new Color(30, 165, 230));				//设置此组件的前景颜色：深蓝色
		button1.setBounds(10, 130, 120, 30);
		button1.setFont(new Font("宋体", Font.PLAIN, 18));
		button1.setContentAreaFilled(false);						//设置不显示按钮区域
		//设置按钮不要边框
		button1.setFocusPainted(false);
		button1.setBorderPainted(false);
		button1.addMouseListener(new MouseAdapter() {				//添加鼠标监听事件，重新设置此组件的前景颜色：深蓝色
			@Override
			public void mouseEntered(MouseEvent e) {
				button1.setForeground(new Color(35, 185, 255));		//改变字体颜色：浅蓝色
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button1.setForeground(new Color(30, 165, 230));		//返回按钮原颜色：深蓝色
			}
		});
		/**
		 * 给“编辑资料”按钮注册监听，实现编辑用户个人信息功能
		 */
		C_personal_homepage_editdata_forms EditListener = new C_personal_homepage_editdata_forms(number, jframe); 	//创建一个名为EditListener的对象
		button1.addActionListener(EditListener);
		
		jLabel.add(button1);
		
		//8.创建文字标签
		//8.1 个人信息
		JLabel jlabelMessage1 = new JLabel("个人信息", JLabel.CENTER);
		jlabelMessage1.setForeground(new Color(255, 35, 235));					//设置字体的颜色：紫色
		jlabelMessage1.setFont(new Font("黑体", Font.PLAIN, 19));					//设置字体规格
		jlabelMessage1.setBounds(110, 20, 120, 30);
		jLabel.add(jlabelMessage1);
		
		//8.2账号信息
		String passName = number + "（" + prop.getProperty("昵称") + ")";
		JLabel jlabelMessage2 = new JLabel(passName, JLabel.LEFT);
		jlabelMessage2.setForeground(Color.black);								//设置字体的颜色：黑色
		jlabelMessage2.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		jlabelMessage2.setBounds(150, 55, 250, 30);
		jLabel.add(jlabelMessage2);
		
		//8.3出生信息
		String Message1 = prop.getProperty("性别") + "  " + prop.getProperty("年龄") + "  " + prop.getProperty("出生月份") + "月"
										+ prop.getProperty("出生日")+ "日   " + prop.getProperty("星座");
		JLabel jlabelMessage3 = new JLabel(Message1, JLabel.LEFT);
		jlabelMessage3.setForeground(Color.black);								//设置字体的颜色：黑色
		jlabelMessage3.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		jlabelMessage3.setBounds(150, 90, 250, 30);
		jLabel.add(jlabelMessage3);
		
		//8.4随心贴
		JLabel jlabelMessage4 = new JLabel("随心贴：", JLabel.CENTER);
		jlabelMessage4.setForeground(new Color(255, 155, 15));					//设置字体的颜色：橙色
		jlabelMessage4.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		jlabelMessage4.setBounds(150, 135, 120, 30);
		jLabel.add(jlabelMessage4);
		
		//8.5创建一个JTextArea文本组件	prop.getProperty("随心贴")
		//给文本域提供字符串（加输出换行处理，限制一行12个字）
		String toSay = "";
		char[] box = prop.getProperty("随心贴").toCharArray();
		int i = 0;
		int n = box.length / 12;
		for(i = 0; i < n; i ++) {
			toSay += String.copyValueOf(box, 12 * i, 12) + "\n";
		}
		toSay += String.copyValueOf(box, 12 * i, box.length - 12 * i);
		
		JTextArea text = new JTextArea(toSay, 3, 3);									
		text.setEditable(false);												//设置文本域不可编辑
		text.setForeground(new Color(55, 255, 235));							//设置文本域字体的颜色：青蓝色
		text.setOpaque(false); 													//设置背景透明
		text.setFont(new Font("黑体", Font.PLAIN, 18));							//设置字体规格
		text.setBounds(180, 175, 220, 100);
		jLabel.add(text);
		
		//创建一个进入聊天界面的按钮
		ImageIcon enter = new ImageIcon("images/主页enter蓝.png");
		ImageIcon entered = new ImageIcon("images/主页enter浅蓝.png");
		JButton enterbutton = new JButton("ENTER", B_login_layout_package.editImages(enter, "images/主页enter蓝.png", 110, 50));
		enterbutton.setBounds(30, 180, 110, 50);
		enterbutton.setName("进入聊天界面");
		B_login_layout_package.popupPrompt(jframe, enterbutton);										//加入监听事件：提示按钮功能
		enterbutton.setFocusPainted(false);
		enterbutton.setBorderPainted(false);
		//2.设置鼠标移动到退出按钮时，更改图片
		enterbutton.setRolloverIcon(B_login_layout_package.editImages(entered, "images/主页enter浅蓝.png", 110, 50)); 
		enterbutton.setHorizontalTextPosition(SwingConstants.CENTER);							//将文字放在图片中间（否则显示不出文字“ENTER”）
		enterbutton.setForeground(Color.white);
		enterbutton.setFont(new Font("黑体", Font.BOLD, 20));
		enterbutton.setContentAreaFilled(false);												//设置不显示按钮区域
		enterbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> A_group_chat_entrance.multiplayerChat(number));		//打开聊天界面
			}
		});
		
		
		jLabel.add(enterbutton);
		
		return jpanel;
	}

	


	//界面中部
	public static JPanel createCenterPanel(JFrame jframe, String number) {
		
		//1.1创建背景JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(440, 370));			//设置面板尺寸
		
		//1.2创建背景JPanell面板组件
		JPanel jpanell = new JPanel();
		jpanell.setLayout(null);									//不使用Panel默认的布局，使用下方自定义布局
		jpanell.setBounds(3, 0, 434, 367);;							//设置面板尺寸
		
		//2.1模拟设置阴影框（以不同程度的灰色为基调，前后共四层）
		jpanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(250, 250, 250), 1, true), 
															new LineBorder(new Color(230, 230, 230), 1, true)));
		jpanel.setBackground(new Color(200, 200, 200));	

		//2.2设置阴影框
		jpanell.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(180, 180, 180), 1, true), 
															new LineBorder(Color.black, 1, true)));
		jpanell.setBackground(new Color(160, 160, 160));							//设置面板背景为灰色
		
		//将jpanell面板组件加入到jpanel面板组件中
		jpanel.add(jpanell);
		
		//3.设置背景标签组件
		String imageIP = Universal_A_avatar_and_background_manage.backGround(number);				
		ImageIcon background = new ImageIcon(imageIP);								//设置登录头像
		JLabel jLabel = new JLabel(B_login_layout_package.editImages(background, imageIP, 430, 300));				
		jLabel.setBounds(2, 0, 430, 300);
		jpanell.add(jLabel);
		
		//8.4个性签名
		String selfHoood = prop.getProperty("个性签名");
		JLabel jlabelMessage4 = new JLabel(selfHoood, JLabel.LEFT);
		jlabelMessage4.setForeground(Color.white);					//设置字体的颜色：黑色
		jlabelMessage4.setFont(new Font("黑体", Font.BOLD, 24));					//设置字体规格
		jlabelMessage4.setBounds(20, 300, 330, 67);
		jpanell.add(jlabelMessage4);
		
		//创建一个进入更换的按钮
		ImageIcon iconlx = new ImageIcon("images/更换封面.png");
		ImageIcon iconlxx = new ImageIcon("images/更换封面浅灰.png");
		JButton changeImage = B_login_layout_package.operateSystemuttons(iconlx, "images/更换封面.png", 
																iconlxx, "images/更换封面浅灰.png", 63, 63);		//该方法会返回一个Jbutton对象
		changeImage.setBounds(367, 300, 63, 63);
		changeImage.setName("更换封面"); 												//设置按钮名字
		B_login_layout_package.popupPrompt(jframe, changeImage);							//加入监听事件：提示按钮功能
		B_login_layout_package.actionListener(jframe, changeImage); 							//加入监听事件：提示功能未开启
		jpanell.add(changeImage);													//将按钮加入面板组件
			
		return jpanel;
	}

	/**
	 * 
	 * 方法功能区
	 * @return 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */

	public static Properties createContainer(String number) throws FileNotFoundException, IOException {
		//1.获取父级路径path
		File directory = new File(".");						//此new对象用于获取当前路径
		String path = null;
		try {
			path = directory.getCanonicalPath();			//获取标准的路径——上一级文件夹路径；而getAbsolutePath()获取的是本级路径。
		} catch (IOException e) {							//getCanonicalPath()需要catch...try，而getAbsolutePath()不需要
			e.printStackTrace();
			
		}
		
		//2.获取文件的绝对路径
		String AbsolutePath = path + "\\Users information\\" + number + "\\编辑资料\\基本资料";
		
		//3.创建集合Properties对象
		Properties prop = new Properties();
		
		//2.从流中读取文件内容（先获取源文件资料信息）
			prop.load(new FileReader(AbsolutePath));

		return prop;
	}

	
	//修改服务端状态
	public static void changeServer() {
		//1.创建集合Properties对象
		Properties prop = new Properties();
		
		//2.从流中读取文件内容
		try {
			prop.load(new FileReader("Files\\IdAndPassword"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//2.模拟数据库查询
		int flag = 0;
		String[] numbers = {"1111111111", "6666666666", "9999999999", "0123456789", "5211314521"};
		for(int i = 0; i < 5; i ++) {
			if(prop.getProperty(numbers[i] + "在线状态").equals("true")) {			//判断账号密码及在线状态是否均符合 
				flag = 1;
				break;
			}
			}
		if(flag == 0) {
			prop.setProperty("服务端开启状态", "false");
			try {
				prop.store(new FileWriter("Files/IdAndPassword"), "password");//模拟将修改的数据同步到数据库
			} catch (IOException e1) {										
				e1.printStackTrace();
			}
		}
	
	}
	

	
	//修改数据库里客户端状态和在线状态
	public static void changeCondition(String number) {
		//1.创建集合Properties对象
		Properties proper = new Properties();
		
		//2.从流中读取文件内容
		try {
			proper.load(new FileReader("Files\\IdAndPassword"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
			proper.setProperty(number + "客户端开启状态", "false");
			proper.setProperty(number + "在线状态", "false");
			
			try {
				proper.store(new FileWriter("Files/IdAndPassword"), "password");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
}











