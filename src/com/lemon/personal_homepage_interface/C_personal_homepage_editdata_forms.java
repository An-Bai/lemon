package com.lemon.personal_homepage_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class C_personal_homepage_editdata_forms implements ActionListener {
	private String number;
	private JFrame jframe;
	private Properties prop = null;
	
	public C_personal_homepage_editdata_forms(String number, JFrame jframe) {
		this.number = number;
		this.jframe = jframe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		//1.创建JFrame顶级窗体
		JFrame jf = new JFrame("编辑资料");
		jf.setBounds(660, 240, 600, 580);		//分辨率1920 × 1080的中间位置
		jf.setResizable(false); 				//禁止修改窗体大小
		jf.setVisible(true);					//设置窗体可见
		jf.setLayout(new BorderLayout()); 		//设置Jframe窗体为流式布局
		//更换窗体图标
		ImageIcon logo = new ImageIcon("images/Lemon.jpg");		
		jf.setIconImage(logo.getImage());
		
		
		
		//2.使用Jpanel面板布局
		//2.1创建一个JPanel面板
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);										//不使用Panel默认的布局，使用下方自定义布局
		jpanel.setPreferredSize(new Dimension(600, 580));			//设置面板尺寸
		jpanel.setBackground(new Color(100, 255, 250));	 				//设置字体为天蓝色
		
		
		//2.2创建昵称JLabel和JTextField组件
		JLabel id = new JLabel("昵称", JLabel.LEFT);
		id.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		id.setBounds(50, 30, 40, 40);
		jpanel.add(id);
		//创建昵称文本框组件
		JTextField Id = new JTextField();
		Id.setFont(new Font("黑体", Font.PLAIN, 18));
		Id.setBounds(100, 32, 270, 40);
		Id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {					//当键盘被释放是调用
				String arr = Id.getText();
				if(arr.length() >= 10) {
					Id.setText(String.copyValueOf(arr.toCharArray(), 0, 10));
					Id.setEditable(false);
				if(arr.length() == 10 && e.getKeyCode() == 8) {
					Id.setEditable(true);
					Id.setText(String.copyValueOf(arr.toCharArray(), 0, 9));
				}	
				}
			}
		});

		jpanel.add(Id);
		
		//创建提示标签——（字数不可超过于10）
		JLabel ID = new JLabel("（字数不可超过于10）", JLabel.LEFT);
		ID.setForeground(new Color(255, 140, 200));					//设置字体的颜色：粉色
		ID.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		ID.setBounds(380, 32, 250, 40);
		jpanel.add(ID);
		
		
		//2.3创建年龄、生日JLabel和JComboBox下拉框选项组件
		JLabel birthday = new JLabel("年龄          生日       月       日", JLabel.LEFT);
		birthday.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		birthday.setBounds(60, 100, 400, 40);
		jpanel.add(birthday);
		
		//创建年龄文本框
		JTextField age = new JTextField();
		age.setFont(new Font("黑体", Font.PLAIN, 18));
		age.setBounds(50, 0, 60, 40);
		age.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {					//当键盘被释放是调用
				String arr = age.getText();
				if((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || 
						(e.getKeyCode() >= 96 && e.getKeyCode() <= 105) || e.getKeyCode() == 8) {
					age.setEditable(true);
					if(arr.length() >= 4) {
						age.setText(String.copyValueOf(arr.toCharArray(), 0, 4));
						age.setEditable(false);
						if(arr.length() == 4 && e.getKeyCode() == 8) {
							age.setEditable(true);
							age.setText(String.copyValueOf(arr.toCharArray(), 0, 3));
					}	
				}
				}else {
					age.setEditable(false);
				}
			}
		});

		birthday.add(age);
		
		//创建生日的月份下拉框
		JComboBox<String> month = new JComboBox<String>();
		for(int i = 1; i <= 12; i ++) {
			month.addItem(String.valueOf(i));
		}	
		month.setEditable(false); 										//设置下拉框不可编辑
		month.setBounds(168, 0, 50, 40);
		month.setFont(new Font("黑体", Font.PLAIN, 20));					//设置字体规格
		month.setBackground(Color.white);								//设置边框颜色：白色

		birthday.add(month);
		
		//创建生日的日份下拉框
		JComboBox<String> day = new JComboBox<String>();
		for(int i = 1; i <= 31; i ++) {
			day.addItem(String.valueOf(i));
		}	
		day.setEditable(false); 									//设置下拉框不可编辑
		day.setBounds(251, 0, 50, 40);
		day.setFont(new Font("黑体", Font.PLAIN, 20));					//设置字体规格
		day.setBackground(Color.white);								//设置边框颜色：白色
		
		//给month添加监听事件：重新为生日的日份下拉框添加Items
		month.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				day.removeAllItems();
				int n = 31;
				int x = Integer.valueOf((String)month.getSelectedItem());		//将月份下拉框选择的字符串转换成整数
				if(x == 1 || x == 3 || x == 5 || x == 7 || x == 8 || x == 10 || x == 12)
					n = 31;
				else if(x == 4 || x == 6 || x == 9 || x == 11)
					n = 30;
				else if(x == 2)
					n = 29;
				for(int i = 1; i <= n; i ++) {
					day.addItem(String.valueOf(i));
				}	
			}
		});

		birthday.add(day);


		//2.5创建性别JRadioButton单选按钮组件
		JRadioButton MaleButton = new JRadioButton("男");
		JRadioButton FemaleButton = new JRadioButton("女");
		MaleButton.setFont(new Font("黑体", Font.PLAIN, 18));
		FemaleButton.setFont(new Font("黑体", Font.PLAIN, 18));
		MaleButton.setBounds(430, 100, 50, 40);
		FemaleButton.setBounds(500, 100, 50, 40);
		MaleButton.setOpaque(false);								//设置单选按钮透明
		FemaleButton.setOpaque(false);
		ButtonGroup group = new ButtonGroup();						//要JRadioButton按钮之间的互斥，需要使用ButtonGroup类。	
		group.add(MaleButton);
		group.add(FemaleButton);
		jpanel.add(MaleButton);
		jpanel.add(FemaleButton);

		//3创建个性签名标签组件
		//3.1创建标签组件
		JLabel selfhood = new JLabel("个性签名", JLabel.LEFT);
		selfhood.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		selfhood.setBounds(50, 170, 100, 40);
		jpanel.add(selfhood);
		
		//3.2创建个性签名的文本域
		JTextArea signature = new JTextArea();
		signature.setFont(new Font("黑体", Font.PLAIN, 18));
		signature.setBounds(50, 220, 500, 30);
		
		signature.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {					//当键盘被释放是调用
				String arr = signature.getText();
				if(arr.length() >= 12) {
					signature.setText(String.copyValueOf(arr.toCharArray(), 0, 12));
					signature.setEditable(false);
				if(arr.length() == 12 && e.getKeyCode() == 8) {
					signature.setEditable(true);
					signature.setText(String.copyValueOf(arr.toCharArray(), 0, 11));
				}	
				}
			}
		});

		jpanel.add(signature);
		
		//3.3创建提示个性标签——（字数不可超过于12）
		JLabel tip = new JLabel("（字数不可超过于12）", JLabel.LEFT);
		tip.setBounds(320, 0, 200, 30);
		tip.setForeground(new Color(220, 220, 220));					//设置字体的颜色：粉色
		tip.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		signature.add(tip);
		
		
		
		
		//4创建随心贴标签组件
		//4.1创建标签
		JLabel freedom = new JLabel("随心贴", JLabel.LEFT);
		freedom.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		freedom.setBounds(50, 280, 100, 40);
		jpanel.add(freedom);
		
		//4.2创建随心贴的文本域
		JTextArea freewrite = new JTextArea();
		freewrite.setFont(new Font("黑体", Font.PLAIN, 18));
		freewrite.setBounds(50, 330, 500, 110);
		
		freewrite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {					//当键盘被释放是调用
				String arr = freewrite.getText();
				if(arr.length() >= 48) {
					freewrite.setText(String.copyValueOf(arr.toCharArray(), 0, 48));
					freewrite.setEditable(false);
				if(arr.length() == 48 && e.getKeyCode() == 8) {
					freewrite.setEditable(true);
					freewrite.setText(String.copyValueOf(arr.toCharArray(), 0, 47));
				}	
				}
			}
		});

		jpanel.add(freewrite);
		
		//4.3创建提示个性标签——（字数不可超过于48）
		JLabel reminder = new JLabel("（字数不可超过于48）", JLabel.LEFT);
		reminder.setBounds(320, 80, 200, 30);
		reminder.setForeground(new Color(220, 220, 220));					//设置字体的颜色：粉色
		reminder.setFont(new Font("黑体", Font.PLAIN, 18));					//设置字体规格
		freewrite.add(reminder);
		
		
		
		//5加入窗体取消和确定按钮
		//5.1设置取消按钮
		JButton btn1 = new JButton("取消");
		btn1.setBounds(0, 495, 300, 50);
		btn1.setForeground(Color.white); 							//设置字体颜色：白色
		btn1.setBackground(Color.gray);								//设置背景颜色：灰色
		btn1.setFont(new Font("黑体", Font.BOLD, 20));	 			//设置字体
		btn1.setOpaque(false); 										//设置按钮透明
		//设置按钮不要边框
		btn1.setFocusPainted(false);
		btn1.setBorderPainted(false);
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn1.setForeground(Color.black); 							//设置字体颜色：黑色
				btn1.setOpaque(true); 
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn1.setForeground(Color.white); 							//设置字体颜色：白色
				btn1.setOpaque(false); 	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				jf.dispose();
			}
			
		});
		
		//5.2设置确定按钮
		JButton btn2 = new JButton("确定");
		btn2.setBounds(300, 495, 300, 50);
		btn2.setForeground(Color.white); 							//设置字体颜色：白色
		btn2.setBackground(Color.gray);								//设置背景颜色：灰色
		btn2.setFont(new Font("黑体", Font.BOLD, 20));	 			//设置字体
		btn2.setOpaque(false); 										//设置按钮透明
		//设置按钮不要边框
		btn2.setFocusPainted(false);
		btn2.setBorderPainted(false);
		btn2.addMouseListener(new MouseAdapter() {							//为“确定”按钮设置监听器
			@Override
			public void mouseEntered(MouseEvent e) {
				btn2.setForeground(Color.black); 							//设置字体颜色：黑色
				btn2.setOpaque(true); 
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn2.setForeground(Color.white); 							//设置字体颜色：白色
				btn2.setOpaque(false); 	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//0.创建一个Properties容器来保持要修改的值
				Properties container = getEditMessage(Id, age, month, day, MaleButton, FemaleButton, signature, freewrite);
				//调用方法获取修改好的资料信息
				
				
				jf.dispose();												//关闭编辑资料窗口
				System.out.println("container" + container);
				//调用changeMessage(Properties prop)实现拷贝数据功能
				try {
					changeMessage(container);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//关闭窗口后再次打开，实现数据更新
				jframe.dispose();										
				SwingUtilities.invokeLater(() -> A_personal_homepage_entrance.homepageDesign(number));
			}
			
		});
		
		jpanel.add(btn1);
		jpanel.add(btn2);
		
		//6将面板组件jpanel加入到顶级容器jf中
		jf.add(jpanel, BorderLayout.CENTER);	
		
	}
	
	
	

	/**
	 * 自定义方法区
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	
	//修改用户个人资料文件夹信息
	public void changeMessage(Properties container) throws FileNotFoundException, IOException {
		
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
		prop = new Properties();
		
		//2.从流中读取文件内容（先获取源文件资料信息）
			prop.load(new FileReader(AbsolutePath));

		
		//3.使用迭代器Iterator获取键值
		Set<String> stringPropertyNames = container.stringPropertyNames();			//需要用一个set容器封装Key值
		Iterator<String> it = stringPropertyNames.iterator();					
		
		//4.开始更新数据
		while(it.hasNext()) {													//开始匹配、更换
			String key = it.next();
			if(container.getProperty(key) != " ") {
				prop.setProperty(key, container.getProperty(key));
			}
		}
	
		
		//3.将集合中的数据通过输出流写出到文件中
			prop.store(new FileWriter(AbsolutePath), "information");		//第二个参数是一个描述信息

	}	
	
	
	
	protected Properties getEditMessage(JTextField id, JTextField age, JComboBox<String> month, JComboBox<String> day,
			 JRadioButton maleButton, JRadioButton femaleButton, JTextArea signature,JTextArea freewrite) {
	
		Properties container = new Properties();
		//1.将昵称与其对应的字符串保存到容器中
		String str = " ";
		if(id.getText().length() > 0)
			str = id.getText();
		System.out.println("str = " + str);
		container.setProperty("昵称", str);		
		
		
		//2.将年龄与其对应的字符串保存到容器中
		str = " ";
		if(age.getText().length() > 0)
			str = age.getText();
		container.setProperty("年龄", str);	
		
		
		//3.将出生月份与其对应的字符串保存到容器中
		str = " ";
		if(((String)month.getSelectedItem()).length() > 0)
			str = (String)month.getSelectedItem();
		container.setProperty("出生月份", str);	
		
		
		//4.将出生日与其对应的字符串保存到容器中
		str = " ";
		if(((String)day.getSelectedItem()).length() > 0)
			str = (String)day.getSelectedItem();
		container.setProperty("出生日", str);	
		
		
		//2.4根据生日自动添加星座，将星座与其对应的字符串保存到容器中
			int m = Integer.valueOf((String)month.getSelectedItem());
			int d = Integer.valueOf((String)day.getSelectedItem());
			String string = null;
			if((m == 1 && d >= 21) || (m == 2 && d <= 19)) {
				string = "水瓶座";
			} else if((m == 2 && d >= 20) || (m == 3 && d <= 20)) {
				string = "双鱼座";
			} else if((m == 3 && d >= 21) || (m == 4 && d <= 20)) {
				string = "白羊座";
			} else if((m == 4 && d >= 21) || (m == 5 && d <= 21)) {
				string = "金牛座";
			} else if((m == 5 && d >= 22) || (m == 6 && d <= 21)) {
				string = "双子座";
			} else if((m == 6 && d >= 22) || (m == 7 && d <= 22)) {
				string = "巨蟹座";
			} else if((m == 7 && d >= 23) || (m == 8 && d <= 23)) {
				string = "狮子座";
			} else if((m == 8 && d >= 24) || (m == 9 && d <= 23)) {
				string = "处女座";
			} else if((m == 9 && d >= 24) || (m == 10 && d <= 23)) {
				string = "天秤座";
			} else if((m == 10 && d >= 24) || (m == 11 && d <= 22)) {
				string = "天蝎座";
			} else if((m == 11 && d >= 23) || (m == 12 && d <= 21)) {
				string = "射手座";
			} else if((m == 12 && d >= 22) || (m == 1 && d <= 20)) {
				string = "摩羯座";
			}
		str = " ";
		if(string.length() > 0)
			str = string;
		container.setProperty("星座", str);		
		
		
		//6.将性别与其对应的字符串保存到容器中	
		str = " ";
		if(maleButton.isSelected()) {
			str = "男";
		} else if(femaleButton.isSelected()) {
			str = "女";
		}
		container.setProperty("性别", str);	

		
		//7.将个性签名与其对应的字符串保存到容器中
		str = " ";
		if(signature.getText().length() > 0)
			str = signature.getText();
		container.setProperty("个性签名", str);	
		
		
		//8.将随心贴与其对应的字符串保存到容器中
		str = " ";
		if(freewrite.getText().length() > 0)
			str = freewrite.getText();
		container.setProperty("随心贴", str);	
		
		return container;
	}

	
}









