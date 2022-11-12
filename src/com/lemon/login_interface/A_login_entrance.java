package com.lemon.login_interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class A_login_entrance {

	public static void interfacialDesign() {
		//1.创建JFrame顶级窗体
		JFrame jf = new JFrame("Lemon");
		jf.setBounds(682, 343, 600, 450);		//分辨率1920 × 1080的中间位置
		jf.setResizable(false); 				//禁止修改窗体大小
		jf.setUndecorated(true);				//设置窗口边框不显示
		jf.setVisible(true);					//设置窗体可见
		
		jf.setLayout(new BorderLayout()); 		//设置Jframe窗体为流式布局

		
		//2.使用Jpanel面板布局
		
		//顶部
		JPanel topPanel = B_login_layout_package.createTopPanel(jf);
		jf.add(topPanel, BorderLayout.PAGE_START);
				
		//左侧
		JPanel LeftPanel = B_login_layout_package.createLeftPanel(jf);
		jf.add(LeftPanel, BorderLayout.LINE_START);
				
		//中间
		JPanel CenterPanel = null;
		CenterPanel = B_login_layout_package.createCenterPanel(jf);
		jf.add(CenterPanel, BorderLayout.CENTER);	
				
		//右侧
		JPanel RightPanel = B_login_layout_package.createRightPanel(jf);
		jf.add(RightPanel, BorderLayout.LINE_END);
				
		//底部
		JPanel BottomPanel = B_login_layout_package.createBottomPanel(jf);
		jf.add(BottomPanel, BorderLayout.PAGE_END);
	}

}
