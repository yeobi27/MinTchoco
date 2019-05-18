package com.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChattingClient {
	Socket socket;
	MyClientFrame cf;

	ChattingClient(MyClientFrame cf){
		this.cf=cf;
	}
	void startClient(String address){
		
		Thread thread=new Thread(){
			
			@Override
			public void run() {
				try{
					socket=new Socket();
					socket.connect(new InetSocketAddress(address,5001));
					
					cf.displayText("연결[: "+socket.getRemoteSocketAddress()+"]");
					cf.btnConn.setText("stop");
					cf.btnSend.setEnabled(true);
				}catch(Exception e){
					cf.displayText("[연결안됨");
					if(!socket.isClosed()){
						stopClient();
						return;
					}
				}
				receive();
			}
			
		};
		thread.start();
	}
	void stopClient(){
		try {
			
			cf.displayText("[연결끊김]");
			cf.btnConn.setText("start");
			cf.btnSend.setEnabled(false);
			
			if(socket!=null && !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {}
	}
	
	void receive() {
		while(true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				
				//서버가 비정상적으로 종료되었을 경우  IOException 발생
				int readByteCount = inputStream.read(byteArr);
				
				//서버가 정상적으로  Socket의 close()를 호출했을때 발생
				if(readByteCount == -1) { break; }
				
				String data = new String(byteArr, 0, readByteCount, "UTF-8");
				
				cf.displayText("[받기 완료] "  + data);
			} catch (Exception e) {
				cf.displayText("[서버 통신 안됨]");
				stopClient();
				break;
			}
		}
	}
	void send(MyClientFrame cf,String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {		
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
					cf.displayText("[보내기 완료]");
				} catch(Exception e) {
					cf.displayText("[서버통신 안됨]");
					e.printStackTrace();
					stopClient();
				}				
			}
		};
		thread.start();
	}
	

}
