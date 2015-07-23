package com.common;

import com.manage.ManageProbability;
import com.tools.MyNumbers;
/**
 * ����
 * @author PowerfulSong
 */
public class Vehicles {
	//���ĺ����꣬������
	int x=0,y=0;

	//�ٶ�
	int speed;

	//����
	int direct;

	//���
	String No;

	//�˿���
	int passengersNum;
	
	/**
	 * �жϳ��Ƿ񵽴�
	 */
	boolean isArrived=false;

	/**
	 * �ж��Ƿ���Ҫͣ��
	 */
	boolean isNeedStop=false;

	/**
	 * ͣ��������
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
 * ��ά����
 * @author PowerfulSong
 */
class IVECO extends Vehicles implements Runnable{
	//����->���� ��;ͣ������
	double P_BX_gz,P_BX_cp,P_BX_wg,P_BX_xp,P_BX_xy,P_BX_xn;
	//����->���� ��;ͣ������
	double P_XB_xy,P_XB_xp,P_XB_wg,P_XB_cp,P_XB_gz,P_XB_bj;

	//���������
	ManageProbability manageProbability=null;
	
	/**
	 * ʼ��վ�ϳ���������
	 */
	int allPassengerNum=0;
	
	/**
	 * ������վʱ�ĳ˿���Ŀ
	 */
	int oldPassengerNum=0;
	/**
	 * ������վʱ�ĳ˿���Ŀ
	 */
	int newPassengerNum=0;

	/**
	 * ��վ�ÿ���Ŀ
	 */
	int arrivedPassengerNum=0;

	public IVECO(int x,int y){
		super(x, y);

		manageProbability=new ManageProbability();
		//��ȡ����ֵ
		//����->����
		P_BX_gz=manageProbability.getProbability("P_BX_gz");
		P_BX_cp=manageProbability.getProbability("P_BX_cp");
		P_BX_wg=manageProbability.getProbability("P_BX_wg");
		P_BX_xp=manageProbability.getProbability("P_BX_xp");
		P_BX_xy=manageProbability.getProbability("P_BX_xy");
		P_BX_xn=manageProbability.getProbability("P_BX_xn");
		//����->����
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

			//���ķ���
			switch(this.direct){
			case 0: //�����ƶ� 
				if(this.x >= 776){ //�������߽磬���˳�ѭ��
					//System.out.println("iveco��������");
					this.isArrived = true;
				}else{  //û�е���߽�
					if(this.x+30==176 && P_BX_gz!=0){ //iveco������������³�
						this.stopTwoMinutes();
						//��ȡʼ��վ�ϳ���ȫ���˿���Ŀ
						allPassengerNum=this.getPassengersNum();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==260 && P_BX_cp!=0){ //iveco����̼��£������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==506 && P_BX_wg!=0){ //iveco�����书�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==590 && P_BX_xp!=0){ //iveco������ƽ�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==686 && P_BX_xy!=0){ //iveco���������������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==776 && P_BX_xn!=0){ //iveco���������������³�
						this.stopTwoMinutes();
						//�յ�վ�˿���ĿΪ0
						this.setPassengersNum(0);
					}

					//�ж��Ƿ���Ҫͣ��
					if(this.isNeedStop == false){
						this.x+=speed;
					}
				}
				break;
			case 1: //�����ƶ�
				if(this.x <= 20){ //�������߽磬���˳�ѭ��
					//System.out.println("iveco���ﱦ��");
					this.isArrived = true;
				}else{//û�е���߽�
					if(this.x+30==686 && P_XB_xy!=0){ //iveco���������������³�
						this.stopTwoMinutes();
						//��ȡʼ��վ�ϳ���ȫ���˿���Ŀ
						allPassengerNum=this.getPassengersNum();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==590 && P_XB_xp!=0){ //iveco������ƽ�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==506 && P_XB_wg!=0){ //iveco�����书�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==260 && P_XB_cp!=0){ //iveco����̼��£������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==176 && P_XB_gz!=0){ //iveco������������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+30==80 && P_XB_bj!=0){ //iveco���ﱦ���������³�
						this.stopTwoMinutes();
						//�յ�վ�˿���ĿΪ0
						this.setPassengersNum(0);
					}

					if(this.isNeedStop == false){
						this.x-=speed;
					}
				}

				break;
			}


			//�жϳ����Ƿ񵽱߽磬��ֹ�߳�
			if(this.isArrived == true){
				//�˳��߳�
				break;
			}

		}

	}

}

