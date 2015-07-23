package com.common;

import java.util.Vector;

import javax.swing.JLabel;

import com.tools.MyNumbers;
import com.manage.*;

public class Time implements Runnable{
	//管理类
	ManageJLabel manageJLabel;
	ManageStartState manageStartState;
	ManageThread manageThread;
	ManageVehicles manageVehicles;
	ManageStationMessage manageStationMessage;

	private int current_time=MyNumbers.time_start;
	int current_time2;
	int hour,minute;

	//显示时间
	JLabel jl_showTime;

	//获取初始状态下各车的数目及每分钟到达的人数
	int XN_volvoNums,XN_ivecoNums,BJ_volvoNums,BJ_ivecoNums,PN;

	//创建向量，用于存放车对象
	Vector<IVECO> XB_ivecos=new Vector<IVECO>();	
	Vector<IVECO> BX_ivecos=new Vector<IVECO>();
	Vector<VOLVO> XB_volvos=new Vector<VOLVO>();
	Vector<VOLVO> BX_volvos=new Vector<VOLVO>();
	
	//创建车站向量，用于存储车站的不同车
	Vector<IVECO> XNStation_ivecos=new Vector<IVECO>();
	Vector<IVECO> BJStation_ivecos=new Vector<IVECO>();
	Vector<VOLVO> XNStation_volvos=new Vector<VOLVO>();
	Vector<VOLVO> BJStation_volvos=new Vector<VOLVO>();

	/**
	 * 车编号
	 */
	String vehiclesNo;
	
	/**
	 * 西安车站当前乘客人数
	 */
	public static int XNStation_passengerNum=0;
	/**
	 * 宝鸡车站当前乘客人数
	 */
	public static int BJStation_passengerNum=0;
	
	/**
	 * 西安车站已经发送乘客数目
	 */
	public static int XNStation_hasSent=0;
	/**
	 * 宝鸡车站已经发送乘客数目
	 */
	public static int BJStation_hasSent=0;
	
	/**
	 * 西安站当前发送乘客数目
	 */
	int XNStation_currentSentNum=0;
	/**
	 * 宝鸡站当前发送乘客数目
	 */
	int BJStation_currentSentNum=0;

