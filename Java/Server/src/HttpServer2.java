import java.awt.Cursor;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {

	public static void main(String[] args) {
		new WebServer().start();
	}
}

class WebServer extends Thread{
	public void run() {
		// 1. �������� ����
		ServerSocket server = new ServerSocket(8080);
		while(true) {
			// 2. ������
			Socket client = server.accept();
			// 3. ��Ʈ���� ���� �� ��ûȮ��
			// �������ݿ��� uri�� ������ �Լ��� �����ϴ� �� ���� �������� ����
			// �޾ƿ��� ����(uri) : /(����Ű)/json/GangseonFod.../1/5/
			String cmd = uri.split("/");
			cmd[1]="����Ű";
			cmd[2]="json";
			cmd[3]="�����̸�";
			cmd[4]="�����ε���";
			cmd[5]="�����ε���";
			
			// 4. �����ͺ��̽� ����
			
			// 5. ��������
			String query = "select * from Ganseonfood... where... limit : ...";
			Cursor cursor = db.execute(query);
			while(cursor.moveToNext) {
				
			}
		}
	}
}

class WebClient extends Thread{
	public void run() {
		Socket client = new Socket("ip.ip.ip.ip",8080);
	}
}