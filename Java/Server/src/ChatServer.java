import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		Server server = new Server(10004);
		server.start();
	}

}

class Server extends Thread{
	ServerSocket server;
	// 0. 서버소켓 생성
	public Server(int port) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean runFlag = true;
	public void run() {
		System.out.println("Server is running========================");
		while(runFlag){
			// 1. Client의 요청을 대기
			try {
			Socket client = server.accept(); // 여기서 대기
			new ClientProcess(client).start();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
// Client요청을 개별 thread로 처리하는 클래스
class ClientProcess extends Thread{
	Socket client;
	public ClientProcess(Socket client){
		this.client = client;
	}
	public void run(){
		try {
		InputStreamReader isr = new InputStreamReader(client.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String msg="";
		StringBuilder sb = new StringBuilder();
		// 4. 한줄씩 읽어서 출력
		while(!(msg=br.readLine()).equals("exit")) {
			System.out.println(client.getInetAddress()+":"+msg);
		}
		// 연결 닫기
		br.close();
		isr.close();
		client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}