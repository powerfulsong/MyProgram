package com.common;

import com.manage.ManageProbability;
import com.tools.MyNumbers;
/**
 * 车类
 * @author PowerfulSong
 */
public class Vehicles {
	//车的横坐标，纵坐标
	int x=0,y=0;

	//速度
	int speed;

	//方向
	int direct;

	//编号
	String No;

	//乘客数
	int passengersNum;
	
	/**
	 * 判断车是否到达
	 */
	boolean isArrived=false;

	/**
	 * 判断是否需要停车
	 */
	boolean isNeedStop=false;

	/**
	 * 停车两分钟
	 */
	public void stopTwoMinutes(){
		this.isNeedStop=true;
		try {
			Thread.sleep(2*MyNumbers.milliSeconds); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.isNeedStop=false;
	}
	
	public int getPassengersNum() {
		return passengersNum;
	}

	public void setPassengersNum(int passengersNum) {
		this.passengersNum = passengersNum;
	}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public boolean isArrived() {
		return isArrived;
	}

	public void setArrived(boolean isArrived) {
		this.isArrived = isArrived;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Vehicles(int x,int y){
		this.x = x;
		this.y = y;
	}
}

/**
 * 依维柯类
 * @author PowerfulSong
 */
class IVECO extends Vehicles implements Runnable{
	//宝鸡->西安 沿途停车概率
	double P_BX_gz,P_BX_cp,P_BX_wg,P_BX_xp,P_BX_xy,P_BX_xn;
	//西安->宝鸡 沿途停车概率
	double P_XB_xy,P_XB_xp,P_XB_wg,P_XB_cp,P_XB_gz,P_XB_bj;

	//管理类对象
	ManageProbability manageProbability=null;
	
	/**
	 * 始发站上车的总人数
	 */
	int allPassengerNum=0;
	
	/**
	 * 车辆进站时的乘客数目
	 */
	int oldPassengerNum=0;
	/**
	 * 车辆出站时的乘客数目
	 */
	int newPassengerNum=0;

	/**
	 * 到站旅客数目
	 */
	int arrivedPassengerNum=0;

	public IVECO(int x,int y){
		super(x, y);

		manageProbability=new ManageProbability();
		//获取概率值
		//宝鸡->西安
		P_BX_gz=manageProbability.getProbability("P_BX_gz");
		P_BX_cp=manageProbability.getProbability("P_BX_cp");
		P_BX_wg=manageProbability.getProbability("P_BX_wg");
		P_BX_xp=manageProbability.getProbability("P_BX_xp");
		P_BX_xy=manageProbability.getProbability("P_BX_xy");
		P_BX_xn=manageProbability.getProbability("P_BX_xn");
		//西安->宝鸡
		P_XB_xy=manageProbability.getProbability("P_XB_xy");
		P_XB_xp=manageProbability.getProbability("P_XB_xp");
		P_XB_wg=manageProbability.getProbability("P_XB_wg");
		P_XB_cp=manageProbability.getProbability("P_XB_cp");
		P_XB_gz=manageProbability.getProbability("P_XB_gz");
		P_XB_bj=manageProbability.getProbability("P_XB_bj");
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(MyNumbers.milliSeconds);
			} catch (Exception e) {
				// TODO: handle exception
			}

			//车的方向
			switch(this.direct){
			case 0: //向右移动 
				if(this.x >= 776){ //如果到达边界，则退出循环
					//System.out.println("iveco到达西安");
					this.isArrived = true;
				}else{  //没有到达边界
					if(this.x+30==176 && P_BX_gz!=0){ //iveco到达虢镇，有人下车
						this.stopTwoMinutes();
						//获取始发站上车的全部乘客数目
						allPassengerNum=this.getPassengersNum();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==260 && P_BX_cp!=0){ //iveco到达蔡家坡，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==506 && P_BX_wg!=0){ //iveco到达武功，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==590 && P_BX_xp!=0){ //iveco到达兴平，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==686 && P_BX_xy!=0){ //iveco到达咸阳，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==776 && P_BX_xn!=0){ //iveco到达西安，有人下车
						this.stopTwoMinutes();
						//终点站乘客数目为0
						this.setPassengersNum(0);
					}

					//判断是否需要停车
					if(this.isNeedStop == false){
						this.x+=speed;
					}
				}
				break;
			case 1: //向左移动
				if(this.x <= 20){ //如果到达边界，则退出循环
					//System.out.println("iveco到达宝鸡");
					this.isArrived = true;
				}else{//没有到达边界
					if(this.x+30==686 && P_XB_xy!=0){ //iveco到达咸阳，有人下车
						this.stopTwoMinutes();
						//获取始发站上车的全部乘客数目
						allPassengerNum=this.getPassengersNum();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==590 && P_XB_xp!=0){ //iveco到达兴平，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==506 && P_XB_wg!=0){ //iveco到达武功，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==260 && P_XB_cp!=0){ //iveco到达蔡家坡，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==176 && P_XB_gz!=0){ //iveco到达虢镇，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==80 && P_XB_bj!=0){ //iveco到达宝鸡，有人下车
						this.stopTwoMinutes();
						//终点站乘客数目为0
						this.setPassengersNum(0);
					}

					if(this.isNeedStop == false){
						this.x-=speed;
					}
				}

				break;
			}


			//判断车辆是否到边界，终止线程
			if(this.isArrived == true){
				//退出线程
				break;
			}

		}

	}

}

