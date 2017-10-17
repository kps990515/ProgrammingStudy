# ChatServer & ChatClient

#### [HttpServer](https://github.com/kps990515/ProgrammingStudy/tree/master/Java/Server/src)로 이동

### ChatServer
- Server, ClientProcess Thread클래스 생성
- Main에서 server클래스 new -> server실행
- Server에서 ServerSocket만들고 누군가 접속하면 정보를 Client에 저장
- ClientProcess클래스에 client포함해서 전달&실행
- ClientProcess는 정보를 읽어오고 출력

```java
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
```

### ChatClient
- main에서 ip+port를 사용해 Client 생성 & 시작
- Client에서 입력을 받고 OutputStream을 통해 해당 ip+port가진 서버로 입력전달

```java
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

```
