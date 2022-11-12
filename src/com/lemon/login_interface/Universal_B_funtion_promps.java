package com.lemon.login_interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class Universal_B_funtion_promps {
	public static JPopupMenu popupMenu;
	
	public static void createAndShowGUI(JFrame jframe, JButton button, MouseEvent e) {
		//1.创建弹出式菜单对象
		popupMenu = new JPopupMenu();
		popupMenu.setBackground(Color.white);
		popupMenu.setVisible(true);
		
		//2.创建菜单项
		JMenuItem item1 = new JMenuItem(button.getName());
		item1.setBackground(Color.white);
		item1.setForeground(Color.black);
		item1.setFont(new Font("宋体", Font.BOLD, 14));

		item1.setEnabled(false);									//设置菜单项不可被选中
		
		//设置按钮不要边框
		item1.setFocusPainted(false);
		item1.setBorderPainted(false);
		
		//3.将菜单添加到菜单对象中
		popupMenu.add(item1);
		
		//4.设置一个当前时间
		/*
		 * long time = System.currentTimeMillis();
		 * 
		 * for(int i = 0; ; i ++) { if(System.currentTimeMillis() > time + 500)
		 * //让显示有个缓冲时间：0.5秒 break; }
		 */
		//5.显示提示

		if(popupMenu.isVisible())
		popupMenu.show(e.getComponent(), e.getX(), e.getY());		//第一个参数是固定的，后面二个是获取鼠标的当前位置

		button.addMouseListener(new MouseAdapter() {								//添加鼠标监听事件，重新设置此组件的前景颜色：深蓝色
			public void mouseExited(MouseEvent e) {
				if(Universal_B_funtion_promps.popupMenu.isVisible())
				Universal_B_funtion_promps.popupMenu.setVisible(false);					//关闭提示
			}
	});
		
		
	}
}







