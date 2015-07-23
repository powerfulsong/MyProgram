package com.manage;

import java.util.HashMap;
import javax.swing.*;

public class ManageJLabel {
	private static HashMap hm=new HashMap<String,JLabel>();

	/**
	 * ���JLabel����������
	 * @param jlabelName label��
	 * @param jlabel 
	 */
	public static void addJLabel(String jlabelName,JLabel jlabel){
		hm.put(jlabelName,jlabel);
	}

	/**
	 * ͨ��label����ȡlabel
	 * @param jlabelName
	 * @return
	 */
	public static JLabel getJLabel(String jlabelName){
		return (JLabel)hm.get(jlabelName);
	}
}
