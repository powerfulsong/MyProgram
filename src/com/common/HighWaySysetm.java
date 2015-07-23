package com.common;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.tools.*;
import com.manage.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HighWaySysetm extends JFrame implements ActionListener{
	Container ct=null;
	//演示面板
	MyPanel mp=null;

	//时间
	JLabel jl_showTime;
	JButton jb_start_suspend;
	static int countClickStartButton=0;

	//时钟
	Time time;
	Thread timeThread;

	//管理（线程、JLabel、初始状态、概率）
	ManageThread manageThread;
	ManageJLabel manageJLabel;
	ManageStartState manageStartState;
	ManageProbability manageProbability;
	ManageVehicles manageVehicles;

	//起始状态面板
	JPanel jp_initState;
	JLabel jl_XN_volvoNums,jl_XN_ivecoNums,jl_BJ_volvoNums,jl_BJ_ivecoNums,jl_PN;
	JTextField jtf_XN_volvoNums,jtf_XN_ivecoNums,jtf_BJ_volvoNums,jtf_BJ_ivecoNums,jtf_PN;
	int XN_volvoNums,XN_ivecoNums,BJ_volvoNums,BJ_ivecoNums,PN;
	//西安-宝鸡 概率面板
	JPanel jp_P_XB;
	JLabel jl_P_XB_xy,jl_P_XB_xp,jl_P_XB_wg,jl_P_XB_cp,jl_P_XB_gz,jl_P_XB_bj;
	JTextField jtf_P_XB_xy,jtf_P_XB_xp,jtf_P_XB_wg,jtf_P_XB_cp,jtf_P_XB_gz,jtf_P_XB_bj;
	double P_XB_xy,P_XB_xp,P_XB_wg,P_XB_cp,P_XB_gz,P_XB_bj;

	//宝鸡-西安 概率面板
	JPanel jp_P_BX;
	JLabel jl_P_BX_gz,jl_P_BX_cp,jl_P_BX_wg,jl_P_BX_xp,jl_P_BX_xy,jl_P_BX_xn;
	JTextField jtf_P_BX_gz,jtf_P_BX_cp,jtf_P_BX_wg,jtf_P_BX_xp,jtf_P_BX_xy,jtf_P_BX_xn;
	double P_BX_gz,P_BX_cp,P_BX_wg,P_BX_xp,P_BX_xy,P_BX_xn;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HighWaySysetm hws=new HighWaySysetm();
	}

	public HighWaySysetm(){
		ct=this.getContentPane();
		//创建线程管理类
		manageThread=new ManageThread();
		//创建JLabel管理类
		manageJLabel=new ManageJLabel();
		//创建起始状态管理类
		manageStartState=new ManageStartState();
		//创建概率管理类
		manageProbability=new ManageProbability();
		//创建管理车集合类
		manageVehicles=new ManageVehicles();
		
		this.setLayout(null); //设定布局为空布局

		//时间面板
		this.drawTimePanel();

		//起始状态面板
		this.drawInitPanel();

		//西安-宝鸡概率面板
		this.drawXBPanel();

		//宝鸡-西安概率面板
		this.drawBXPanel();

		//演示面板
		this.drawMyPanel();

		//整个窗体的设定
		this.setTitle("西宝高速公路客运活动仿真程序");
		this.setSize(865, 740);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 画时间面板
	 */
	public void drawTimePanel(){
		//时间显示
		jl_showTime=new JLabel("当前时间为："+(MyNumbers.time_start/60)+" : "+(MyNumbers.time_start%60));
		jl_showTime.setFont(MyFonts.f7);
		jl_showTime.setBounds(250, 5, 250, 30);
		ct.add(jl_showTime);
		//管理JLabel
		manageJLabel.addJLabel("Time", jl_showTime);

		//按钮
		jb_start_suspend=new JButton("开始");
		jb_start_suspend.setFocusPainted(false); //去掉jbutton旁边的边框
		jb_start_suspend.setFont(MyFonts.f7);
		jb_start_suspend.setBounds(505, 5, 100, 30);
		ct.add(jb_start_suspend);
		jb_start_suspend.addActionListener(this);

		//线程
		time=new Time();
		timeThread=new Thread(time);
		//将该线程加入到线程管理类中
		manageThread.addThread("timeThread", timeThread);
	}

	/**
	 * 画起始状态面板
	 */
	public void drawInitPanel(){
		jp_initState=new JPanel();

		jl_XN_volvoNums=new JLabel("沃尔沃(西安)数量:");
		jl_XN_volvoNums.setFont(MyFonts.f2);
		jtf_XN_volvoNums=new JTextField(2);
		jtf_XN_volvoNums.setText("5");
		jtf_XN_volvoNums.setHorizontalAlignment(JTextField.CENTER);  //文字居中

		jl_XN_ivecoNums=new JLabel(" 依维柯(西安)数量:");
		jl_XN_ivecoNums.setFont(MyFonts.f2);
		jtf_XN_ivecoNums=new JTextField(2);
		jtf_XN_ivecoNums.setText("12");
		jtf_XN_ivecoNums.setHorizontalAlignment(JTextField.CENTER);

		jl_BJ_volvoNums=new JLabel(" 沃尔沃(宝鸡)数量:");
		jl_BJ_volvoNums.setFont(MyFonts.f2);
		jtf_BJ_volvoNums=new JTextField(2);
		jtf_BJ_volvoNums.setText("4");
		jtf_BJ_volvoNums.setHorizontalAlignment(JTextField.CENTER);

		jl_BJ_ivecoNums=new JLabel(" 依维柯(宝鸡)数量:");
		jl_BJ_ivecoNums.setFont(MyFonts.f2);
		jtf_BJ_ivecoNums=new JTextField(2);
		jtf_BJ_ivecoNums.setText("15");
		jtf_BJ_ivecoNums.setHorizontalAlignment(JTextField.CENTER);

		jl_PN=new JLabel(" 最多新增乘客数(人/分):");
		jl_PN.setFont(MyFonts.f2);
		jtf_PN=new JTextField(2);
		jtf_PN.setText("2");
		jtf_PN.setHorizontalAlignment(JTextField.CENTER);

		jp_initState.add(jl_XN_volvoNums);
		jp_initState.add(jtf_XN_volvoNums);
		jp_initState.add(jl_XN_ivecoNums);
		jp_initState.add(jtf_XN_ivecoNums);
		jp_initState.add(jl_BJ_volvoNums);
		jp_initState.add(jtf_BJ_volvoNums);
		jp_initState.add(jl_BJ_ivecoNums);
		jp_initState.add(jtf_BJ_ivecoNums);
		jp_initState.add(jl_PN);
		jp_initState.add(jtf_PN);

		jp_initState.setBounds(0, 35, 860, 50);
		jp_initState.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "初始状态", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("宋体",Font.PLAIN,14), Color.black));

		ct.add(jp_initState);
	}

	/**
	 * 画西安-宝鸡的概率面板
	 */
	public void drawXBPanel(){
		jp_P_XB=new JPanel();

		jl_P_XB_xy=new JLabel("咸  阳:");
		jl_P_XB_xy.setFont(MyFonts.f2);
		jtf_P_XB_xy=new JTextField(3);
		jtf_P_XB_xy.setText("0.1");
		jtf_P_XB_xy.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_xp=new JLabel("      兴  平:");
		jl_P_XB_xp.setFont(MyFonts.f2);
		jtf_P_XB_xp=new JTextField(3);
		jtf_P_XB_xp.setText("0.1");
		jtf_P_XB_xp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_wg=new JLabel("      武  功:");
		jl_P_XB_wg.setFont(MyFonts.f2);
		jtf_P_XB_wg=new JTextField(3);
		jtf_P_XB_wg.setText("0.2");
		jtf_P_XB_wg.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_cp=new JLabel("      蔡家坡:");
		jl_P_XB_cp.setFont(MyFonts.f2);
		jtf_P_XB_cp=new JTextField(3);
		jtf_P_XB_cp.setText("0.2");
		jtf_P_XB_cp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_gz=new JLabel("      虢  镇:");
		jl_P_XB_gz.setFont(MyFonts.f2);
		jtf_P_XB_gz=new JTextField(3);
		jtf_P_XB_gz.setText("0.2");
		jtf_P_XB_gz.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_bj=new JLabel("      宝  鸡:");
		jl_P_XB_bj.setFont(MyFonts.f2);
		jtf_P_XB_bj=new JTextField(3);
		jtf_P_XB_bj.setText("0.2");
		jtf_P_XB_bj.setHorizontalAlignment(JTextField.CENTER);

		//加入面板
		jp_P_XB.add(jl_P_XB_xy);
		jp_P_XB.add(jtf_P_XB_xy);
		jp_P_XB.add(jl_P_XB_xp);
		jp_P_XB.add(jtf_P_XB_xp);
		jp_P_XB.add(jl_P_XB_wg);
		jp_P_XB.add(jtf_P_XB_wg);
		jp_P_XB.add(jl_P_XB_cp);
		jp_P_XB.add(jtf_P_XB_cp);
		jp_P_XB.add(jl_P_XB_gz);
		jp_P_XB.add(jtf_P_XB_gz);
		jp_P_XB.add(jl_P_XB_bj);
		jp_P_XB.add(jtf_P_XB_bj);

		jp_P_XB.setBounds(0, 90, 860, 50);
		jp_P_XB.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "下车概率(西安-->宝鸡)", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("宋体",Font.PLAIN,14), Color.black));

		ct.add(jp_P_XB);

	}

	/**
	 * 画宝鸡-西安的概率面板
	 */
	public void drawBXPanel(){
		jp_P_BX=new JPanel();

		jl_P_BX_gz=new JLabel("虢  镇:");
		jl_P_BX_gz.setFont(MyFonts.f2);
		jtf_P_BX_gz=new JTextField(3);
		jtf_P_BX_gz.setText("0.1");
		jtf_P_BX_gz.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_cp=new JLabel("      蔡家坡:");
		jl_P_BX_cp.setFont(MyFonts.f2);
		jtf_P_BX_cp=new JTextField(3);
		jtf_P_BX_cp.setText("0.1");
		jtf_P_BX_cp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_wg=new JLabel("      武  功:");
		jl_P_BX_wg.setFont(MyFonts.f2);
		jtf_P_BX_wg=new JTextField(3);
		jtf_P_BX_wg.setText("0.2");
		jtf_P_BX_wg.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_xp=new JLabel("      兴  平:");
		jl_P_BX_xp.setFont(MyFonts.f2);
		jtf_P_BX_xp=new JTextField(3);
		jtf_P_BX_xp.setText("0.2");
		jtf_P_BX_xp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_xy=new JLabel("      咸  阳:");
		jl_P_BX_xy.setFont(MyFonts.f2);
		jtf_P_BX_xy=new JTextField(3);
		jtf_P_BX_xy.setText("0.2");
		jtf_P_BX_xy.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_xn=new JLabel("      西  安:");
		jl_P_BX_xn.setFont(MyFonts.f2);
		jtf_P_BX_xn=new JTextField(3);
		jtf_P_BX_xn.setText("0.2");
		jtf_P_BX_xn.setHorizontalAlignment(JTextField.CENTER);

		//加入面板
		jp_P_BX.add(jl_P_BX_gz);
		jp_P_BX.add(jtf_P_BX_gz);
		jp_P_BX.add(jl_P_BX_cp);
		jp_P_BX.add(jtf_P_BX_cp);
		jp_P_BX.add(jl_P_BX_wg);
		jp_P_BX.add(jtf_P_BX_wg);
		jp_P_BX.add(jl_P_BX_xp);
		jp_P_BX.add(jtf_P_BX_xp);
		jp_P_BX.add(jl_P_BX_xy);
		jp_P_BX.add(jtf_P_BX_xy);
		jp_P_BX.add(jl_P_BX_xn);
		jp_P_BX.add(jtf_P_BX_xn);

		jp_P_BX.setBounds(0, 145, 860, 50);
		jp_P_BX.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "下车概率(宝鸡-->西安)", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("宋体",Font.PLAIN,14), Color.black));

		ct.add(jp_P_BX);
	}


	/**
	 * 设定已经正确设定的数据不可更改
	 */
	public void setJTextFieldNoChange(){

		jtf_XN_volvoNums.setEditable(false);
		jtf_XN_ivecoNums.setEditable(false);
		jtf_BJ_volvoNums.setEditable(false);
		jtf_BJ_ivecoNums.setEditable(false);
		jtf_PN.setEditable(false);

		jtf_P_XB_xy.setEditable(false);
		jtf_P_XB_xp.setEditable(false);
		jtf_P_XB_wg.setEditable(false);
		jtf_P_XB_cp.setEditable(false);
		jtf_P_XB_gz.setEditable(false);
		jtf_P_XB_bj.setEditable(false);

		jtf_P_BX_gz.setEditable(false);
		jtf_P_BX_cp.setEditable(false);
		jtf_P_BX_wg.setEditable(false);
		jtf_P_BX_xp.setEditable(false);
		jtf_P_BX_xy.setEditable(false);
		jtf_P_BX_xn.setEditable(false);
	}

	/**
	 * 获取初始状态数据，并加入到管理初始数据类中
	 */
	public void getStartState(){
		XN_volvoNums=Integer.parseInt(jtf_XN_volvoNums.getText().trim());
		XN_ivecoNums=Integer.parseInt(jtf_XN_ivecoNums.getText().trim());
		BJ_volvoNums=Integer.parseInt(jtf_BJ_volvoNums.getText().trim());
		BJ_ivecoNums=Integer.parseInt(jtf_BJ_ivecoNums.getText().trim());
		PN=Integer.parseInt(jtf_PN.getText().trim());
		
		//将数据加入到管理初始状态管理类中
		manageStartState.addStartState("XN_volvoNums", XN_volvoNums);
		manageStartState.addStartState("XN_ivecoNums", XN_ivecoNums);
		manageStartState.addStartState("BJ_volvoNums", BJ_volvoNums);
		manageStartState.addStartState("BJ_ivecoNums", BJ_ivecoNums);
		manageStartState.addStartState("PN", PN);
	}
	
	/**
	 * 计算西安—>宝鸡的概率和,并加入到概率管理类中
	 * @return 
	 */
	public double get_P_XB_sum(){
		double res=0.0;
		P_XB_xy=Double.parseDouble(jtf_P_XB_xy.getText().trim());
		P_XB_xp=Double.parseDouble(jtf_P_XB_xp.getText().trim());
		P_XB_wg=Double.parseDouble(jtf_P_XB_wg.getText().trim());
		P_XB_cp=Double.parseDouble(jtf_P_XB_cp.getText().trim());
		P_XB_gz=Double.parseDouble(jtf_P_XB_gz.getText().trim());
		P_XB_bj=Double.parseDouble(jtf_P_XB_bj.getText().trim());
		//System.out.println(P_XB_xy+" "+P_XB_xp+" "+P_XB_wg+" "+P_XB_cp+" "+P_XB_gz+" "+P_XB_bj);
		
		//将数据加入到管理概率类中
		manageProbability.addProbability("P_XB_xy", P_XB_xy);
		manageProbability.addProbability("P_XB_xp", P_XB_xp);
		manageProbability.addProbability("P_XB_wg", P_XB_wg);
		manageProbability.addProbability("P_XB_cp", P_XB_cp);
		manageProbability.addProbability("P_XB_gz", P_XB_gz);
		manageProbability.addProbability("P_XB_bj", P_XB_bj);
		
		res=P_XB_xy+P_XB_xp+P_XB_wg+P_XB_cp+P_XB_gz+P_XB_bj;
		return res;
	}

	/**
	 * 计算宝鸡—>西安的概率和，并加入到概率管理类中
	 * @return 
	 */
	public double get_P_BX_sum(){
		double res=0.0;
		P_BX_gz=Double.parseDouble(jtf_P_BX_gz.getText().trim());
		P_BX_cp=Double.parseDouble(jtf_P_BX_cp.getText().trim());
		P_BX_wg=Double.parseDouble(jtf_P_BX_wg.getText().trim());
		P_BX_xp=Double.parseDouble(jtf_P_BX_xp.getText().trim());
		P_BX_xy=Double.parseDouble(jtf_P_BX_xy.getText().trim());
		P_BX_xn=Double.parseDouble(jtf_P_BX_xn.getText().trim());
		//System.out.println(P_BX_gz+" "+P_BX_cp+" "+P_BX_wg+" "+P_BX_xp+" "+P_BX_xy+" "+P_BX_xn);
		
		//将数据加入到管理概率类中
		manageProbability.addProbability("P_BX_gz", P_BX_gz);
		manageProbability.addProbability("P_BX_cp", P_BX_cp);
		manageProbability.addProbability("P_BX_wg", P_BX_wg);
		manageProbability.addProbability("P_BX_xp", P_BX_xp);
		manageProbability.addProbability("P_BX_xy", P_BX_xy);
		manageProbability.addProbability("P_BX_xn", P_BX_xn);
		
		res=P_BX_gz+P_BX_cp+P_BX_wg+P_BX_xp+P_BX_xy+P_BX_xn;
		return res;
	}

	/**
	 * 画演示面板
	 */
	public void drawMyPanel(){
		//演示面板
		mp=new MyPanel();
		//仿真面板模块
		mp.setBounds(0, 200, 860, 510);
		mp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "仿真结果", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("宋体",Font.PLAIN,14), Color.black));
		ct.add(mp);

		//创建面板线程，刷新面板
		Thread t_mp=new Thread(mp);
		//将该线程加入到线程管理类中
		manageThread.addThread("myPanelThread", t_mp);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb_start_suspend){ //点击开始&暂停
			//创建时间线程
			Thread t_time=ManageThread.getThread("timeThread");
			//创建面板线程
			Thread t_mp=ManageThread.getThread("myPanelThread");
			
			//获取车集合类对象
			Vector<IVECO> BX_ivecos=manageVehicles.getVehicles("BX_ivecos");
			Vector<VOLVO> BX_volvos=manageVehicles.getVehicles("BX_volvos");
			Vector<IVECO> XB_ivecos=manageVehicles.getVehicles("XB_ivecos");
			Vector<VOLVO> XB_volvos=manageVehicles.getVehicles("XB_volvos");
			
			countClickStartButton++;
			if(countClickStartButton == 1){ //第一次点击，开启线程
				//System.out.println("第一次点击");
				//System.out.println("1.点击次数为："+countClickStartButton);

				//先判断输入数据是否为空
				if((jtf_XN_volvoNums.getText().trim()).equals("") || (jtf_XN_ivecoNums.getText().trim()).equals("")
						||(jtf_BJ_volvoNums.getText().trim()).equals("")||(jtf_BJ_ivecoNums.getText().trim()).equals("")
						||(jtf_PN.getText().trim()).equals("")
						||(jtf_P_XB_xy.getText().trim()).equals("") ||(jtf_P_XB_xp.getText().trim()).equals("")
						||(jtf_P_XB_wg.getText().trim()).equals("") ||(jtf_P_XB_cp.getText().trim()).equals("")
						||(jtf_P_XB_gz.getText().trim()).equals("") ||(jtf_P_XB_bj.getText().trim()).equals("")
						||(jtf_P_BX_gz.getText().trim()).equals("") ||(jtf_P_BX_cp.getText().trim()).equals("")
						||(jtf_P_BX_wg.getText().trim()).equals("") ||(jtf_P_BX_xp.getText().trim()).equals("")
						||(jtf_P_BX_xy.getText().trim()).equals("") ||(jtf_P_BX_xn.getText().trim()).equals("")
						){ //输入数据为空，提示重新输入
					countClickStartButton--; //清除点击次数
					JLabel show_message=new JLabel("初始值中存在为空的数据，请补充完整！");
					show_message.setFont(MyFonts.f2);
					JOptionPane.showMessageDialog(this, show_message);
				}else{ //输入数据不为空
					this.getStartState();  //获取初始状态
					double P_XB = this.get_P_XB_sum(); //西安->宝鸡的概率和
					double P_BX = this.get_P_BX_sum(); //宝鸡->西安的概率和
					//System.out.println(P_XB+"  "+P_BX);

					if((P_XB != 1.0) || (P_BX!=1.0)){ //如果概率和不为1，提示概率设置错误，请重新设置
						countClickStartButton--; //清除点击次数
						JLabel show_message=new JLabel("概率和不为1，请重新设置概率！");
						show_message.setFont(MyFonts.f2);
						JOptionPane.showMessageDialog(this, show_message);
					}else{ //概率和为1，可以开始程序运行
						//设定程序数据不可更改，以防用户临时更改数据，造成程序运行错误
						this.setJTextFieldNoChange();

						jb_start_suspend.setText("暂停");
						t_time.start(); //时间线程开启

						//时间线程已开启，可以再后续添加代码……(在此处开启其他线程)	
						t_mp.start(); //刷新我的面板刷新线程开启

					}

				}

			}else if((countClickStartButton > 1) && (countClickStartButton%2==0)){ //第 2n 次点击挂起线程
				//System.out.println("第 2n 次点击");
				//System.out.println("2.点击次数为："+countClickStartButton);
				jb_start_suspend.setText("开始");

				t_time.suspend(); //时间线程停止
				t_mp.suspend();  //刷新我的面板刷新线程停止
				
				//西安->宝鸡 iveco 线程停止
				for(int i=0; i<XB_ivecos.size(); i++){
					//获取西安->宝鸡 依维柯的线程
					Thread t_xb_iveco=manageThread.getThread(XB_ivecos.get(i).getNo());
					t_xb_iveco.suspend();
				}
				
				//西安->宝鸡 volvo 线程停止
				for(int j=0; j<XB_volvos.size(); j++){
					Thread t_xb_volvo=manageThread.getThread(XB_volvos.get(j).getNo());
					t_xb_volvo.suspend();
				}
				
				//宝鸡 -> 西安 iveco 线程停止
				for(int m=0; m<BX_ivecos.size(); m++){
					Thread t_bx_iveco=manageThread.getThread(BX_ivecos.get(m).getNo());
					t_bx_iveco.suspend();
				}
				
				//宝鸡-> 西安 volvo 线程停止
				for(int n=0; n<BX_volvos.size(); n++){
					Thread t_bx_volvo=manageThread.getThread(BX_volvos.get(n).getNo());
					t_bx_volvo.suspend();
				}
				//时间线程已停止，可以再后续添加代码……(在此处停止其他线程)

			}else if((countClickStartButton > 1) && (countClickStartButton%2==1)){ //第（2n+1）次点击激活线程
				//System.out.println("第 2n+1 次点击");
				//System.out.println("3.点击次数为："+countClickStartButton);
				jb_start_suspend.setText("暂停");

				t_time.resume(); //时间线程继续运行
				t_mp.resume();   //刷新我的面板刷新线程继续运行
				
				//西安->宝鸡 iveco 线程继续运行
				for(int i=0; i<XB_ivecos.size(); i++){
					//获取西安->宝鸡 依维柯的线程
					Thread t_xb_iveco=manageThread.getThread(XB_ivecos.get(i).getNo());
					t_xb_iveco.resume();
				}
				
				//西安->宝鸡 volvo 线程继续运行
				for(int j=0; j<XB_volvos.size(); j++){
					Thread t_xb_volvo=manageThread.getThread(XB_volvos.get(j).getNo());
					t_xb_volvo.resume();
				}
				
				//宝鸡 -> 西安 iveco 线程继续运行
				for(int m=0; m<BX_ivecos.size(); m++){
					Thread t_bx_iveco=manageThread.getThread(BX_ivecos.get(m).getNo());
					t_bx_iveco.resume();
				}
				
				//宝鸡-> 西安 volvo 线程继续运行
				for(int n=0; n<BX_volvos.size(); n++){
					Thread t_bx_volvo=manageThread.getThread(BX_volvos.get(n).getNo());
					t_bx_volvo.resume();
				}
				//时间线程已继续运行，可以再后续添加代码……(在此处继续运行其他线程)
			}
		}
	}
}

