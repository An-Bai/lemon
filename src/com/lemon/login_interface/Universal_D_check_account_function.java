package com.lemon.login_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import com.lemon.personal_homepage_interface.A_personal_homepage_entrance;

public class Universal_D_check_account_function implements ActionListener{
	
	private JFrame jf;
	public JComboBox<Object> box;
	public JPasswordField jPasswordField;
	public static Properties prop;
	public String Id;
	public String Password;
	private int n = 0;
	
	public Universal_D_check_account_function(JComboBox<Object> box, JPasswordField jPasswordField, JFrame jf) {
		super();
		this.box = box;
		this.jPasswordField = jPasswordField;
		this.jf = jf;
		
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//1.获取用户输入的账号和密码
		Id = (String) box.getSelectedItem();
		Password = new String(jPasswordField.getPassword());	
		
		//2.模拟数据库查询
		Set<String> stringPropertyNames = prop.stringPropertyNames();			//需要用一个set容器封装Key值
		Iterator<String> it = stringPropertyNames.iterator();					//使用迭代器Iterator
		
		boolean flag = false;
		if(Id == null) {

		}else if(Id != null && jPasswordField.getPassword().length == 0) {
			n ++;
			if(n >= 5) {
				JOptionPane.showMessageDialog(null, "            哎呀，小笨蛋你是不是忘记密码了呀" + "\n" 			//11个空格
					+ "      没办法，告诉你啦，谁让小柠檬这么可爱呢" + "\n" + "亲爱滴封测者，柠可以点击注册账号查看账号信息哦");		//6个空格
			}else {
				JOptionPane.showMessageDialog(null, "密码不能为空哟，请输入您的密码" );
			}
			
		}else if(Id != null && jPasswordField.getPassword().length > 0){
			while(it.hasNext()) {													//开始匹配
				String key = it.next();
				if(Id.equals(key) && Password.equals(prop.getProperty(key)) 		//判断账号密码及在线状态是否均符合
						&& (prop.getProperty(key + "在线状态")).equals("false")) {		
					//1.成功匹配
					flag = true;
					prop.setProperty(key + "在线状态", "true");						//将在线状态改为“true”
					try {
						prop.store(new FileWriter("Files/IdAndPassword"), "password");//模拟将修改的数据同步到数据库
					} catch (IOException e1) {										
						e1.printStackTrace();
					}
					n = 0;
					break;	
				}
			}
				if(flag) {
					//1.成功
					jf.dispose();														//关闭登录界面
					//开启一个新线程——打开用户主页
					SwingUtilities.invokeLater(() -> A_personal_homepage_entrance.homepageDesign(Id));
					
				}else if(!flag) {
				//2.失败，错误提示
					n ++;
					if(Id.equals(Id) && Password.equals(prop.getProperty(Id)) 		
							&& (prop.getProperty(Id + "在线状态")).equals("true")) {
						JOptionPane.showMessageDialog(null, "此账号已经登录了哟，小柠檬建议换一个内测账号试试哦");
					}else if(n >= 5) {
						JOptionPane.showMessageDialog(null, "            哎呀，小笨蛋你是不是忘记密码了呀" + "\n" 			//11个空格
								+ "      没办法，告诉你啦，谁让小柠檬这么可爱呢" + "\n" + "亲爱滴封测者，柠可以点击注册账号查看账号信息哦");		//6个空格
						}
					else {
						JOptionPane.showMessageDialog(null, "您的数据有误，请检查您输入的账号或密码是否正确" );
					}
				
			}
		}
	}
	
}







