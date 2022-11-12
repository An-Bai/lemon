package com.lemon.login_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class B_login_layout_package {
	
	/**
	 * 创建一个私有的成员变量listener
	 * 	由于联系createCenterPanel()和createBottomPanel()的关系，登录过程需要将登录按钮和用户输入的文本相关联；
	 * 	需要扩大变量的作用域，使createCenterPanel()创建（new）的对象能在createBottomPanel()z中使用
	 * 	故需要在此先定义一个私有类的对象变量，此对象的作用是核对用户登录信息，以便授权成功登录用户主界面
	 */
	private static Universal_D_check_account_function listener;
	
	//界面顶部
	public static JPanel createTopPanel(JFrame jframe) {
		//1.创建JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);								//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(600, 195));	//设置面板尺寸
		
	
		//2.创建图片组件ImageIcon
		ImageIcon icon = new ImageIcon("images/登录界面背景.png");
			
		//3.创建标签组件，设置背景图		
		JLabel backGround = new JLabel(editImages(icon, "images/登录界面背景.png", 600, 195));
		backGround.setBounds(0, 0, 600, 195);				//设置标签的位置及尺寸

		//4.在右上角添加按钮 ——> 退出的按钮
		ImageIcon iconlx = new ImageIcon("images/蓝X.png");
		ImageIcon iconlxx = new ImageIcon("images/红X.png");
		JButton outButton = operateSystemuttons(iconlx, "images/蓝X.png", iconlxx, "images/红X.png", 45, 45);		//该方法会返回一个Jbutton对象
		outButton.setBounds(555, 0, 45, 45);
		outButton.setName("关闭"); 							//设置按钮名字
		outButton.addActionListener(e -> System.exit(0));	//为按钮注册监听器 功能：关闭窗体
		popupPrompt(jframe, outButton);						//加入监听事件：提示按钮功能
		jpanel.add(outButton);								//将按钮加入面板组件
		
		//5.在右上角添加按钮 ——> 最小化的按钮
		ImageIcon iconmin = new ImageIcon("images/最小化.png");
		ImageIcon iconminn = new ImageIcon("images/最小化虚化.png");
		JButton minButton = operateSystemuttons(iconmin, "images/最小化.png", iconminn, "images/最小化虚化.png", 45, 45);
		minButton.setBounds(510, 0, 45, 45);
		minButton.setName("最小化"); 							//设置按钮名字
		minButton.addActionListener(e -> jframe.setExtendedState(Frame.ICONIFIED));	//为按钮注册监听器 功能：最小化窗体
		popupPrompt(jframe, minButton);						//加入监听事件：提示按钮功能
		jpanel.add(minButton);								//将按钮加入面板组件
		
		//6.在右上角添加按钮 ——> 设置的按钮
		ImageIcon iconset = new ImageIcon("images/设置.png");
		ImageIcon iconsett = new ImageIcon("images/设置虚化.png");
		JButton setButton = operateSystemuttons(iconset, "images/设置.png", iconsett, "images/设置虚化.png", 45, 45);
		setButton.setBounds(465, 0, 45, 45);
		setButton.setName("设置"); 							//设置按钮名字
		actionListener(jframe, setButton); 					//加入监听事件：提示功能未开启
		popupPrompt(jframe, setButton);						//加入监听事件：提示按钮功能
		jpanel.add(setButton);								//将按钮加入面板组件
		
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

		jpanel.add(backGround);
		return jpanel;
	}

	
	
	//界面左侧
	public static JPanel createLeftPanel(JFrame jframe) {
		//1.创建JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);								//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(160, 180));	//设置面板尺寸
		jpanel.setBackground(Color.white);					//设置面板背景为白色
		
		String imageIP = "images/Lemon.jpg";				
		ImageIcon avatar = new ImageIcon(imageIP);			//设置登录头像
		JLabel jLabel = new JLabel(editImages(avatar, imageIP, 110, 110));				
		jLabel.setBounds(40, 50, 110, 110);
		
		jLabel.setBorder(new LineBorder(new Color(140, 255, 251), 2, true));				//设置标签组件边框颜色：天蓝
		//jLabel.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.pink));		//其它设置颜色的方法
		//jLabel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.blue), new LineBorder(Color.pink)));
		jpanel.add(jLabel);
		
		return jpanel;
	}
	
	
	
	//界面中部
	public static JPanel createCenterPanel(JFrame jframe) {
		//1.创建JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);									//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(270, 190));		//设置面板尺寸
		jpanel.setBackground(Color.white);						//设置面板背景为白色
		
		
		//String[] sArr = {"5201314", "7758258"};					//模拟已经登陆过的账号记录
		
		//2.创建账号列表项(测试数据，模拟登录过的账号)
		JComboBox<Object> box = new JComboBox<Object>();	
		box.setEditable(true); 									//设置下拉框可编辑
		box.setBounds(20, 60, 250, 40);							//设置位置
		box.setFont(new Font("Calibri", Font.PLAIN, 22));
		box.setBackground(Color.white);							//设置边框颜色：白色
		jpanel.add(box);
		
		//3.创建密码输入框
		JPasswordField jPasswordField = new JPasswordField();
		jPasswordField.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));	//流式布局，从左到右（上下）,为添加键盘图标准备
		jPasswordField.setBounds(20, 100, 250, 40);
		jPasswordField.setFont(new Font("Calibri", Font.PLAIN, 22));
		jPasswordField.setPreferredSize(new Dimension(200, 40));			//指定内部尺寸(setSize是设定的固定大小，而setPreferredSize仅仅是设置最好的大小)
		jpanel.add(jPasswordField);
		
		
		//4.创建小键盘图片按钮
		ImageIcon icon = new ImageIcon("images/小键盘图标.png");
		ImageIcon iconn = new ImageIcon("images/小键盘图标阴影.png");
		JButton btn = operateSystemuttons(icon, "images/小键盘图标.png", iconn, "images/小键盘图标阴影.png", 35, 35);
		btn.setPreferredSize(new Dimension(35, 35));
		btn.setName("打开软键盘"); 										//设置按钮名字
		btn.setContentAreaFilled(false);								//设置不显示按钮区域
		actionListener(jframe, btn);									//加入监听事件：提示功能未开启
		popupPrompt(jframe, btn);									//加入监听事件：提示按钮功能

		jPasswordField.add(btn);
		
		//5.创建两个勾选选项
		JCheckBox box1 = new JCheckBox("自动登录");
		box1.setFocusPainted(false);
		box1.setFont(new Font("宋体", Font.PLAIN, 14));
		box1.setBounds(35, 145, 100, 30);
		box1.setBackground(Color.white);
		
		JCheckBox box2 = new JCheckBox("记住密码");
		box2.setFocusPainted(false);
		box2.setFont(new Font("宋体", Font.PLAIN, 14));
		box2.setBounds(165, 145, 100, 30);
		box2.setBackground(Color.white);

		jpanel.add(box1);
		jpanel.add(box2);
		
		//创建一个LonginListener对象，然后传参（LonginListener为密码登录包装类）
		
		listener = new Universal_D_check_account_function(box, jPasswordField, jframe);
		
		jpanel.add(jPasswordField);

		return jpanel;
	}

	
	
	//界面右侧
	public static JPanel createRightPanel(JFrame jframe) {
		//1.创建JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(170, 190));	
		jpanel.setBackground(Color.white);							//设置面板背景为白色

		//2.创建注册账号的button1
		JButton button1 = new JButton("注册账号");
		button1.setForeground(new Color(25, 185, 255));				//设置此组件的前景颜色：蓝色
		button1.setBounds(0, 60, 100, 40);
		button1.setFont(new Font("宋体", Font.PLAIN, 16));
		button1.setContentAreaFilled(false);						//设置不显示按钮区域
		//设置按钮不要边框
		button1.setFocusPainted(false);
		button1.setBorderPainted(false);
		button1.addMouseListener(new MouseAdapter() {				//添加鼠标监听事件，重新设置此组件的前景颜色：紫色
			@Override
			public void mouseEntered(MouseEvent e) {
				button1.setForeground(new Color(220, 90, 240));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button1.setForeground(new Color(25, 185, 255));		//返回按钮原颜色
			}
			@Override
			public void mouseClicked(MouseEvent e) {				
				try {
					Universal_C_account_display_interface.accountNmbers(jframe);	//点击给予账号信息
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		//创建找回密码的button2
		JButton button2 = new JButton("找回密码");
		button2.setForeground(new Color(25, 185, 255));				//设置此组件的前景颜色：蓝色
		button2.setBounds(0, 100, 100, 40);
		button2.setFont(new Font("宋体", Font.PLAIN, 16));
		button2.setContentAreaFilled(false);						//设置不显示按钮区域
		//设置按钮不要边框
		button2.addMouseListener(new MouseAdapter() {				//添加鼠标监听事件，重新设置此组件的前景颜色：深蓝色
			@Override
			public void mouseEntered(MouseEvent e) {
				button2.setForeground(new Color(220, 90, 240));		//重新设置此组件的前景颜色：紫色
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button2.setForeground(new Color(25, 185, 255));		//返回按钮原颜色
			}
		});
		button2.setFocusPainted(false);
		button2.setBorderPainted(false);
		actionListener(jframe, button2); 							//加入监听事件：提示功能未开启
		jpanel.add(button1);
		jpanel.add(button2);
		
		return jpanel;
	}
	
	
	//界面尾部
	public static JPanel createBottomPanel(JFrame jframe) {
		//1.创建JPanel面板组件
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(600, 75));			
		jpanel.setBackground(Color.white);							//设置面板背景为白色

		//2.创建左下角多人登录按钮图标
		ImageIcon icon = new ImageIcon("images/多人登录按钮.png");
		ImageIcon iconn = new ImageIcon("images/多人登录按钮虚化.png");
		JButton btn1 = operateSystemuttons(icon, "images/多人登录按钮.png", iconn, "images/多人登录按钮虚化.png", 45, 45);
		btn1.setBounds(5, 25, 45, 45);; 							//设置按钮大小
		btn1.setName("多人登录");										//设置按钮名字
		btn1.setContentAreaFilled(false);							//设置不显示按钮区域
		actionListener(jframe, btn1);								//加入监听事件：提示功能未开启
		popupPrompt(jframe, btn1);									//加入监听事件：提示按钮功能
		jpanel.add(btn1);
		
		//3.创建底部中间登录按钮图标
		ImageIcon iconz = new ImageIcon("images/登录.png");
		ImageIcon iconzz = new ImageIcon("images/登录虚化.png");
		JButton btn2 = new JButton("登  录", editImages(iconz, "images/登录.png", 270, 50));
		btn2.setBounds(168, 0, 270, 50);
		btn2.setFocusPainted(false);
		btn2.setBorderPainted(false);
		//2.设置鼠标移动到退出按钮时，更改图片
		btn2.setRolloverIcon(editImages(iconzz, "images/登录虚化.png", 270, 50)); 
		btn2.setHorizontalTextPosition(SwingConstants.CENTER);		//将文字放在图片中间（否则显示不出文字“登     录”）
		btn2.setForeground(Color.white);
		btn2.setFont(new Font("黑体", Font.BOLD, 20));
		btn2.setContentAreaFilled(false);							//设置不显示按钮区域
		jpanel.add(btn2);
		
		//4.创建右下角二维码登录图标
		ImageIcon iconm = new ImageIcon("images/二维码.png");
		ImageIcon iconmm = new ImageIcon("images/二维码虚化.png");
		JButton btn3 = operateSystemuttons(iconm, "images/二维码.png", iconmm, "images/二维码虚化.png", 35, 35);
		btn3.setBounds(560, 35, 35, 35);
		btn3.setName("二维码登录");									//设置按钮名字
		btn3.setContentAreaFilled(false);							//设置不显示按钮区域
		actionListener(jframe, btn3);								//加入监听事件：提示功能未开启
		popupPrompt(jframe, btn3);									//加入监听事件：提示按钮功能
		jpanel.add(btn3);
		
		//为登录按钮注册监听，需要中间部位的账号密码
			btn2.addActionListener(listener);

		return jpanel;
	}
	
	
	/**
	 * 实现功能区
	 * 	1.	public static ImageIcon editImages(ImageIcon icon, String iamge, int width, int height);
	 * 
	 * 	2.	public static JButton operateSystemuttons(ImageIcon icon1, String iamge1, ImageIcon icon2, String iamge2, int width, int height)
	 *
	 *	3.	public static void actionListener(JFrame jFrame, JButton button) { };
	 *
	 *	4.	public static void popupPrompt(JFrame jFrame, JButton button) {};
	 *
	 */
	public static ImageIcon editImages(ImageIcon icon, String iamge, int width, int height) {
			//创建图片内容设置Image对象
			Image image = icon.getImage();
			
			//调整图片大小
			image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			
			//图片组件加入新设置好的image
			icon.setImage(image);		
			
			return icon;
	}
	
	public static JButton operateSystemuttons(ImageIcon icon1, String iamge1, ImageIcon icon2, String iamge2,
										int width, int height) {
		
		JButton Button = new JButton(editImages(icon1, iamge1, width, height));
		//1.设置按钮不要边框
		Button.setFocusPainted(false);
		Button.setBorderPainted(false);
		
		Button.setRolloverIcon(editImages(icon2, iamge2, width, height)); 		//设置按钮的翻转图标(当鼠标进入该组件时图标改变)
		
		return Button;
	}
	
	
	
	//打开功能未实现窗口
	public static void actionListener(JFrame jFrame, JButton button) {			//提示功能未实现
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("我被点击了！！！");
				SwingUtilities.invokeLater(() -> Universal_A_function_useless_prompts_forms.createAndShowGUI(jFrame));
			}
		});;
	}
	
	
	public static void popupPrompt(JFrame jFrame, JButton button) { 				//功能名称弹出提示
		
		button.addMouseListener(new MouseAdapter() {								//添加鼠标监听事件，重新设置此组件的前景颜色：深蓝色
			@Override
			public void mouseEntered(MouseEvent e) {
				SwingUtilities.invokeLater(() -> Universal_B_funtion_promps.createAndShowGUI(jFrame, button, e));
			}
			/**
			public void mouseExited(MouseEvent e) {
				if(universal_B_功能名称弹出提示.popupMenu.isVisible())
				universal_B_功能名称弹出提示.popupMenu.setVisible(false);					//关闭提示
			}
			*/
	});
		

		
	
}
	
	

}





















