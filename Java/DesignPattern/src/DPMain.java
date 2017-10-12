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
		System.out.println("Client " + title + "�� ��������� �ݿ�");
	}
}

class Client2 implements Subject.IObserver{
	String title = "";
	public Client2(String title) {
		this.title=title;
	}
	@Override
	public void noti() {
		System.out.println("Client " + title + "�� ��������� �ݿ�");
	}
}

class Subject extends Thread{
	
	List<IObserver> clients = new ArrayList<>(); // ������ �� client���
	
	public void run() {
		Random random = new Random();
		while(true){
			for(IObserver observer : clients) {
				observer.noti();
			}
			System.out.println("[Subject] �޽��� ���ۿϷ�");
			// ���ֱ��� ������ ���� �׽�Ʈ �ڵ�
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

// Ŭ���̾�Ʈ ����� ���� ���� Ŭ����
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

