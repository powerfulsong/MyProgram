package com.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import com.manage.ManageStartState;
import com.manage.ManageStationMessage;
import com.manage.ManageThread;
import com.manage.ManageVehicles;
import com.tools.MyFonts;
import com.tools.MyNumbers;

/**
 * 我的面板
 * @author PowerfulSong
 */
class MyPanel extends JPanel implements Runnable{

	ManageVehicles manageVehicles;
	ManageThread manageThread;
	ManageStationMessage manageStationMessage;

	//显示车站当前候车人数和已发送旅客数目
	int XNStation_passengerNum=0, BJStation_passengerNum=0, XNStation_hasSent=0,BJStation_hasSent=0;

	//创建车站向量，用于存储车站的不同车
	Vector<IVECO> XNStation_ivecos=new Vector<IVECO>();
	Vector<IVECO> BJStation_ivecos=new Vector<IVECO>();
	Vector<VOLVO> XNStation_volvos=new Vector<VOLVO>();
	Vector<VOLVO> BJStation_volvos=new Vector<VOLVO>();
	
	//显示站内所有同型号车辆
	String allXNStation_ivecos="",allBJStation_ivecos="",allXNStation_volvos="",allBJStation_volvos="";

	Graphics g;

	public MyPanel(){
		//创建管理类对象
		manageVehicles=new ManageVehicles();
		manageThread=new ManageThread();
		manageStationMessage=new ManageStationMessage();

	}

	//重写paint方法
	public void paint(Graphics g){	
		//System.out.println("绘制面板");	
		super.paint(g);

		//画路面及车站
		this.drawRoadAndStation(g);	

		//画所有在路面上运动的车
		this.drawAllVehicles(g);

		//画当前始发站信息
		this.drawCurrentStationMessage(g);

	}

	/**
	 * 画出背景路面及车站
	 * @param g
	 */
	public void drawRoadAndStation(Graphics g){

		//画马路
		g.setColor(Color.BLACK);
		g.fillRect(80, 50, 696, 245);

		//画分割线
		g.setColor(Color.ORANGE);
		g.fillRect(80, 160, 696, 10);
		g.fillRect(80, 175, 696, 10);

		//画白线
		g.setColor(Color.WHITE);
		g.fillRect( 80, 100, 100, 10);
		g.fillRect(280, 100, 100, 10);
		g.fillRect(480, 100, 100, 10);
		g.fillRect(680, 100, 100, 10);
		g.fillRect( 80, 235, 100, 10);
		g.fillRect(280, 235, 100, 10);
		g.fillRect(480, 235, 100, 10);
		g.fillRect(680, 235, 100, 10);

		//画虚线
		g.setColor(Color.CYAN);
		g.drawLine( 80, 45,  80, 300);
		g.drawLine(176, 45, 176, 300);
		g.drawLine(260, 45, 260, 300);
		g.drawLine(508, 45, 508, 300);
		g.drawLine(592, 45, 592, 300);
		g.drawLine(688, 45, 688, 300);
		g.drawLine(776, 45, 776, 300);

		//画车站
		g.setColor(Color.BLACK);
		g.setFont(new Font("华文新魏", Font.PLAIN, 24));
		g.drawString("宝鸡",  56, 40);
		g.drawString("虢镇", 152, 40);
		g.drawString("蔡家坡",  224, 40);
		g.drawString("武功", 484, 40);
		g.drawString("兴平",  568, 40);
		g.drawString("咸阳", 664, 40);
		g.drawString("西安",  752, 40);
	}

