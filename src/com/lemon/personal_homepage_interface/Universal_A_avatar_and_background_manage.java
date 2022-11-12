package com.lemon.personal_homepage_interface;

import java.io.File;
import java.io.IOException;

public class Universal_A_avatar_and_background_manage {
	
	//1.头像管理
	public static String Avatar(String number){
		//获取该客户的个人头像图片地址
		File directory = new File(".");						//此new对象用于获取当前路径
		String path = null;
		try {
			path = directory.getCanonicalPath();			//获取标准的路径——上一级文件夹路径；而getAbsolutePath()获取的是本级路径。
		} catch (IOException e) {							//getCanonicalPath()需要catch...try，而getAbsolutePath()不需要
			e.printStackTrace();
			
		}
		
		String paths = path + "\\Users information\\" + number + "\\图片管理\\头像.jpg";
		
		return paths;
	}
	
	//2.主页背景图管理
	public static String backGround(String number){
		//获取该客户的个人头像图片地址
		File directory = new File(".");						//此new对象用于获取当前路径
		String path = null;
		try {
			path = directory.getCanonicalPath();			//获取标准的路径——上一级文件夹路径；而getAbsolutePath()获取的是本级路径。
		} catch (IOException e) {							//getCanonicalPath()需要catch...try，而getAbsolutePath()不需要
			e.printStackTrace();
			
		}
		
		String paths = path + "\\Users information\\" + number + "\\图片管理\\主页背景.jpg";
		
		return paths;
	}
	

}









