package com.manage;

import java.util.HashMap;

/**
 * 管理线程的类
 * @author song
 *
 */
public class ManageThread {
	private static HashMap hm=new HashMap<String,Thread>();
	
	/**
	 * 把创建好的额线程加入到HashMap中
	 * @param threadName 线程名
	 * @param thread 线程
	 */
	public static void addThread(String threadName,Thread thread){
		hm.put(threadName,thread);
	}
	
	/**
	 * 通过线程名获得该线程
	 * @param threadName 线程名
	 * @return 线程
	 */
	public static Thread getThread(String threadName){
		return (Thread)hm.get(threadName);
	}
	
	/**
	 * 通过线程名将线程从管理类中移出
	 * @param threadName 线程名
	 */
	public static void removeThread(String threadName){
		hm.remove(threadName);
	}
}
