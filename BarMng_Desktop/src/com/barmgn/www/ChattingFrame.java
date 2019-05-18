package com.barmgn.www;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/* Server Frame */

public class ChattingFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tf_InputMessage;
	MainFrame mainframe;
	JTextArea txtDisplay;
	JTextField txtNickName;
	JButton btnSend;
	JButton btnBack;
	JTabbedPane tabbedPane;
	JTextArea ta_All;
	JTextArea ta_Whisper;
	ChattingClient client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattingFrame frame = new ChattingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
//	public ChattingFrame(MainFrame mainframe) {
	public ChattingFrame() {
//		this.mainframe = mainframe;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(280, 0, 182, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrP_txtDisplay = new JScrollPane();
		scrP_txtDisplay.setBounds(12, 10, 158, 104);
		panel.add(scrP_txtDisplay);
		
		txtDisplay = new JTextArea();
		txtDisplay.setColumns(10);
		txtDisplay.setEnabled(false);
		scrP_txtDisplay.setViewportView(txtDisplay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 277, 313);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnSend = new JButton("보내기");
		btnSend.setPreferredSize(new Dimension(50, 50));
		btnSend.setBounds(197, 208, 80, 25);
		panel_1.add(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 274, 200);
		panel_1.add(scrollPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		scrollPane.setViewportView(tabbedPane);
		
		ta_All = new JTextArea();
		tabbedPane.addTab("전체", null, ta_All, null);
		ta_All.setTabSize(12);
		ta_All.setName("");
		
		ta_Whisper = new JTextArea();
		tabbedPane.addTab("개인", null, ta_Whisper, null);
		
		tf_InputMessage = new JTextField();
		tf_InputMessage.setBounds(0, 209, 197, 24);
		panel_1.add(tf_InputMessage);
		tf_InputMessage.setColumns(10);
		
		JLabel lblNickName = new JLabel("닉네임");
		lblNickName.setBounds(12, 257, 43, 15);
		panel_1.add(lblNickName);
		
		txtNickName = new JTextField();
		txtNickName.setBounds(67, 254, 60, 21);
		panel_1.add(txtNickName);
		txtNickName.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(278, 260, 182, 52);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnBack = new JButton("\uC774\uC804");
		btnBack.setBounds(82, 27, 100, 25);
		btnBack.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_2.add(btnBack);
		
		setVisible(true);
		
		// input textfield : tf_InWriting focus
		tf_InputMessage.requestFocus();
		
		/*	시작 ChattingFrame 띄우자마자*/ 
		/*
		 * 1. 서버 시작 
		 * 2. 클라이언트 25개 시작 , 맨처음 커서는 textField요소의 tf_InWriting 에 커서가 가게끔 해놓아야
		 * 사용자가 편함. 커서가게하는 클래스는 뭐더랑. 
		 * 3. 기본기능 3-1 메세지 쓰고 엔터(편의성) : 타겟을 textArea요소의 ta_All
		 * 와 ta_Whisper 둘중 하나에 입력이 되어야 함.
		 * 
		 * ps. ta_Whisper 는 테이블을 선택하는 프레임에서 선택을 하고 추가요금 프레임 팝업
		 */
		
		// Server 쪽에서 Socket 을 생성 ( port 번호, address를 부여해야함 )
		// 
		
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			client= new ChattingClient(this);
//			String address=ia.toString();
			String address = "127.0.0.1";
			System.out.println(address);
			client.startClient(address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnBack.addActionListener(this);
		tf_InputMessage.addActionListener(this);
		btnSend.addActionListener(this);
	}
	
	void displayText(String str){
		txtDisplay.append(str+"\n");
	}	
	
	void displayTextAll(String str) {
		ta_All.append(str+"\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

			try {
				if(e.getSource()==btnBack)
				{
					client.stopClient();
					dispose();
					mainframe.setVisible(true);				
				}
				else if(e.getSource()==tf_InputMessage)
				{
					System.out.println("엔터!");
					String name=txtNickName.getText().toString();
					String data=tf_InputMessage.getText().toString();
					client.send(this, name+">>"+data);
					tf_InputMessage.setText("");
				}
				else if(e.getSource()==btnSend)
				{
					System.out.println("보내기!");
					String name=txtNickName.getText().toString();
					String data=tf_InputMessage.getText().toString();
					client.send(this, name+">>"+data);
					tf_InputMessage.setText("");
				}			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	
		}
}

