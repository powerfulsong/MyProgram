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
	//��ʾ���
	MyPanel mp=null;

	//ʱ��
	JLabel jl_showTime;
	JButton jb_start_suspend;
	static int countClickStartButton=0;

	//ʱ��
	Time time;
	Thread timeThread;

	//�����̡߳�JLabel����ʼ״̬�����ʣ�
	ManageThread manageThread;
	ManageJLabel manageJLabel;
	ManageStartState manageStartState;
	ManageProbability manageProbability;
	ManageVehicles manageVehicles;

	//��ʼ״̬���
	JPanel jp_initState;
	JLabel jl_XN_volvoNums,jl_XN_ivecoNums,jl_BJ_volvoNums,jl_BJ_ivecoNums,jl_PN;
	JTextField jtf_XN_volvoNums,jtf_XN_ivecoNums,jtf_BJ_volvoNums,jtf_BJ_ivecoNums,jtf_PN;
	int XN_volvoNums,XN_ivecoNums,BJ_volvoNums,BJ_ivecoNums,PN;
	//����-���� �������
	JPanel jp_P_XB;
	JLabel jl_P_XB_xy,jl_P_XB_xp,jl_P_XB_wg,jl_P_XB_cp,jl_P_XB_gz,jl_P_XB_bj;
	JTextField jtf_P_XB_xy,jtf_P_XB_xp,jtf_P_XB_wg,jtf_P_XB_cp,jtf_P_XB_gz,jtf_P_XB_bj;
	double P_XB_xy,P_XB_xp,P_XB_wg,P_XB_cp,P_XB_gz,P_XB_bj;

	//����-���� �������
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
		//�����̹߳�����
		manageThread=new ManageThread();
		//����JLabel������
		manageJLabel=new ManageJLabel();
		//������ʼ״̬������
		manageStartState=new ManageStartState();
		//�������ʹ�����
		manageProbability=new ManageProbability();
		//��������������
		manageVehicles=new ManageVehicles();
		
		this.setLayout(null); //�趨����Ϊ�ղ���

		//ʱ�����
		this.drawTimePanel();

		//��ʼ״̬���
		this.drawInitPanel();

		//����-�����������
		this.drawXBPanel();

		//����-�����������
		this.drawBXPanel();

		//��ʾ���
		this.drawMyPanel();

		//����������趨
		this.setTitle("�������ٹ�·���˻�������");
		this.setSize(865, 740);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * ��ʱ�����
	 */
	public void drawTimePanel(){
		//ʱ����ʾ
		jl_showTime=new JLabel("��ǰʱ��Ϊ��"+(MyNumbers.time_start/60)+" : "+(MyNumbers.time_start%60));
		jl_showTime.setFont(MyFonts.f7);
		jl_showTime.setBounds(250, 5, 250, 30);
		ct.add(jl_showTime);
		//����JLabel
		manageJLabel.addJLabel("Time", jl_showTime);

		//��ť
		jb_start_suspend=new JButton("��ʼ");
		jb_start_suspend.setFocusPainted(false); //ȥ��jbutton�Աߵı߿�
		jb_start_suspend.setFont(MyFonts.f7);
		jb_start_suspend.setBounds(505, 5, 100, 30);
		ct.add(jb_start_suspend);
		jb_start_suspend.addActionListener(this);

		//�߳�
		time=new Time();
		timeThread=new Thread(time);
		//�����̼߳��뵽�̹߳�������
		manageThread.addThread("timeThread", timeThread);
	}

	/**
	 * ����ʼ״̬���
	 */
	public void drawInitPanel(){
		jp_initState=new JPanel();

		jl_XN_volvoNums=new JLabel("�ֶ���(����)����:");
		jl_XN_volvoNums.setFont(MyFonts.f2);
		jtf_XN_volvoNums=new JTextField(2);
		jtf_XN_volvoNums.setText("5");
		jtf_XN_volvoNums.setHorizontalAlignment(JTextField.CENTER);  //���־���

		jl_XN_ivecoNums=new JLabel(" ��ά��(����)����:");
		jl_XN_ivecoNums.setFont(MyFonts.f2);
		jtf_XN_ivecoNums=new JTextField(2);
		jtf_XN_ivecoNums.setText("12");
		jtf_XN_ivecoNums.setHorizontalAlignment(JTextField.CENTER);

		jl_BJ_volvoNums=new JLabel(" �ֶ���(����)����:");
		jl_BJ_volvoNums.setFont(MyFonts.f2);
		jtf_BJ_volvoNums=new JTextField(2);
		jtf_BJ_volvoNums.setText("4");
		jtf_BJ_volvoNums.setHorizontalAlignment(JTextField.CENTER);

		jl_BJ_ivecoNums=new JLabel(" ��ά��(����)����:");
		jl_BJ_ivecoNums.setFont(MyFonts.f2);
		jtf_BJ_ivecoNums=new JTextField(2);
		jtf_BJ_ivecoNums.setText("15");
		jtf_BJ_ivecoNums.setHorizontalAlignment(JTextField.CENTER);

		jl_PN=new JLabel(" ��������˿���(��/��):");
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
		jp_initState.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "��ʼ״̬", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("����",Font.PLAIN,14), Color.black));

		ct.add(jp_initState);
	}

	/**
	 * ������-�����ĸ������
	 */
	public void drawXBPanel(){
		jp_P_XB=new JPanel();

		jl_P_XB_xy=new JLabel("��  ��:");
		jl_P_XB_xy.setFont(MyFonts.f2);
		jtf_P_XB_xy=new JTextField(3);
		jtf_P_XB_xy.setText("0.1");
		jtf_P_XB_xy.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_xp=new JLabel("      ��  ƽ:");
		jl_P_XB_xp.setFont(MyFonts.f2);
		jtf_P_XB_xp=new JTextField(3);
		jtf_P_XB_xp.setText("0.1");
		jtf_P_XB_xp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_wg=new JLabel("      ��  ��:");
		jl_P_XB_wg.setFont(MyFonts.f2);
		jtf_P_XB_wg=new JTextField(3);
		jtf_P_XB_wg.setText("0.2");
		jtf_P_XB_wg.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_cp=new JLabel("      �̼���:");
		jl_P_XB_cp.setFont(MyFonts.f2);
		jtf_P_XB_cp=new JTextField(3);
		jtf_P_XB_cp.setText("0.2");
		jtf_P_XB_cp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_gz=new JLabel("      �  ��:");
		jl_P_XB_gz.setFont(MyFonts.f2);
		jtf_P_XB_gz=new JTextField(3);
		jtf_P_XB_gz.setText("0.2");
		jtf_P_XB_gz.setHorizontalAlignment(JTextField.CENTER);

		jl_P_XB_bj=new JLabel("      ��  ��:");
		jl_P_XB_bj.setFont(MyFonts.f2);
		jtf_P_XB_bj=new JTextField(3);
		jtf_P_XB_bj.setText("0.2");
		jtf_P_XB_bj.setHorizontalAlignment(JTextField.CENTER);

		//�������
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
		jp_P_XB.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "�³�����(����-->����)", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("����",Font.PLAIN,14), Color.black));

		ct.add(jp_P_XB);

	}

	/**
	 * ������-�����ĸ������
	 */
	public void drawBXPanel(){
		jp_P_BX=new JPanel();

		jl_P_BX_gz=new JLabel("�  ��:");
		jl_P_BX_gz.setFont(MyFonts.f2);
		jtf_P_BX_gz=new JTextField(3);
		jtf_P_BX_gz.setText("0.1");
		jtf_P_BX_gz.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_cp=new JLabel("      �̼���:");
		jl_P_BX_cp.setFont(MyFonts.f2);
		jtf_P_BX_cp=new JTextField(3);
		jtf_P_BX_cp.setText("0.1");
		jtf_P_BX_cp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_wg=new JLabel("      ��  ��:");
		jl_P_BX_wg.setFont(MyFonts.f2);
		jtf_P_BX_wg=new JTextField(3);
		jtf_P_BX_wg.setText("0.2");
		jtf_P_BX_wg.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_xp=new JLabel("      ��  ƽ:");
		jl_P_BX_xp.setFont(MyFonts.f2);
		jtf_P_BX_xp=new JTextField(3);
		jtf_P_BX_xp.setText("0.2");
		jtf_P_BX_xp.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_xy=new JLabel("      ��  ��:");
		jl_P_BX_xy.setFont(MyFonts.f2);
		jtf_P_BX_xy=new JTextField(3);
		jtf_P_BX_xy.setText("0.2");
		jtf_P_BX_xy.setHorizontalAlignment(JTextField.CENTER);

		jl_P_BX_xn=new JLabel("      ��  ��:");
		jl_P_BX_xn.setFont(MyFonts.f2);
		jtf_P_BX_xn=new JTextField(3);
		jtf_P_BX_xn.setText("0.2");
		jtf_P_BX_xn.setHorizontalAlignment(JTextField.CENTER);

		//�������
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
		jp_P_BX.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "�³�����(����-->����)", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("����",Font.PLAIN,14), Color.black));

		ct.add(jp_P_BX);
	}


	/**
	 * �趨�Ѿ���ȷ�趨�����ݲ��ɸ���
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
	 * ��ȡ��ʼ״̬���ݣ������뵽�����ʼ��������
	 */
	public void getStartState(){
		XN_volvoNums=Integer.parseInt(jtf_XN_volvoNums.getText().trim());
		XN_ivecoNums=Integer.parseInt(jtf_XN_ivecoNums.getText().trim());
		BJ_volvoNums=Integer.parseInt(jtf_BJ_volvoNums.getText().trim());
		BJ_ivecoNums=Integer.parseInt(jtf_BJ_ivecoNums.getText().trim());
		PN=Integer.parseInt(jtf_PN.getText().trim());
		
		//�����ݼ��뵽�����ʼ״̬��������
		manageStartState.addStartState("XN_volvoNums", XN_volvoNums);
		manageStartState.addStartState("XN_ivecoNums", XN_ivecoNums);
		manageStartState.addStartState("BJ_volvoNums", BJ_volvoNums);
		manageStartState.addStartState("BJ_ivecoNums", BJ_ivecoNums);
		manageStartState.addStartState("PN", PN);
	}
	
	/**
	 * ����������>�����ĸ��ʺ�,�����뵽���ʹ�������
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
		
		//�����ݼ��뵽�����������
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
	 * ���㱦����>�����ĸ��ʺͣ������뵽���ʹ�������
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
		
		//�����ݼ��뵽�����������
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
	 * ����ʾ���
	 */
	public void drawMyPanel(){
		//��ʾ���
		mp=new MyPanel();
		//�������ģ��
		mp.setBounds(0, 200, 860, 510);
		mp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "������", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("����",Font.PLAIN,14), Color.black));
		ct.add(mp);

		//��������̣߳�ˢ�����
		Thread t_mp=new Thread(mp);
		//�����̼߳��뵽�̹߳�������
		manageThread.addThread("myPanelThread", t_mp);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb_start_suspend){ //�����ʼ&��ͣ
			//����ʱ���߳�
			Thread t_time=ManageThread.getThread("timeThread");
			//��������߳�
			Thread t_mp=ManageThread.getThread("myPanelThread");
			
			//��ȡ�����������
			Vector<IVECO> BX_ivecos=manageVehicles.getVehicles("BX_ivecos");
			Vector<VOLVO> BX_volvos=manageVehicles.getVehicles("BX_volvos");
			Vector<IVECO> XB_ivecos=manageVehicles.getVehicles("XB_ivecos");
			Vector<VOLVO> XB_volvos=manageVehicles.getVehicles("XB_volvos");
			
			countClickStartButton++;
			if(countClickStartButton == 1){ //��һ�ε���������߳�
				//System.out.println("��һ�ε��");
				//System.out.println("1.�������Ϊ��"+countClickStartButton);

				//���ж����������Ƿ�Ϊ��
				if((jtf_XN_volvoNums.getText().trim()).equals("") || (jtf_XN_ivecoNums.getText().trim()).equals("")
						||(jtf_BJ_volvoNums.getText().trim()).equals("")||(jtf_BJ_ivecoNums.getText().trim()).equals("")
						||(jtf_PN.getText().trim()).equals("")
						||(jtf_P_XB_xy.getText().trim()).equals("") ||(jtf_P_XB_xp.getText().trim()).equals("")
						||(jtf_P_XB_wg.getText().trim()).equals("") ||(jtf_P_XB_cp.getText().trim()).equals("")
						||(jtf_P_XB_gz.getText().trim()).equals("") ||(jtf_P_XB_bj.getText().trim()).equals("")
						||(jtf_P_BX_gz.getText().trim()).equals("") ||(jtf_P_BX_cp.getText().trim()).equals("")
						||(jtf_P_BX_wg.getText().trim()).equals("") ||(jtf_P_BX_xp.getText().trim()).equals("")
						||(jtf_P_BX_xy.getText().trim()).equals("") ||(jtf_P_BX_xn.getText().trim()).equals("")
						){ //��������Ϊ�գ���ʾ��������
					countClickStartButton--; //����������
					JLabel show_message=new JLabel("��ʼֵ�д���Ϊ�յ����ݣ��벹��������");
					show_message.setFont(MyFonts.f2);
					JOptionPane.showMessageDialog(this, show_message);
				}else{ //�������ݲ�Ϊ��
					this.getStartState();  //��ȡ��ʼ״̬
					double P_XB = this.get_P_XB_sum(); //����->�����ĸ��ʺ�
					double P_BX = this.get_P_BX_sum(); //����->�����ĸ��ʺ�
					//System.out.println(P_XB+"  "+P_BX);

					if((P_XB != 1.0) || (P_BX!=1.0)){ //������ʺͲ�Ϊ1����ʾ�������ô�������������
						countClickStartButton--; //����������
						JLabel show_message=new JLabel("���ʺͲ�Ϊ1�����������ø��ʣ�");
						show_message.setFont(MyFonts.f2);
						JOptionPane.showMessageDialog(this, show_message);
					}else{ //���ʺ�Ϊ1�����Կ�ʼ��������
						//�趨�������ݲ��ɸ��ģ��Է��û���ʱ�������ݣ���ɳ������д���
						this.setJTextFieldNoChange();

						jb_start_suspend.setText("��ͣ");
						t_time.start(); //ʱ���߳̿���

						//ʱ���߳��ѿ����������ٺ�����Ӵ��롭��(�ڴ˴����������߳�)	
						t_mp.start(); //ˢ���ҵ����ˢ���߳̿���

					}

				}

			}else if((countClickStartButton > 1) && (countClickStartButton%2==0)){ //�� 2n �ε�������߳�
				//System.out.println("�� 2n �ε��");
				//System.out.println("2.�������Ϊ��"+countClickStartButton);
				jb_start_suspend.setText("��ʼ");

				t_time.suspend(); //ʱ���߳�ֹͣ
				t_mp.suspend();  //ˢ���ҵ����ˢ���߳�ֹͣ
				
				//����->���� iveco �߳�ֹͣ
				for(int i=0; i<XB_ivecos.size(); i++){
					//��ȡ����->���� ��ά�µ��߳�
					Thread t_xb_iveco=manageThread.getThread(XB_ivecos.get(i).getNo());
					t_xb_iveco.suspend();
				}
				
				//����->���� volvo �߳�ֹͣ
				for(int j=0; j<XB_volvos.size(); j++){
					Thread t_xb_volvo=manageThread.getThread(XB_volvos.get(j).getNo());
					t_xb_volvo.suspend();
				}
				
				//���� -> ���� iveco �߳�ֹͣ
				for(int m=0; m<BX_ivecos.size(); m++){
					Thread t_bx_iveco=manageThread.getThread(BX_ivecos.get(m).getNo());
					t_bx_iveco.suspend();
				}
				
				//����-> ���� volvo �߳�ֹͣ
				for(int n=0; n<BX_volvos.size(); n++){
					Thread t_bx_volvo=manageThread.getThread(BX_volvos.get(n).getNo());
					t_bx_volvo.suspend();
				}
				//ʱ���߳���ֹͣ�������ٺ�����Ӵ��롭��(�ڴ˴�ֹͣ�����߳�)

			}else if((countClickStartButton > 1) && (countClickStartButton%2==1)){ //�ڣ�2n+1���ε�������߳�
				//System.out.println("�� 2n+1 �ε��");
				//System.out.println("3.�������Ϊ��"+countClickStartButton);
				jb_start_suspend.setText("��ͣ");

				t_time.resume(); //ʱ���̼߳�������
				t_mp.resume();   //ˢ���ҵ����ˢ���̼߳�������
				
				//����->���� iveco �̼߳�������
				for(int i=0; i<XB_ivecos.size(); i++){
					//��ȡ����->���� ��ά�µ��߳�
					Thread t_xb_iveco=manageThread.getThread(XB_ivecos.get(i).getNo());
					t_xb_iveco.resume();
				}
				
				//����->���� volvo �̼߳�������
				for(int j=0; j<XB_volvos.size(); j++){
					Thread t_xb_volvo=manageThread.getThread(XB_volvos.get(j).getNo());
					t_xb_volvo.resume();
				}
				
				//���� -> ���� iveco �̼߳�������
				for(int m=0; m<BX_ivecos.size(); m++){
					Thread t_bx_iveco=manageThread.getThread(BX_ivecos.get(m).getNo());
					t_bx_iveco.resume();
				}
				
				//����-> ���� volvo �̼߳�������
				for(int n=0; n<BX_volvos.size(); n++){
					Thread t_bx_volvo=manageThread.getThread(BX_volvos.get(n).getNo());
					t_bx_volvo.resume();
				}
				//ʱ���߳��Ѽ������У������ٺ�����Ӵ��롭��(�ڴ˴��������������߳�)
			}
		}
	}
}