	public Time() {
		// TODO Auto-generated constructor stub
		//创建JLabel管理类对象
		manageJLabel=new ManageJLabel();
		jl_showTime=manageJLabel.getJLabel("Time");

		//创建初始状态管理类对象
		manageStartState=new ManageStartState();

		//把车向量加入到管理车的类中，用于在MyPanel画时，可以取出
		manageVehicles=new ManageVehicles();
		manageVehicles.addVehicles("XB_ivecos", XB_ivecos);
		manageVehicles.addVehicles("BX_ivecos", BX_ivecos);
		manageVehicles.addVehicles("XB_volvos", XB_volvos);
		manageVehicles.addVehicles("BX_volvos", BX_volvos);
		manageVehicles.addVehicles("XNStation_ivecos", XNStation_ivecos);
		manageVehicles.addVehicles("BJStation_ivecos", BJStation_ivecos);
		manageVehicles.addVehicles("XNStation_volvos", XNStation_volvos);
		manageVehicles.addVehicles("BJStation_volvos", BJStation_volvos);
		
		//创建线程管理类对象
		manageThread=new ManageThread();
		
		//创建管理始发车站信息
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
				Thread.sleep(MyNumbers.milliSeconds);  //休眠一秒

				//显示时间
				if(minute>=0 && minute<=9){
					jl_showTime.setText("当前时间为："+hour+" : 0"+minute);
				}else{
					jl_showTime.setText("当前时间为："+hour+" : "+minute);
				}

				//生成乘客
				PN=manageStartState.getStartState("PN");
				XNStation_passengerNum+=(int)((Math.random()*1000)%(PN+1));
				BJStation_passengerNum+=(int)((Math.random()*1000)%(PN+1));
				//System.out.println("西安站乘客数为："+XNStation_passengerNum);
				//System.out.println("宝鸡站乘客数为："+BJStation_passengerNUM);
				
				//时间最开始，建立各个车对象，并创建线程加入到线程管理类中（线程不启动，在需要时再启动）
				if(current_time==MyNumbers.time_start){
					//从初始状态管理类中获取到起始状态信息
					XN_ivecoNums=manageStartState.getStartState("XN_ivecoNums");
					BJ_ivecoNums=manageStartState.getStartState("BJ_ivecoNums");
					XN_volvoNums=manageStartState.getStartState("XN_volvoNums");
					BJ_volvoNums=manageStartState.getStartState("BJ_volvoNums");
					
					//创建西安->宝鸡的依维柯
					for(int i=0;i<XN_ivecoNums;i++){
						//（西->宝）依维柯的初始位置
						IVECO xb_iveco=new IVECO(776, 57); //从右往左
						xb_iveco.setDirect(1); //设定车的方向
						xb_iveco.setSpeed(MyNumbers.ivecoSpeed);
						vehiclesNo="Xi_"+(i+1)+"";
						xb_iveco.setNo(vehiclesNo); //设定车的编号
						xb_iveco.setPassengersNum(0); //初始情况，乘客数为0
						XB_ivecos.add(xb_iveco);
						XNStation_ivecos.add(xb_iveco);

						Thread t_xb_iveco=new Thread(xb_iveco);
						//将线程加入到线程管理类中
						manageThread.addThread(vehiclesNo, t_xb_iveco);
					}

					//创建宝鸡->西安的依维柯
					for(int j=0;j<BJ_ivecoNums;j++){
						//创建一辆宝鸡-->西安的iveco对象
						IVECO bx_iveco=new IVECO(20, 192);  //从左往右
						bx_iveco.setDirect(0); //设定车的方向
						bx_iveco.setSpeed(MyNumbers.ivecoSpeed);
						vehiclesNo="Bi_"+(j+1)+"";
						bx_iveco.setNo(vehiclesNo);
						bx_iveco.setPassengersNum(0);
						//加入到（宝西iveco）向量组中
						BX_ivecos.add(bx_iveco);
						BJStation_ivecos.add(bx_iveco);

						//创建线程
						Thread t_bx_iveco=new Thread(bx_iveco);
						//将线程加入到线程管理类中
						manageThread.addThread(vehiclesNo, t_bx_iveco);
					}

					//创建西安->宝鸡volvo
					for(int m=0; m<XN_volvoNums; m++){
						//（西->宝）沃尔沃的初始位置
						VOLVO xb_volvo=new VOLVO(776, 117); //从右往左
						xb_volvo.setDirect(1); //设定车的方向
						xb_volvo.setSpeed(MyNumbers.volvoSpeed);
						vehiclesNo="Xv_"+(m+1)+"";
						xb_volvo.setNo(vehiclesNo);
						xb_volvo.setPassengersNum(0);
						XB_volvos.add(xb_volvo);
						XNStation_volvos.add(xb_volvo);

						Thread t_xb_volvo=new Thread(xb_volvo);
						manageThread.addThread(vehiclesNo, t_xb_volvo);

					}


					//创建宝鸡->西安volvo
					for(int n=0; n<BJ_volvoNums; n++){
						//（宝->西）沃尔沃的初始位置
						VOLVO bx_volvo=new VOLVO(10, 252); //从左往右
						bx_volvo.setDirect(0); //设定车的方向
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

				
				//如果时间在iveco发车的时间范围(8:00am - 6:00pm)内，且每20分钟一班，开启依维柯线程
				if((current_time>=MyNumbers.time_first_iveco)&&(current_time<=MyNumbers.time_last_iveco)&&((current_time-MyNumbers.time_first_iveco)%MyNumbers.iveco_minutes==0))
				{
					//System.out.println("当前时间为："+hour+" : "+minute);
					
					if(XNStation_ivecos.size()!=0){
						//搭乘乘客
						if(XNStation_passengerNum >= MyNumbers.ivecoSeats){  //当前车站乘客人数大于iveco的最大载客量
							XNStation_currentSentNum=MyNumbers.ivecoSeats;
						}else{
							XNStation_currentSentNum=XNStation_passengerNum;
						}
						XNStation_passengerNum-=XNStation_currentSentNum;  //当前车站人数
						XNStation_hasSent+=XNStation_currentSentNum; //当前发送旅客数
						//设定当前iveco发送人数
						XNStation_ivecos.get(0).setPassengersNum(XNStation_currentSentNum);
//						System.out.println("XNStation_passengerNum="+XNStation_passengerNum);
//						System.out.println("XNStation_hasSent="+XNStation_hasSent);
						
						//取出车辆线程，发车
						Thread t_xb_iveco=manageThread.getThread(XNStation_ivecos.get(0).getNo());
						XNStation_ivecos.remove(0);
						t_xb_iveco.start();
					}


					if(BJStation_ivecos.size()!=0){
						//搭乘乘客
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
						
						//取出车辆线程，发车
						Thread t_bx_iveco=manageThread.getThread(BJStation_ivecos.get(0).getNo());
						BJStation_ivecos.remove(0);
						t_bx_iveco.start();
					}

				}
				
				
				
				//如果时间在volvo发车的时间范围内(8:30am - 5:30pm)，开启沃尔沃线程
				if((current_time2>=MyNumbers.time_first_volvo)&&(current_time2<=MyNumbers.time_last_volvo)&&((current_time2-MyNumbers.time_first_volvo)%MyNumbers.volvo_minutes==0))
				{
					//System.out.println("当前时间为："+hour+" : "+minute);

					if(XNStation_volvos.size()!=0){
						//搭乘乘客
						if(XNStation_passengerNum >= MyNumbers.volvoSeats){  //当前车站乘客人数大于volvo的最大载客量
							XNStation_currentSentNum=MyNumbers.volvoSeats;
						}else{
							XNStation_currentSentNum=XNStation_passengerNum;
						}
						XNStation_passengerNum-=XNStation_currentSentNum;  //当前车站人数
						XNStation_hasSent+=XNStation_currentSentNum; //当前发送旅客数
						//设定当前volvo发送人数
						XNStation_volvos.get(0).setPassengersNum(XNStation_currentSentNum);
						
						//取出车辆线程，发车
						Thread t_bx_volvo=manageThread.getThread(XNStation_volvos.get(0).getNo());
						XNStation_volvos.remove(0);
						t_bx_volvo.start();
					}

					if(BJStation_volvos.size()!=0){
						//搭乘乘客
						if(BJStation_passengerNum >= MyNumbers.volvoSeats){
							BJStation_currentSentNum = MyNumbers.volvoSeats;
						}else{
							BJStation_currentSentNum = BJStation_passengerNum;
						}
						BJStation_passengerNum-=BJStation_currentSentNum;
						BJStation_hasSent+=BJStation_currentSentNum;
						BJStation_volvos.get(0).setPassengersNum(BJStation_currentSentNum);
						
						//取出车辆线程，发车
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
				
				//添加始发车站信息到管理类中
				manageStationMessage.addStationMessage("XNStation_passengerNum", XNStation_passengerNum);
				manageStationMessage.addStationMessage("XNStation_hasSent", XNStation_hasSent);
				manageStationMessage.addStationMessage("BJStation_passengerNum", BJStation_passengerNum);
				manageStationMessage.addStationMessage("BJStation_hasSent", BJStation_hasSent);
				
				//时间数加1
				current_time++;
				current_time2++;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	

}
