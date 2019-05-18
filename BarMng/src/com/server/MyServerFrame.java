package com.server;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyServerFrame extends JFrame implements ActionListener{
	TextArea txtDisplay;
	JButton btnStartStop;
	ChattingServer server;
	Socket socket;
	public MyServerFrame(){
		setTitle("채팅 서버");
		txtDisplay=new TextArea();
		txtDisplay.setEnabled(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnStartStop=new JButton("start");
		btnStartStop.addActionListener(this);
		add(txtDisplay,BorderLayout.CENTER);
		add(btnStartStop,BorderLayout.SOUTH);
		setSize(600,400);
		setVisible(true);
		server=new ChattingServer(this);
	}
	
	
	void displayText(String str){
		txtDisplay.append(str+"\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	
		if(btnStartStop.getText().equals("start")){
			server.startServer();
		}else{
			server.stopServer();
		}
	}
	public static void main(String[] args){
		new MyServerFrame();
	}
}
