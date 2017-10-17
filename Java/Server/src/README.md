# HttpServer

### ServerMain
- 서버를 생성해서 자료에 접근할 때 처리해주는 법
- Path사용

```java
public class HttpServer {

	public static void main(String[] args) {
		WebServer server = new WebServer(8080);
		server.start();
	}

}

class WebServer extends Thread{
	ServerSocket server;
	public boolean runFlag=true;
	private final String DIR = "C:\\Users\\user\\Desktop";
	public WebServer(int port) {
		try {
			server = new ServerSocket(port);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run(){
		while(runFlag){
			// 1. 클라이언트 연결대기
			try {
				Socket client = server.accept();
				// 2. 요청에 대한 처리를 새로운 Thread에서 처리
				new Thread() {
					public void run() {
						try {
							//  3. 스트림연결
							InputStreamReader isr = new InputStreamReader(client.getInputStream());
							BufferedReader br = new BufferedReader(isr);
							// 4. 웹브라우저에서 요청한 주소로 줄단위의 명령어가 날라오는 것을 꺼내서 처리
							String line = br.readLine();
							System.out.println("line = "+line);
							// 5. 요청된 명령어의 첫줄만 parsing해서 동작을 결정
							// Method[] + 도메인을 제외한 주소[] + 프로토콜의 버전
							String [] cmd = line.split(" ");
							if("/hello".equals(cmd[1])){
								String msg = "<h1>Hello~~~~~~~~~~~~~~~~~</h1>";
								OutputStream os = client.getOutputStream();
								// 화면에 보이지 않는 메타정보
								os.write("HTTP/1.1 200 OK \r\n".getBytes());
								os.write("Content-Type: text/html \r\n".getBytes());
								os.write(("Content-Length: "+msg.getBytes().length+"\r\n").getBytes());
								// 헤더와 바디 구분자로 전송
								os.write("\r\n".getBytes());
								// 실제 전송되는 데이터
								os.write(msg.getBytes());
								os.flush();
```

#### Path
1. path사용해 파일읽고
2. 파일을 byte[]로 변환
3. probeContentType을 통해 파일type확인
4. text이면 plain/text, 아니면 type을 넣어줌

```java
							}else{
								// Path를 사용한 파일처리
								Path path = Paths.get(DIR+"/"+cmd[1]);
								byte [] content = Files.readAllBytes(path);
								if(Files.exists(path)) {
									String type = Files.probeContentType(path);
									OutputStream os = client.getOutputStream();
									os.write("HTTP/1.1 200 OK \r\n".getBytes());
									if("plain/text".equals(type)) {
										os.write("Content-Type: text/html \r\n".getBytes());
									}else{
										os.write(("Content-Type: " +type+ "\r\n").getBytes());
									}
									// 파일을 읽고 byte배열로 변환 후 사이즈를 계산
									int size = content.length;
									os.write(("Content-Length: "+size+"\r\n").getBytes());
									// 헤더와 바디 구분자로 전송
									os.write("\r\n".getBytes());
									// 실제 전송되는 데이터
									os.write(content);
									os.flush();
									os.close();
								}
							}
							client.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

```
