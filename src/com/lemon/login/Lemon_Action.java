package com.lemon.login;

import javax.swing.SwingUtilities;

import com.lemon.login_interface.A_login_entrance;

//创建一个Runnable接口的实现类,同时重写接口中的run()方法;
class myRunnzble implements Runnable {
	@Override
	public void run() {
		SwingUtilities.invokeLater(() -> A_login_entrance.interfacialDesign());

	}
}

public class Lemon_Action {
	public static void main(String[] args) {
		//创建Runnable接口的实现类对象;
		myRunnzble runnzble = new myRunnzble();
		
		//使用Thread (Runnable target, String name)构造方法创建线程对象
		Thread thread = new Thread(runnzble, "Lemon");
		
		//调用线程实例的start()方法启动线程。
		thread.start();
	}
}
