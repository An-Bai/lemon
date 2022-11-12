package com.lemon.loginchat_interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class Universal_E_chat_layout_function_options {
	public static void createAndShowGUI(JFrame jframe, JButton button, ActionEvent e, String number) {
		//1.创建弹出式菜单对象
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBackground(Color.white);
		
		//2.创建菜单项
		JMenuItem item1 = setItem("上传文件");					//上传文件

		JMenuItem item2 = setItem("下载文件");					//下载文件
		
		JMenuItem item3 = setItem("更换字体颜色");				//更换字体颜色

		JMenuItem item4 = setItem("清空历史记录");				//清空历史记录
		
		//3.给Item4添加监听器
		item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					deleteData(number);							//清空聊天记录
					//重写打开窗口
					jframe.dispose();
					SwingUtilities.invokeLater(() -> A_group_chat_entrance.multiplayerChat(number));	//打开窗口					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}							
			}
		});
		
		popupMenu.add(item1);
		popupMenu.add(item2);
		popupMenu.add(item3);
		popupMenu.add(item4);
		
		//5.显示提示
		popupMenu.show(jframe.getComponent(0), 804, 55);		//第一个参数是固定的，后面二个是获取鼠标的当前位置
		

	}
	

	//设置菜单项操作
	public static JMenuItem setItem(String name) {
		JMenuItem item = new JMenuItem(name);
		item.setBackground(new Color(150, 255, 250));
		item.setForeground(new Color(255, 175, 200));
		item.setFont(new Font("宋体", Font.BOLD, 22));
		//设置按钮不要边框
		item.setFocusPainted(false);
		item.setBorderPainted(false);
		
		return item;
	}


	//功能：清空用户聊天记录
	public static void deleteData(String number) throws IOException {

			String path = "Users information\\" + number + "\\聊天记录\\Chat datas";
			
			File file = new File(path);
			
			file.delete();

			Path filepath = Paths.get(path);
			
			Files.createFile(filepath);					//如果文件已经创建了，会报错
		
	}
	
	
	


}






