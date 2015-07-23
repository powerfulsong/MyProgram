package com.common;

import java.util.Vector;

import javax.swing.JLabel;

import com.tools.MyNumbers;
import com.manage.*;

public class Time implements Runnable{
	//������
	ManageJLabel manageJLabel;
	ManageStartState manageStartState;
	ManageThread manageThread;
	ManageVehicles manageVehicles;
	ManageStationMessage manageStationMessage;

	private int current_time=MyNumbers.time_start;
	int current_time2;
	int hour,minute;

	//��ʾʱ��
	JLabel jl_showTime;

	//��ȡ��ʼ״̬�¸�������Ŀ��ÿ���ӵ��������
	int XN_volvoNums,XN_ivecoNums,BJ_volvoNums,BJ_ivecoNums,PN;

	//�������������ڴ�ų�����
	Vector<IVECO> XB_ivecos=new Vector<IVECO>();	
	Vector<IVECO> BX_ivecos=new Vector<IVECO>();
	Vector<VOLVO> XB_volvos=new Vector<VOLVO>();
	Vector<VOLVO> BX_volvos=new Vector<VOLVO>();
	
	//������վ���������ڴ洢��վ�Ĳ�ͬ��
	Vector<IVECO> XNStation_ivecos=new Vector<IVECO>();
	Vector<IVECO> BJStation_ivecos=new Vector<IVECO>();
	Vector<VOLVO> XNStation_volvos=new Vector<VOLVO>();
	Vector<VOLVO> BJStation_volvos=new Vector<VOLVO>();

	/**
	 * �����
	 */
	String vehiclesNo;
	
	/**
	 * ������վ��ǰ�˿�����
	 */
	public static int XNStation_passengerNum=0;
	/**
	 * ������վ��ǰ�˿�����
	 */
	public static int BJStation_passengerNum=0;
	
	/**
	 * ������վ�Ѿ����ͳ˿���Ŀ
	 */
	public static int XNStation_hasSent=0;
	/**
	 * ������վ�Ѿ����ͳ˿���Ŀ
	 */
	public static int BJStation_hasSent=0;
	
	/**
	 * ����վ��ǰ���ͳ˿���Ŀ
	 */
	int XNStation_currentSentNum=0;
	/**
	 * ����վ��ǰ���ͳ˿���Ŀ
	 */
	int BJStation_currentSentNum=0;