	/**
	 * 画当前车站信息
	 */
	public void drawCurrentStationMessage(Graphics g){
		//宝鸡站
		g.setColor(Color.BLACK);
		g.setFont(new Font("华文新魏", Font.PLAIN, 24));
		g.drawString("当前宝鸡车站信息：", 5, 320);
		g.setFont(MyFonts.f1);
		//显示候车乘客数目
		g.drawString("候车乘客数目：",30,345);
		if(manageStationMessage.hasMessage()){
			BJStation_passengerNum=manageStationMessage.getStationMessage("BJStation_passengerNum");
		}
		g.drawString(BJStation_passengerNum+"", 140, 345);
		//显示已发送数目
		g.drawString("已发送乘客数目：", 250, 345);
		if(manageStationMessage.hasMessage()){
			BJStation_hasSent=manageStationMessage.getStationMessage("BJStation_hasSent");
		}
		g.drawString(BJStation_hasSent+"", 380, 345);
		//显示站内iveco的编号
		g.drawString("站内的依维柯编号：", 30, 370);
		BJStation_ivecos=manageVehicles.getVehicles("BJStation_ivecos");
		if(BJStation_ivecos.size()!=0){
			allBJStation_ivecos="";
			for(int i=0; i<BJStation_ivecos.size(); i++){
				allBJStation_ivecos+=BJStation_ivecos.get(i).getNo()+" ";
			}
		}else{ //站内没车
			allBJStation_ivecos="";
		}
		g.drawString(allBJStation_ivecos, 170, 370);
		//显示站内volvo的编号
		g.drawString("站内的沃尔沃编号：", 30, 395);
		BJStation_volvos=manageVehicles.getVehicles("BJStation_volvos");
		if(BJStation_volvos.size()!=0){
			allBJStation_volvos="";
			for(int j=0;j<BJStation_volvos.size();j++){
				allBJStation_volvos+=BJStation_volvos.get(j).getNo()+" ";
			}
		}else{
			allBJStation_volvos="";
		}
		g.drawString(allBJStation_volvos, 170, 395);

		//西安站
		g.setFont(new Font("华文新魏", Font.PLAIN, 24));
		g.drawString("当前西安车站信息：", 5, 420);
		g.setFont(MyFonts.f1);
		//显示候车乘客数目
		g.drawString("候车乘客数目：",30,445);
		if(manageStationMessage.hasMessage()){
			XNStation_passengerNum=manageStationMessage.getStationMessage("XNStation_passengerNum");
		}
		g.drawString(XNStation_passengerNum+"", 140, 445);
		//显示已发送数目
		g.drawString("已发送乘客数目：", 250, 445);
		if(manageStationMessage.hasMessage()){
			XNStation_hasSent=manageStationMessage.getStationMessage("XNStation_hasSent");
		}
		g.drawString(XNStation_hasSent+"", 380, 445);
		//显示站内iveco的编号
		g.drawString("站内的依维柯编号：", 30, 470);
		XNStation_ivecos=manageVehicles.getVehicles("XNStation_ivecos");
		if(XNStation_ivecos.size()!=0){
			allXNStation_ivecos="";
			for(int m=0;m<XNStation_ivecos.size();m++){
				allXNStation_ivecos+=XNStation_ivecos.get(m).getNo()+" ";
			}
		}else{
			allXNStation_ivecos="";
		}
		g.drawString(allXNStation_ivecos, 170, 470);
		//显示站内volvo的编号
		g.drawString("站内的沃尔沃编号：", 30, 495);
		XNStation_volvos=manageVehicles.getVehicles("XNStation_volvos");
		if(XNStation_volvos.size()!=0){
			allXNStation_volvos="";
			for(int n=0;n<XNStation_volvos.size();n++){
				allXNStation_volvos+=XNStation_volvos.get(n).getNo()+" ";
			}
		}else{
			allXNStation_volvos="";
		}
		g.drawString(allXNStation_volvos, 170, 495);
	}

