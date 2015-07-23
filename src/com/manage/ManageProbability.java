package com.manage;

import java.util.HashMap;

/**
 * 管理概率类
 * @author PowerfulSong
 *
 */
public class ManageProbability {
private static HashMap hm=new HashMap<String, Double>();
	
	/**
	 * 将概率值加入到HashMap中
	 * @param name 数据名
	 * @param value 数据值
	 */
	public static void addProbability(String name, Double value){
		hm.put(name, value);
	}
	
	/**
	 * 根据数据名得到概率值
	 * @param name 数据名
	 * @return 数据值
	 */
	public static double getProbability(String name){
		return (Double)hm.get(name);
	}
}
