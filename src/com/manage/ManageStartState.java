package com.manage;

import java.util.HashMap;

/**
 * 管理初始状态类
 * @author PowerfulSong
 */
public class ManageStartState {
	private static HashMap hm=new HashMap<String, Integer>();
	
	/**
	 * 将初始状态值加入到HashMap中
	 * @param name 数据名
	 * @param value 数据值
	 */
	public static void addStartState(String name, Integer value){
		hm.put(name, value);
	}
	
	/**
	 * 根据数据名得到数据值
	 * @param name 数据名
	 * @return 数据值
	 */
	public static int getStartState(String name){
		return (Integer)hm.get(name);
	}
}