	/**
	 * 画所有在路面运动上的车
	 */
	public void drawAllVehicles(Graphics g){

		Vector<IVECO> BX_ivecos=manageVehicles.getVehicles("BX_ivecos");
		Vector<VOLVO> BX_volvos=manageVehicles.getVehicles("BX_volvos");
		Vector<IVECO> XB_ivecos=manageVehicles.getVehicles("XB_ivecos");
		Vector<VOLVO> XB_volvos=manageVehicles.getVehicles("XB_volvos");

		Vector<IVECO> XNStation_ivecos=manageVehicles.getVehicles("XNStation_ivecos");
		Vector<IVECO> BJStation_ivecos=manageVehicles.getVehicles("BJStation_ivecos");
		Vector<VOLVO> XNStation_volvos=manageVehicles.getVehicles("XNStation_volvos");
		Vector<VOLVO> BJStation_volvos=manageVehicles.getVehicles("BJStation_volvos");


		//画出(宝-->西 iveco组)
		//System.out.println("BX_ivecos.size()="+BX_ivecos.size());
		for(int i=0; i<BX_ivecos.size();i++){
			//System.out.println("x="+BX_ivecos.get(i).getX()+"    y="+BX_ivecos.get(i).getY());
			IVECO bx_iveco= BX_ivecos.get(i);
			if(!bx_iveco.isArrived){ //宝西iveco还没有到达
				this.drawIVECO(BX_ivecos.get(i).getX(), BX_ivecos.get(i).getY(), g, BX_ivecos.get(i).getDirect(),BX_ivecos.get(i).getNo(),BX_ivecos.get(i).getPassengersNum());
			}
			else{ //如果到达，并先加入另一个反向向量组中，在将其从原来的向量组中删除
				//先加到另一个向量组(XB_ivecos)中待写
				bx_iveco=new IVECO(776,57);
				bx_iveco.setDirect(1);
				bx_iveco.setSpeed(MyNumbers.ivecoSpeed);
				bx_iveco.setNo(BX_ivecos.get(i).getNo());
				bx_iveco.setPassengersNum(0);
				XB_ivecos.add(bx_iveco);
				XNStation_ivecos.add(bx_iveco); //加到车站里
				//创建线程并加入到管理类中
				Thread t_bx_iveco=new Thread(bx_iveco);
				manageThread.addThread(BX_ivecos.get(i).getNo(), t_bx_iveco);

				//将其从原来的向量组中删除
				BX_ivecos.remove(i);

			}

		}

		//画出(宝-->西 volvo组)
		for(int j=0; j<BX_volvos.size(); j++){
			//System.out.println("x="+BX_volvos.get(j).getX()+"    y="+BX_volvos.get(j).getY());
			VOLVO bx_volvo=BX_volvos.get(j);
			if(!bx_volvo.isArrived){ //宝西volvo还没有到达
				this.drawVOLVO(BX_volvos.get(j).getX(), BX_volvos.get(j).getY(), g, BX_volvos.get(j).getDirect(), BX_volvos.get(j).getNo() ,BX_volvos.get(j).getPassengersNum());
			}
			else{ //如果到达，并先加入另一个反向向量组中，在将其从原来的向量组中删除
				//加到另一个向量组(XB_volvos)中待写
				bx_volvo=new VOLVO(776, 117);
				bx_volvo.setDirect(1);
				bx_volvo.setSpeed(MyNumbers.volvoSpeed);
				bx_volvo.setNo(BX_volvos.get(j).getNo());
				bx_volvo.setPassengersNum(0);
				XB_volvos.add(bx_volvo);
				XNStation_volvos.add(bx_volvo);
				//创建线程并加入到管理类中
				Thread t_bx_volvo=new Thread(bx_volvo);
				manageThread.addThread(BX_volvos.get(j).getNo(), t_bx_volvo);

				//将其从原来的向量组中删除
				BX_volvos.remove(j);
			}

		}

		//画出(西-->宝 iveco组)
		for(int m=0; m<XB_ivecos.size(); m++){
			IVECO xb_iveco=XB_ivecos.get(m);
			if(!xb_iveco.isArrived){ //西宝iveco还没有到达
				this.drawIVECO(XB_ivecos.get(m).getX(), XB_ivecos.get(m).getY(), g, XB_ivecos.get(m).getDirect(), XB_ivecos.get(m).getNo(), XB_ivecos.get(m).getPassengersNum());
			}
			else{//如果到达，并先加入另一个反向向量组中，在将其从原来的向量组中删除
				//加到另一个向量组(BX_ivecos)中待写
				xb_iveco=new IVECO(20, 192);
				xb_iveco.setDirect(0);
				xb_iveco.setSpeed(MyNumbers.ivecoSpeed);
				xb_iveco.setNo(XB_ivecos.get(m).getNo());
				xb_iveco.setPassengersNum(0);
				BX_ivecos.add(xb_iveco);
				BJStation_ivecos.add(xb_iveco);
				//创建线程并加入到管理类中
				Thread t_xb_iveco=new Thread(xb_iveco);
				manageThread.addThread(XB_ivecos.get(m).getNo(), t_xb_iveco);

				//将其从原来的向量组中删除
				XB_ivecos.remove(m);

			}

		}

		//画出(西-->宝 volvo组)
		for(int n=0; n<XB_volvos.size(); n++){
			VOLVO xb_volvo=XB_volvos.get(n);
			if(!xb_volvo.isArrived){  //西宝volvo还没有到达
				this.drawVOLVO(XB_volvos.get(n).getX(), XB_volvos.get(n).getY(), g, XB_volvos.get(n).getDirect(),  XB_volvos.get(n).getNo(),XB_volvos.get(n).getPassengersNum());
			}
			else{ //如果到达，并先加入另一个反向向量组中，在将其从原来的向量组中删除
				//加到另一个向量组(BX_volvos)中待写
				xb_volvo=new VOLVO(10, 252);
				xb_volvo.setDirect(0);
				xb_volvo.setSpeed(MyNumbers.volvoSpeed);
				xb_volvo.setNo(XB_volvos.get(n).getNo());
				xb_volvo.setPassengersNum(0);
				BX_volvos.add(xb_volvo);
				BJStation_volvos.add(xb_volvo);
				//创建线程并加入到管理类中
				Thread t_xb_volvo=new Thread(xb_volvo);
				manageThread.addThread(XB_volvos.get(n).getNo(), t_xb_volvo);

				//将其从原来的向量组中删除
				XB_volvos.remove(n);
			}

		}
	}

