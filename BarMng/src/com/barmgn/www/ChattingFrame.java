package com.barmgn.www;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;



public class ChattingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_InWriting;
	MainFrame mainframe;
	ChattingServer server;
	JTextArea txtDisplay;

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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 10, 158, 104);
		panel.add(scrollPane_1);
		
		JTextArea txtDisplay = new JTextArea();
		txtDisplay.setEnabled(false);
		scrollPane_1.setViewportView(txtDisplay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 277, 313);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton button = new JButton("<<<");
		button.setPreferredSize(new Dimension(50, 50));
		button.setBounds(197, 208, 80, 25);
		panel_1.add(button);
		
		JButton btnSendAll = new JButton("\uC804\uCCB4 \uBCF4\uB0B4\uAE30");
		btnSendAll.setBounds(152, 278, 125, 25);
		panel_1.add(btnSendAll);
		btnSendAll.setPreferredSize(new Dimension(100, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 274, 200);
		panel_1.add(scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		scrollPane.setViewportView(tabbedPane);
		
		JTextArea ta_All = new JTextArea();
		tabbedPane.addTab("전체", null, ta_All, null);
		ta_All.setTabSize(12);
		ta_All.setName("");
		
		JTextArea ta_Whisper = new JTextArea();
		tabbedPane.addTab("개인", null, ta_Whisper, null);
		
		tf_InWriting = new JTextField();
		tf_InWriting.setBounds(0, 209, 197, 24);
		panel_1.add(tf_InWriting);
		tf_InWriting.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(278, 260, 182, 52);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnBack = new JButton("\uC774\uC804");
		btnBack.setBounds(82, 27, 100, 25);
		btnBack.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_2.add(btnBack);
		
		setVisible(true);
		
		
		
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
		server = new ChattingServer(this);
		server.startServer();
		System.out.println("서버시작함수로");
		
		ChattingListener l = new ChattingListener(); 
		btnBack.addActionListener(l);
	}
	
	void displayText(String str){
		txtDisplay.append(str+"\n");
	}
	
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//	
//		
//	}
	
	class ChattingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String c_cmd = e.getActionCommand();
	
			switch(c_cmd)
			{
			case "이전":
				dispose();
				mainframe.setVisible(true);
				break;
			case "전체":
				break;
			case "개인":
				break;
			}
			
		}
		
	}
}
