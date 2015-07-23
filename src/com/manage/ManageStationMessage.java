package com.manage;

import java.util.HashMap;

/**
 * ����ʼ����վ��Ϣ��Ŀǰ�˿���Ŀ���ѷ��ͳ˿���Ŀ��
 * @author PowerfulSong
 */
public class ManageStationMessage {
	private static HashMap hm=new HashMap<String, Integer>();
	
	/**
	 * ���ʼ����վ��Ϣ
	 * @param name
	 * @param value
	 */
	public static void addStationMessage(String name, Integer value){
		hm.put(name, value);
	}
	
	/**
	 * ��ȡʼ����վ��Ϣ
	 * @param name
	 * @return
	 */
	public static int getStationMessage(String name){
		return (Integer)hm.get(name);
	}
	
	/**
	 * �ж��Ƿ�����Ϣ
	 * @return true:����Ϣ  false: û����Ϣ
	 */
	public boolean hasMessage(){
		boolean b=false;
		if(hm.size()!=0){
			b=true;
		}
		return b;
	}
}
