package com.manage;

import java.util.HashMap;
import javax.swing.*;

public class ManageJLabel {
	private static HashMap hm=new HashMap<String,JLabel>();

	/**
	 * 添加JLabel到管理类中
	 * @param jlabelName label名
	 * @param jlabel 
	 */
	public static void addJLabel(String jlabelName,JLabel jlabel){
		hm.put(jlabelName,jlabel);
	}

	/**
	 * 通过label名获取label
	 * @param jlabelName
	 * @return
	 */
	public static JLabel getJLabel(String jlabelName){
		return (JLabel)hm.get(jlabelName);
	}
}
