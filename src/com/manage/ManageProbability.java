package com.manage;

import java.util.HashMap;

/**
 * ���������
 * @author PowerfulSong
 *
 */
public class ManageProbability {
private static HashMap hm=new HashMap<String, Double>();
	
	/**
	 * ������ֵ���뵽HashMap��
	 * @param name ������
	 * @param value ����ֵ
	 */
	public static void addProbability(String name, Double value){
		hm.put(name, value);
	}
	
	/**
	 * �����������õ�����ֵ
	 * @param name ������
	 * @return ����ֵ
	 */
	public static double getProbability(String name){
		return (Double)hm.get(name);
	}
}
