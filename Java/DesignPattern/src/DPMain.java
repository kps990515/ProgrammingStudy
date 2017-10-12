import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Observer Pattern
 * 
 */
public class DPMain {

	public static void main(String[] args) {
		Subject server = new Subject();
		server.start();
		
		ClientDaemon daemon = new ClientDaemon(server);
		daemon.start();
	}

}

class Client1 implements Subject.IObserver{
	String title = "";
	public Client1(String title) {
		this.title=title;
	}
	@Override
	public void noti() {
		System.out.println("Client " + title + "의 변경사항이 반영");
	}
}

class Client2 implements Subject.IObserver{
	String title = "";
	public Client2(String title) {
		this.title=title;
	}
	@Override
	public void noti() {
		System.out.println("Client " + title + "의 변경사항이 반영");
	}
}

class Subject extends Thread{
	
	List<IObserver> clients = new ArrayList<>(); // 정보를 줄 client목록
	
	public void run() {
		Random random = new Random();
		while(true){
			for(IObserver observer : clients) {
				observer.noti();
			}
			System.out.println("[Subject] 메시지 전송완료");
			// 비주기적 갱신을 위한 테스트 코드
			try {
				Thread.sleep((random.nextInt(10)+1)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		}
	
	public interface IObserver{
		public void noti();
	}
}

// 클라이언트 등록을 위한 데몬 클래스
class ClientDaemon extends Thread{
	Subject server;
	public ClientDaemon(Subject server) {
		this.server=server;
	}
	public void run() {
		int count=0;
		while(true) {
			if(count%2==0) {
				server.clients.add(new Client1("Client 1"));
			}else {
				server.clients.add(new Client2("Client 2"));
			}
			count++;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

