package com.barmgn.www;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChattingServer {
	// ExecutorService 는 주로 사용하는 execute(), submit() 메소드를 외에 여러가지를 기능을 제공
	// 각각의 기능은 찾아보면나옴
	ExecutorService executorService;
	ChattingFrame sf;
	
	ServerSocket serverSocket;
	List<Client> connections=new Vector<Client>();
	
	public ChattingServer(ChattingFrame sf ){
		this.sf=sf;
	}
	
	/*서버 소켓 진행 순서 : Socket 생성 > Binding Port 부여 > Listen - Accept 연결수락 대기 상태 >  */
	void startServer(){
		// ThreadPool을 이용할때 컴퓨터에서 최대로 이용할 수 있는 Core만큼 들고온다.
		executorService=Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors());
		try{
			// 서버개발을 위해서 ServerSocket을 얻는다 
			// 서버소켓을 생성하기위해서는 바인딩포트가 필요하므로 임의로 5001을 주며 생성한다.
			// 포트번호는 0~1023 / 1024~49151 / 49152~65535 3가지로 범위로 존재
			// 각각의 기능이 다름, 우리는 1024~49151 사이에 값을 이용
			serverSocket=new ServerSocket(5001);
			System.out.println("서버시작안쪽");
//			serverSocket.bind(new InetSocketAddress(5001)); 같은 의미
			
//			만약 서버PC의 IP가 여러개일 경우, 특정 IP에 접속하고 싶다면, "localhost" 이곳에 아이피 주소를 적으면된다.
//			serverSocket.bind(new InetSocketAddress("localhost", 5001));

			
		}catch(Exception e){
			if(!serverSocket.isClosed()){
				System.out.println("시작오류");
				stopServer();
			}
			return;
		}
		
		// Runnable implements : Thread 가 작업할 내용을 run() 메소드 안에 넣어준다.
		Runnable runnable=new Runnable(){
			
			// ServerFrame 에서 start()
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// MyServerFrame 내에 콘솔로 출력
				
				sf.displayText("[서버 시작]");
				// 버튼 Start > Stop 으로
//				sf.btnStartStop.setText("stop");
				
				// ServerSocket을 Thread로  listen 상태 만들기
				while(true){
					try{
						// 소켓 객체 생성 > 연결준비 - bind > Port 번호, IP 주소 설정
						//					  accept 를 통해서 연결을 받을 준비를 함 > listen
						Socket socket=serverSocket.accept();
						
						System.out.println("accept!");
						// socket.getRemoteSocketAddress() : 연결'된' 시스템에 대한 주소를 반환한다.
						// 참고 > socket.getLocalSocketAddress() : 연결을 '만든 곳'의 주소를 반환한다.
						// Thread.currentThread() : 현재 실행중인 Thread 의 정보를 반환
						String message="[연결 수락: "+ socket.getRemoteSocketAddress()
							+":"+Thread.currentThread()+"]";
						
						// MyServerFrame 에 메세지 전달
						sf.displayText(message);
						
						// Client
						Client client=new Client(socket);
						
						// Client 는 여러개를 가질 수 있으므로, 지금은 가져야 하므로  
						// 'Client 들만 가질수 있는 배열모양의 객체' 를 생성해야한다.
						// # 필드쪽에 전역변수로 선언된 List<Client> 에 add 로 추가
						connections.add(client);
						sf.displayText("[연결개수 :"+connections.size()+"]");
					}catch(Exception e){
						// 예외 발생시 닫음
						if(!serverSocket.isClosed()){
							stopServer();
						}
						break;
					}
				}
			}
			
		};
		executorService.submit(runnable);
		System.out.println("executorService 이후");
	}
	
	void stopServer(){
		try{
			Iterator<Client> iterator =connections.iterator();
			while(iterator.hasNext()){
				Client client=iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket!=null&&!serverSocket.isClosed()){
				serverSocket.close();
			}
			if(executorService!=null && !executorService.isShutdown()){
				executorService.shutdown();
			}
			sf.displayText("[서버멈춤]");
//			sf.btnStartStop.setText("start");
		}catch(Exception e){}
		
	}
	
	public class Client {
		Socket socket;
		
		Client(Socket socket){
			this.socket=socket;
			receive();
		}
		
		void receive(){
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while(true) {
							byte[] byteArr = new byte[100];
							InputStream inputStream = socket.getInputStream();
							
							//클라이언트가 비정상적으로 종료되면 IOException이 발생
							int readByteCount = inputStream.read(byteArr);
							
							//클라이언트가 정상적으로 Socket의 close()를 호출했을 경우
							if(readByteCount == -1) {  throw new IOException(); }
							
							String message = "[요청처리: " + socket.getRemoteSocketAddress() 
								+ ": " + Thread.currentThread().getName() + "]";
							sf.displayText(message);
							
							String data = new String(byteArr, 0, readByteCount, "UTF-8");
							
							for(Client client : connections) {
								client.send(data);
							}
							System.out.println(data);
						}
					} catch(Exception e) {
						try {
							connections.remove(Client.this);
							String message = "[클라이언트 통신이 안됨: " + socket.getRemoteSocketAddress() 
								+ ": " + Thread.currentThread().getName() + "]";
							sf.displayText(message);
							socket.close();
						} catch (IOException e2) {}
					}
				}
			};
			executorService.submit(runnable);
			
		}
		void send(String data){
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("Send");
						System.out.println();
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(byteArr);
						outputStream.flush();
					} catch(Exception e) {
						try {
							String message = "[클라이언트 통신이 안됨: " + socket.getRemoteSocketAddress() 
								+ ": " + Thread.currentThread().getName() + "]";
							sf.displayText(message);
							connections.remove(Client.this);
							socket.close();
						} catch (IOException e2) {}
					}
				}
			};
			executorService.submit(runnable);
		}
	}
}


