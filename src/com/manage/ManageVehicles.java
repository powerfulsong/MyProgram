package com.manage;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;

/**
 * 管理机动车类
 * @author PowerfulSong
 *
 */
public class ManageVehicles {
	private static HashMap hm=new HashMap<String,Vector>();
	
	/**
	 * 把车向量加入管理机动车的类中
	 * @param vehicle 车种类
	 * @param v 车向量
	 */
	public static void addVehicles(String vehicle,Vector v){
		hm.put(vehicle,v);
	}
	
	/**
	 * 
	 * @param vehicle 车牌号
	 * @return 车向量
	 */
	public static Vector getVehicles(String vehicle){
		return (Vector)hm.get(vehicle);
	}
	
}
