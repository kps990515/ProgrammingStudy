import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		// 특정 ip와 port를 가진 서버에 접속해서 메시지를 전송하는 프로그램
		Client client = new Client("192.168.1.120",10004);
		client.start();
	}
}

class Client extends Thread{
	String ip;
	int port;
	boolean runFlag=true;
	public Client(String ip, int port) {
		this.ip=ip;
		this.port=port;
	}
	public void run(){
		try {
		// 1. 서버와 접속
		Socket socket = new Socket(ip,port);
		OutputStream os = socket.getOutputStream();
		// 2. 메시지 입력받을 scanner
		Scanner sc = new Scanner(System.in);
		while(runFlag){
			String msg = sc.nextLine();
			if("exit".equals(msg)){
				runFlag=false;
			}
			msg = msg+"\r\n";
			os.write(msg.getBytes());
			os.flush();
		}
		socket.close();
		os.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
