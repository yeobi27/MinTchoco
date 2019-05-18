package com.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyClientFrame extends JFrame implements ActionListener{
	
	JTextArea txtDisplay;
	JTextField tfInput,tfAddress, tfName;
	JButton btnConn, btnSend;
	ChattingClient client;
	Socket socket;
	
	MyClientFrame(){
		setTitle("Chat Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,400);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		p2.setLayout(new BorderLayout());
		txtDisplay=new JTextArea();
		txtDisplay.setEnabled(true);
		tfInput=new JTextField(40);
		tfAddress=new JTextField("127.0.0.1",20);
		tfName=new JTextField(10);
		btnConn=new JButton("start");
		btnSend=new JButton("send");
		p1.add(new JLabel("서버주소"));
		p1.add(tfAddress);
		p1.add(new JLabel("대화명"));
		p1.add(tfName);
		p1.add(btnConn);
		p2.add(tfInput,BorderLayout.CENTER);
		p2.add(btnSend, BorderLayout.EAST);
		btnConn.addActionListener(this);
		btnSend.addActionListener(this);
		tfInput.addActionListener(this);
		
		add(p1, BorderLayout.NORTH);
		add(txtDisplay,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		setVisible(true);
		client=new ChattingClient(this);
	}
	
	void displayText(String str){
		txtDisplay.append(str+"\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		try{
			
			if(event.getSource()==btnConn){
				if(btnConn.getText().equals("start")){
					String address=tfAddress.getText();
					
					client.startClient(address);
				}else{
					client.stopClient();
				}
			}else{			
				String name=tfName.getText().toString();
				String data=tfInput.getText().toString();
				client.send(this, name+">>"+data);
				tfInput.setText("");
			}
		}catch(Exception e){}
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyClientFrame();
	}

	

}
