package com.manage;

import java.util.HashMap;

/**
 * �����̵߳���
 * @author song
 *
 */
public class ManageThread {
	private static HashMap hm=new HashMap<String,Thread>();
	
	/**
	 * �Ѵ����õĶ��̼߳��뵽HashMap��
	 * @param threadName �߳���
	 * @param thread �߳�
	 */
	public static void addThread(String threadName,Thread thread){
		hm.put(threadName,thread);
	}
	
	/**
	 * ͨ���߳�����ø��߳�
	 * @param threadName �߳���
	 * @return �߳�
	 */
	public static Thread getThread(String threadName){
		return (Thread)hm.get(threadName);
	}
	
	/**
	 * ͨ���߳������̴߳ӹ��������Ƴ�
	 * @param threadName �߳���
	 */
	public static void removeThread(String threadName){
		hm.remove(threadName);
	}
}
