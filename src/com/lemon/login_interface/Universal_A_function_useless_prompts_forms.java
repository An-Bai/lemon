package com.lemon.login_interface;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Universal_A_function_useless_prompts_forms {
	public static void createAndShowGUI(JFrame jFrame) {
		//1.创建JDoalog对话框
		JDialog jd = new JDialog(jFrame, "至亲爱的Lemon体验者", true);	
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		jd.setSize(560, 420);
		jd.setLocation(700, 370);
		jd.setResizable(false); 				//禁止修改窗体大小
		
		//2.更换窗体图标
		ImageIcon logo = new ImageIcon("images/Lemon.jpg");		
		jd.setIconImage(logo.getImage());
		
		
		//3.创建JPanel面板
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);													//不使用JPanel默认的布局，使用下方自定义布局
		jPanel.setPreferredSize(new Dimension(560, 420));						//设置面板尺寸
		jPanel.setBackground(Color.white);										//设置面板背景为白色
		jPanel.setBorder(new LineBorder(new Color(255, 242, 0), 10, true));
		
		//4.创建文字标签
		JLabel jLabel1 = new JLabel("本功能正在开发测试中，暂未提供开放", JLabel.CENTER);
		jLabel1.setForeground(new Color(255, 50, 220));							//设置字体的颜色（color中的参数为三原色数值）
		jLabel1.setFont(new Font("宋体", Font.ITALIC + Font.BOLD, 25));			//设置字体规格
		jLabel1.setBounds(0, 110, 560, 50);
		jPanel.add(jLabel1);
		
		JLabel jLabel2 = new JLabel("感谢您的体验!", JLabel.CENTER);
		jLabel2.setForeground(new Color(255, 50, 220));
		jLabel2.setFont(new Font("宋体", Font.ITALIC + Font.BOLD, 25));
		jLabel2.setBounds(0, 210, 560, 50);
		jPanel.add(jLabel2);
		
		jd.add(jPanel);
		jd.setVisible(true);
	}
}











