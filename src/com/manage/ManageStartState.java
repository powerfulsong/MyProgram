package com.manage;

import java.util.HashMap;

/**
 * �����ʼ״̬��
 * @author PowerfulSong
 */
public class ManageStartState {
	private static HashMap hm=new HashMap<String, Integer>();
	
	/**
	 * ����ʼ״ֵ̬���뵽HashMap��
	 * @param name ������
	 * @param value ����ֵ
	 */
	public static void addStartState(String name, Integer value){
		hm.put(name, value);
	}
	
	/**
	 * �����������õ�����ֵ
	 * @param name ������
	 * @return ����ֵ
	 */
	public static int getStartState(String name){
		return (Integer)hm.get(name);
	}
}

