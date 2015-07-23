package com.manage;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;

/**
 * �����������
 * @author PowerfulSong
 *
 */
public class ManageVehicles {
	private static HashMap hm=new HashMap<String,Vector>();
	
	/**
	 * �ѳ�����������������������
	 * @param vehicle ������
	 * @param v ������
	 */
	public static void addVehicles(String vehicle,Vector v){
		hm.put(vehicle,v);
	}
	
	/**
	 * 
	 * @param vehicle ���ƺ�
	 * @return ������
	 */
	public static Vector getVehicles(String vehicle){
		return (Vector)hm.get(vehicle);
	}
	
}