	/**
	 * 画依维柯
	 * @param x 当前x坐标
	 * @param y 当前y坐标
	 * @param g 画笔
	 * @param direct 方向  0：从左向右 1：从右向左
	 * @param No 车编号
	 * @param PassengerNum 乘客数
	 */
	public void drawIVECO(int x,int y,Graphics g, int direct, String No, int PassengerNum){
		//设定车体颜色
		g.setColor(Color.yellow);

		//判断方向
		switch(direct){
		case 0: //从左向右
			//画车体
			int xPoints1[]={x+0, x+40, x+60, x+60, x+0};
			int yPoints1[]={y+0, y+0,  y+20, y+30, y+30};
			g.fillPolygon(xPoints1, yPoints1, 5);
			g.fillOval(x+10,  y+24, 12,  12);
			g.fillOval(x+38, y+24, 12,  12);

			//画编号
			g.setColor(Color.black);
			g.setFont(MyFonts.f3);
			g.drawString(No, x+5, y+13);

			//画人数
			g.drawString("PN:"+PassengerNum, x+5, y+28);

			break;
		case 1: //从右向左
			//画车体
			int xPoints2[]={x+20, x+60, x+60, x+0, x+0};
			int yPoints2[]={y+0, y+0,  y+30, y+30, y+20};

			g.fillPolygon(xPoints2, yPoints2, 5);
			g.fillOval(x+10,  y+24, 12,  12);
			g.fillOval(x+38, y+24, 12,  12);
			//画编号
			g.setColor(Color.black);
			g.setFont(MyFonts.f3);
			g.drawString(No, x+23, y+13);

			//画人数
			g.drawString("PN:"+PassengerNum, x+23, y+28);

			break;
		}
	}

	/**
	 * 画沃尔沃
	 * @param x 当前x坐标
	 * @param y 当前y坐标
	 * @param g 画笔
	 * @param direct 方向  0：从左向右 1：从右向左
	 * @param No 车编号
	 * @param PassengerNum 乘客数
	 */
	public void drawVOLVO(int x,int y,Graphics g, int direct, String No, int PassengerNum){

		//设定车体颜色
		g.setColor(Color.green);

		//画车体
		g.fillRect(x, y, 70, 30);
		g.fillOval(x+10,  y+24, 12,  12);
		g.fillOval(x+48, y+24, 12,  12);

		//画编号
		g.setColor(Color.black);
		g.setFont(MyFonts.f3);
		g.drawString(No, x+20, y+13);

		//画人数
		g.drawString("PN:"+PassengerNum, x+20, y+28);

		//判断方向
		switch(direct){
		case 0: //从左向右




			break;
		case 1: //从右向左

			break;
		}


	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//每个100ms去重绘车辆
		while(true){
			try {
				Thread.sleep(MyNumbers.milliSeconds);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			//System.out.println("我的面板线程启动");

			//重绘
			this.repaint();
		}
	}
}