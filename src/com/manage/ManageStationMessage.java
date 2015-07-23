package com.manage;

import java.util.HashMap;

/**
 * 管理始发车站信息（目前乘客数目，已发送乘客数目）
 * @author PowerfulSong
 */
public class ManageStationMessage {
	private static HashMap hm=new HashMap<String, Integer>();
	
	/**
	 * 添加始发车站信息
	 * @param name
	 * @param value
	 */
	public static void addStationMessage(String name, Integer value){
		hm.put(name, value);
	}
	
	/**
	 * 获取始发车站信息
	 * @param name
	 * @return
	 */
	public static int getStationMessage(String name){
		return (Integer)hm.get(name);
	}
	
	/**
	 * 判断是否有信息
	 * @return true:有信息  false: 没有信息
	 */
	public boolean hasMessage(){
		boolean b=false;
		if(hm.size()!=0){
			b=true;
		}
		return b;
	}
}
