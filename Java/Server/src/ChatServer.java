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
	// 0. �������� ����
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
			// 1. Client�� ��û�� ���
			try {
			Socket client = server.accept(); // ���⼭ ���
			new ClientProcess(client).start();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
// Client��û�� ���� thread�� ó���ϴ� Ŭ����
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
		// 4. ���پ� �о ���
		while(!(msg=br.readLine()).equals("exit")) {
			System.out.println(client.getInetAddress()+":"+msg);
		}
		// ���� �ݱ�
		br.close();
		isr.close();
		client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}