	public Time() {
		// TODO Auto-generated constructor stub
		//����JLabel���������
		manageJLabel=new ManageJLabel();
		jl_showTime=manageJLabel.getJLabel("Time");

		//������ʼ״̬���������
		manageStartState=new ManageStartState();

		//�ѳ��������뵽���������У�������MyPanel��ʱ������ȡ��
		manageVehicles=new ManageVehicles();
		manageVehicles.addVehicles("XB_ivecos", XB_ivecos);
		manageVehicles.addVehicles("BX_ivecos", BX_ivecos);
		manageVehicles.addVehicles("XB_volvos", XB_volvos);
		manageVehicles.addVehicles("BX_volvos", BX_volvos);
		manageVehicles.addVehicles("XNStation_ivecos", XNStation_ivecos);
		manageVehicles.addVehicles("BJStation_ivecos", BJStation_ivecos);
		manageVehicles.addVehicles("XNStation_volvos", XNStation_volvos);
		manageVehicles.addVehicles("BJStation_volvos", BJStation_volvos);
		
		//�����̹߳��������
		manageThread=new ManageThread();
		
		//��������ʼ����վ��Ϣ
		manageStationMessage=new ManageStationMessage();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		current_time2=current_time;
		
		try {
			while(current_time<MyNumbers.time_end){
				hour=current_time/60;
				minute=current_time%60;		
				Thread.sleep(MyNumbers.milliSeconds);  //����һ��

				//��ʾʱ��
				if(minute>=0 && minute<=9){
					jl_showTime.setText("��ǰʱ��Ϊ��"+hour+" : 0"+minute);
				}else{
					jl_showTime.setText("��ǰʱ��Ϊ��"+hour+" : "+minute);
				}

				//���ɳ˿�
				PN=manageStartState.getStartState("PN");
				XNStation_passengerNum+=(int)((Math.random()*1000)%(PN+1));
				BJStation_passengerNum+=(int)((Math.random()*1000)%(PN+1));
				//System.out.println("����վ�˿���Ϊ��"+XNStation_passengerNum);
				//System.out.println("����վ�˿���Ϊ��"+BJStation_passengerNUM);
				
				//ʱ���ʼ���������������󣬲������̼߳��뵽�̹߳������У��̲߳�����������Ҫʱ��������
				if(current_time==MyNumbers.time_start){
					//�ӳ�ʼ״̬�������л�ȡ����ʼ״̬��Ϣ
					XN_ivecoNums=manageStartState.getStartState("XN_ivecoNums");
					BJ_ivecoNums=manageStartState.getStartState("BJ_ivecoNums");
					XN_volvoNums=manageStartState.getStartState("XN_volvoNums");
					BJ_volvoNums=manageStartState.getStartState("BJ_volvoNums");
					
					//��������->��������ά��
					for(int i=0;i<XN_ivecoNums;i++){
						//����->������ά�µĳ�ʼλ��
						IVECO xb_iveco=new IVECO(776, 57); //��������
						xb_iveco.setDirect(1); //�趨���ķ���
						xb_iveco.setSpeed(MyNumbers.ivecoSpeed);
						vehiclesNo="Xi_"+(i+1)+"";
						xb_iveco.setNo(vehiclesNo); //�趨���ı��
						xb_iveco.setPassengersNum(0); //��ʼ������˿���Ϊ0
						XB_ivecos.add(xb_iveco);
						XNStation_ivecos.add(xb_iveco);

						Thread t_xb_iveco=new Thread(xb_iveco);
						//���̼߳��뵽�̹߳�������
						manageThread.addThread(vehiclesNo, t_xb_iveco);
					}

					//��������->��������ά��
					for(int j=0;j<BJ_ivecoNums;j++){
						//����һ������-->������iveco����
						IVECO bx_iveco=new IVECO(20, 192);  //��������
						bx_iveco.setDirect(0); //�趨���ķ���
						bx_iveco.setSpeed(MyNumbers.ivecoSpeed);
						vehiclesNo="Bi_"+(j+1)+"";
						bx_iveco.setNo(vehiclesNo);
						bx_iveco.setPassengersNum(0);
						//���뵽������iveco����������
						BX_ivecos.add(bx_iveco);
						BJStation_ivecos.add(bx_iveco);

						//�����߳�
						Thread t_bx_iveco=new Thread(bx_iveco);
						//���̼߳��뵽�̹߳�������
						manageThread.addThread(vehiclesNo, t_bx_iveco);
					}

					//��������->����volvo
					for(int m=0; m<XN_volvoNums; m++){
						//����->�����ֶ��ֵĳ�ʼλ��
						VOLVO xb_volvo=new VOLVO(776, 117); //��������
						xb_volvo.setDirect(1); //�趨���ķ���
						xb_volvo.setSpeed(MyNumbers.volvoSpeed);
						vehiclesNo="Xv_"+(m+1)+"";
						xb_volvo.setNo(vehiclesNo);
						xb_volvo.setPassengersNum(0);
						XB_volvos.add(xb_volvo);
						XNStation_volvos.add(xb_volvo);

						Thread t_xb_volvo=new Thread(xb_volvo);
						manageThread.addThread(vehiclesNo, t_xb_volvo);

					}


					//��������->����volvo
					for(int n=0; n<BJ_volvoNums; n++){
						//����->�����ֶ��ֵĳ�ʼλ��
						VOLVO bx_volvo=new VOLVO(10, 252); //��������
						bx_volvo.setDirect(0); //�趨���ķ���
						bx_volvo.setSpeed(MyNumbers.volvoSpeed);
						vehiclesNo="Bv_"+(n+1)+"";
						bx_volvo.setNo(vehiclesNo);
						bx_volvo.setPassengersNum(0);
						BX_volvos.add(bx_volvo);
						BJStation_volvos.add(bx_volvo);


						Thread t_bx_volvo=new Thread(bx_volvo);
						manageThread.addThread(vehiclesNo, t_bx_volvo);
					}
				}

				
				//���ʱ����iveco������ʱ�䷶Χ(8:00am - 6:00pm)�ڣ���ÿ20����һ�࣬������ά���߳�
				if((current_time>=MyNumbers.time_first_iveco)&&(current_time<=MyNumbers.time_last_iveco)&&((current_time-MyNumbers.time_first_iveco)%MyNumbers.iveco_minutes==0))
				{
					//System.out.println("��ǰʱ��Ϊ��"+hour+" : "+minute);
					
					if(XNStation_ivecos.size()!=0){
						//��˳˿�
						if(XNStation_passengerNum >= MyNumbers.ivecoSeats){  //��ǰ��վ�˿���������iveco������ؿ���
							XNStation_currentSentNum=MyNumbers.ivecoSeats;
						}else{
							XNStation_currentSentNum=XNStation_passengerNum;
						}
						XNStation_passengerNum-=XNStation_currentSentNum;  //��ǰ��վ����
						XNStation_hasSent+=XNStation_currentSentNum; //��ǰ�����ÿ���
						//�趨��ǰiveco��������
						XNStation_ivecos.get(0).setPassengersNum(XNStation_currentSentNum);
//						System.out.println("XNStation_passengerNum="+XNStation_passengerNum);
//						System.out.println("XNStation_hasSent="+XNStation_hasSent);
						
						//ȡ�������̣߳�����
						Thread t_xb_iveco=manageThread.getThread(XNStation_ivecos.get(0).getNo());
						XNStation_ivecos.remove(0);
						t_xb_iveco.start();
					}


					if(BJStation_ivecos.size()!=0){
						//��˳˿�
						if(BJStation_passengerNum >= MyNumbers.ivecoSeats){
							BJStation_currentSentNum = MyNumbers.ivecoSeats;
						}else{
							BJStation_currentSentNum = BJStation_passengerNum;
						}
						BJStation_passengerNum-=BJStation_currentSentNum;
						BJStation_hasSent+=BJStation_currentSentNum;
						BJStation_ivecos.get(0).setPassengersNum(BJStation_currentSentNum);
//						System.out.println("BJStation_passengerNUM="+BJStation_passengerNUM);
//						System.out.println("BJStation_hasSent="+BJStation_hasSent);
						
						//ȡ�������̣߳�����
						Thread t_bx_iveco=manageThread.getThread(BJStation_ivecos.get(0).getNo());
						BJStation_ivecos.remove(0);
						t_bx_iveco.start();
					}

				}
				
				
				
				//���ʱ����volvo������ʱ�䷶Χ��(8:30am - 5:30pm)�������ֶ����߳�
				if((current_time2>=MyNumbers.time_first_volvo)&&(current_time2<=MyNumbers.time_last_volvo)&&((current_time2-MyNumbers.time_first_volvo)%MyNumbers.volvo_minutes==0))
				{
					//System.out.println("��ǰʱ��Ϊ��"+hour+" : "+minute);

					if(XNStation_volvos.size()!=0){
						//��˳˿�
						if(XNStation_passengerNum >= MyNumbers.volvoSeats){  //��ǰ��վ�˿���������volvo������ؿ���
							XNStation_currentSentNum=MyNumbers.volvoSeats;
						}else{
							XNStation_currentSentNum=XNStation_passengerNum;
						}
						XNStation_passengerNum-=XNStation_currentSentNum;  //��ǰ��վ����
						XNStation_hasSent+=XNStation_currentSentNum; //��ǰ�����ÿ���
						//�趨��ǰvolvo��������
						XNStation_volvos.get(0).setPassengersNum(XNStation_currentSentNum);
						
						//ȡ�������̣߳�����
						Thread t_bx_volvo=manageThread.getThread(XNStation_volvos.get(0).getNo());
						XNStation_volvos.remove(0);
						t_bx_volvo.start();
					}

					if(BJStation_volvos.size()!=0){
						//��˳˿�
						if(BJStation_passengerNum >= MyNumbers.volvoSeats){
							BJStation_currentSentNum = MyNumbers.volvoSeats;
						}else{
							BJStation_currentSentNum = BJStation_passengerNum;
						}
						BJStation_passengerNum-=BJStation_currentSentNum;
						BJStation_hasSent+=BJStation_currentSentNum;
						BJStation_volvos.get(0).setPassengersNum(BJStation_currentSentNum);
						
						//ȡ�������̣߳�����
						Thread t_bx_volvo=manageThread.getThread(BJStation_volvos.get(0).getNo());
						BJStation_volvos.remove(0);
						t_bx_volvo.start();
					}
					
				}
				
//				System.out.println("*************************************************");
//				System.out.println("XNStation_passengerNum="+XNStation_passengerNum);
//				System.out.println("XNStation_hasSent="+XNStation_hasSent);
//				System.out.println("BJStation_passengerNum="+BJStation_passengerNum);
//				System.out.println("BJStation_hasSent="+BJStation_hasSent);
				
				//���ʼ����վ��Ϣ����������
				manageStationMessage.addStationMessage("XNStation_passengerNum", XNStation_passengerNum);
				manageStationMessage.addStationMessage("XNStation_hasSent", XNStation_hasSent);
				manageStationMessage.addStationMessage("BJStation_passengerNum", BJStation_passengerNum);
				manageStationMessage.addStationMessage("BJStation_hasSent", BJStation_hasSent);
				
				//ʱ������1
				current_time++;
				current_time2++;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	

}
