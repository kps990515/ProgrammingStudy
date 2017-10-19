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
		// 1. 서버소켓 생성
		ServerSocket server = new ServerSocket(8080);
		while(true) {
			// 2. 연결대기
			Socket client = server.accept();
			// 3. 스트림을 연결 후 요청확인
			// 프로토콜에서 uri를 꺼내는 함수를 제공하는 것 까지 웹서버가 제공
			// 받아오는 정보(uri) : /(인증키)/json/GangseonFod.../1/5/
			String cmd = uri.split("/");
			cmd[1]="인증키";
			cmd[2]="json";
			cmd[3]="서비스이름";
			cmd[4]="시작인덱스";
			cmd[5]="종료인덱스";
			
			// 4. 데이터베이스 연결
			
			// 5. 쿼리생성
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