package com.lemon.login_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Universal_C_account_display_interface {
	public static void accountNmbers(JFrame jFrame) throws IOException {
		//1.创建JDoalog对话框
		JDialog jd = new JDialog(jFrame, "至帅气的Lemon封弊者", false);	
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		jd.setResizable(false); 											//禁止修改窗体大小
		jd.setBounds(510, 260, 900, 500);	 								//分辨率1920 × 1080的中间位置

		
		//2.更换窗体图标
		ImageIcon logo = new ImageIcon("images/Lemon.jpg");		
		jd.setIconImage(logo.getImage());
		
		
		//3.创建JPanel面板
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);													//不使用JPanel默认的布局，使用下方自定义布局
		jPanel.setPreferredSize(new Dimension(900, 500));						//设置面板尺寸
		jPanel.setBackground(new Color(255, 210, 220));							//设置面板背景为白色
		
		
		//4.1获取父级路径path
		File directory = new File(".");						//此new对象用于获取当前路径
		String path = null;
		try {
			path = directory.getCanonicalPath();			//获取标准的路径——上一级文件夹路径；而getAbsolutePath()获取的是本级路径。
		} catch (IOException e) {							//getCanonicalPath()需要catch...try，而getAbsolutePath()不需要
			e.printStackTrace();
		}
		//4.2获取文件的绝对路径
		String AbsolutePath = path + "\\Files\\IDs" ;
		
		//5.创建文本域
		JTextArea area = new JTextArea();
		area.setFont(new Font("黑体", Font.BOLD, 25));
		area.setBounds(20, 20, 860, 460);
		area.setOpaque(false);								//设置背景透明
		area.setEditable(false);							//设置文本域不可编辑				
		jPanel.add(area);
		
		//6创建一个字符流输出对象
		FileReader fr = new FileReader(AbsolutePath);
		
		//创建字符缓冲区进行读写
		char[] arr = new char[1024];
		int i;
		while((i = fr.read(arr)) != -1) {
			area.append(String.valueOf(arr, 0, i));
		}
		
		fr.close();
		
		
		jd.add(jPanel);
		jd.setVisible(true);
	}
	

	
	
}