/**
 * �ֶ�����
 * @author PowerfulSong
 */
class VOLVO extends Vehicles implements Runnable{
	//����->���� ��;ͣ������
	double P_BX_gz,P_BX_cp,P_BX_wg,P_BX_xp,P_BX_xy,P_BX_xn;
	//����->���� ��;ͣ������
	double P_XB_xy,P_XB_xp,P_XB_wg,P_XB_cp,P_XB_gz,P_XB_bj;

	//���������
	ManageProbability manageProbability=null;
	
	/**
	 * ʼ��վ�ϳ���������
	 */
	int allPassengerNum=0;
	
	/**
	 * ������վʱ�ĳ˿���Ŀ
	 */
	int oldPassengerNum=0;
	/**
	 * ������վʱ�ĳ˿���Ŀ
	 */
	int newPassengerNum=0;
	/**
	 * ��վ�ÿ���Ŀ
	 */
	int arrivedPassengerNum=0;

	public VOLVO(int x,int y){
		super(x, y);
		
		manageProbability=new ManageProbability();
		//��ȡ����ֵ
		//����->����
		P_BX_gz=manageProbability.getProbability("P_BX_gz");
		P_BX_cp=manageProbability.getProbability("P_BX_cp");
		P_BX_wg=manageProbability.getProbability("P_BX_wg");
		P_BX_xp=manageProbability.getProbability("P_BX_xp");
		P_BX_xy=manageProbability.getProbability("P_BX_xy");
		P_BX_xn=manageProbability.getProbability("P_BX_xn");
		//����->����
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

			//���ķ���
			switch(this.direct){
			case 0: //�����ƶ�
				if(this.x >= 776){ //�������߽磬���˳�ѭ��
					//System.out.println("volvo��������");
					this.isArrived = true;
				}else{ //û�е���߽�
					if(this.x+32==178 && P_BX_gz!=0){ //volvo������������³�
						this.stopTwoMinutes();
						//��ȡʼ��վ�ϳ���ȫ���˿���Ŀ
						allPassengerNum=this.getPassengersNum();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==258 && P_BX_cp!=0){ //volvo����̼��£������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==506 && P_BX_wg!=0){ //volvo�����书�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==594 && P_BX_xp!=0){ //volvo������ƽ�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==690 && P_BX_xy!=0){ //volvo���������������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_BX_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==770 && P_BX_xn!=0){ //volvo���������������³�
						this.stopTwoMinutes();
						//�յ�վ�˿���ĿΪ0
						this.setPassengersNum(0);
					}

					if(this.isNeedStop==false){
						this.x+=speed;
					}
				}
				break;
			case 1: //�����ƶ�
				if(this.x <= 10){  //�������߽磬���˳�ѭ��
					//System.out.println("volvo���ﱦ��");
					this.isArrived = true;
				}else{//û�е���߽�
					if(this.x+32==688 && P_XB_xy!=0){ //volvo���������������³�
						this.stopTwoMinutes();
						//��ȡʼ��վ�ϳ���ȫ���˿���Ŀ
						allPassengerNum=this.getPassengersNum();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xy);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==592 && P_XB_xp!=0){ //volvo������ƽ�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_xp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==504 && P_XB_wg!=0){ //volvo�����书�������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_wg);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==256 && P_XB_cp!=0){ //volvo����̼��£������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_cp);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==176 && P_XB_gz!=0){ //volvo������������³�
						this.stopTwoMinutes();
						//��ȡԭ����ǰ�˿���Ŀ
						oldPassengerNum=this.getPassengersNum();
						//�����µĳ˿���Ŀ
						arrivedPassengerNum=(int)(allPassengerNum*P_XB_gz);
						newPassengerNum=oldPassengerNum-arrivedPassengerNum;
						this.setPassengersNum(newPassengerNum);
					}
					if(this.x+32==80 && P_XB_bj!=0){ //volvo���ﱦ���������³�
						this.stopTwoMinutes();
						//�յ�վ�˿���ĿΪ0
						this.setPassengersNum(0);
					}

					if(this.isNeedStop==false){
						this.x-=speed;
					}
				}
				break;
			}

			//�жϳ����Ƿ񵽱߽磬��ֹ�߳�
			if(this.isArrived == true){
				//�˳��߳�
				break;
			}

		}

	}

}