/**
 * 沃尔沃类
 * @author PowerfulSong
 */
class VOLVO extends Vehicles implements Runnable{
	//宝鸡->西安 沿途停车概率
	double P_BX_gz,P_BX_cp,P_BX_wg,P_BX_xp,P_BX_xy,P_BX_xn;
	//西安->宝鸡 沿途停车概率
	double P_XB_xy,P_XB_xp,P_XB_wg,P_XB_cp,P_XB_gz,P_XB_bj;

	//管理类对象
	ManageProbability manageProbability=null;
	
	/**
	 * 始发站上车的总人数
	 */
	int allPassengerNum=0;
	
	/**
	 * 车辆进站时的乘客数目
	 */
	int oldPassengerNum=0;
	/**
	 * 车辆出站时的乘客数目
	 */
	int newPassengerNum=0;
	/**
	 * 到站旅客数目
	 */
	int arrivedPassengerNum=0;

	public VOLVO(int x,int y){
		super(x, y);
		
		manageProbability=new ManageProbability();
		//获取概率值
		//宝鸡->西安
		P_BX_gz=manageProbability.getProbability("P_BX_gz");
		P_BX_cp=manageProbability.getProbability("P_BX_cp");
		P_BX_wg=manageProbability.getProbability("P_BX_wg");
		P_BX_xp=manageProbability.getProbability("P_BX_xp");
		P_BX_xy=manageProbability.getProbability("P_BX_xy");
		P_BX_xn=manageProbability.getProbability("P_BX_xn");
		//西安->宝鸡
		P_XB_xy=manageProbability.getProbability("P_XB_xy");
		P_XB_xp=manageProbability.getProbability("P_XB_xp");
		P_XB_wg=manageProbability.getProbability("P_XB_wg");
		P_XB_cp=manageProbability.getProbability("P_XB_cp");
		P_XB_gz=manageProbability.getProbability("P_XB_gz");
		P_XB_bj=manageProbability.getProbability("P_XB_bj");
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(MyNumbers.milliSeconds);
			} catch (Exception e) {
				// TODO: handle exception
			}

			//车的方向
			switch(this.direct){
			case 0: //向右移动
				if(this.x >= 776){ //如果到达边界，则退出循环
					//System.out.println("volvo到达西安");
					this.isArrived = true;
				}else{ //没有到达边界
					if(this.x+32==178 && P_BX_gz!=0){ //volvo到达虢镇，有人下车
						this.stopTwoMinutes();
						//获取始发站上车的全部乘客数目
						allPassengerNum=this.getPassengersNum();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==258 && P_BX_cp!=0){ //volvo到达蔡家坡，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==506 && P_BX_wg!=0){ //volvo到达武功，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==594 && P_BX_xp!=0){ //volvo到达兴平，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==690 && P_BX_xy!=0){ //volvo到达咸阳，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==770 && P_BX_xn!=0){ //volvo到达西安，有人下车
						this.stopTwoMinutes();
						//终点站乘客数目为0
						this.setPassengersNum(0);
					}

					if(this.isNeedStop==false){
						this.x+=speed;
					}
				}
				break;
			case 1: //向左移动
				if(this.x <= 10){  //如果到达边界，则退出循环
					//System.out.println("volvo到达宝鸡");
					this.isArrived = true;
				}else{//没有到达边界
					if(this.x+32==688 && P_XB_xy!=0){ //volvo到达咸阳，有人下车
						this.stopTwoMinutes();
						//获取始发站上车的全部乘客数目
						allPassengerNum=this.getPassengersNum();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==592 && P_XB_xp!=0){ //volvo到达兴平，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==504 && P_XB_wg!=0){ //volvo到达武功，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==256 && P_XB_cp!=0){ //volvo到达蔡家坡，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==176 && P_XB_gz!=0){ //volvo到达虢镇，有人下车
						this.stopTwoMinutes();
						//获取原来当前乘客数目
						oldPassengerNum=this.getPassengersNum();
						//计算新的乘客数目
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==80 && P_XB_bj!=0){ //volvo到达宝鸡，有人下车
						this.stopTwoMinutes();
						//终点站乘客数目为0
						this.setPassengersNum(0);
					}

					if(this.isNeedStop==false){
						this.x-=speed;
					}
				}
				break;
			}

			//判断车辆是否到边界，终止线程
			if(this.isArrived == true){
				//退出线程
				break;
			}

		}

	}

}

