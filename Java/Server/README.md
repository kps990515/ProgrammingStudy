# Server & Client

#### [ChatServer](https://github.com/kps990515/ProgrammingStudy/tree/master/Java/Server/.settings)이동

### ServerMain
1. 서버를 생성
2. 요청을 대기  
Socket client = server.accept();
3. 접속된 client와 Stream을 생성한다
4. 한줄씩 읽어서 저장
5. 출력 & 닫기

```java
public class ServerMain {

	// 웹 서버 만들기
	// 브라우저에서 내가 만든 서버프로그램쪽으로 request를 요청
	public static void main(String[] args) {
		try {
		// 1. 서버를 생성
		ServerSocket server = new ServerSocket(10004);
		// 2. 요청을 대기
		Socket client = server.accept(); // 마치 scanner의 next처럼 요청이 있을 때 까지 대기
		// 3. 접속된 client와 Stream을 생성한다
		InputStreamReader isr = new InputStreamReader(client.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String temp="";
		StringBuilder sb = new StringBuilder();
		// 4. 한줄씩 읽어서 저장
		while((temp=br.readLine()) != null) {
			sb.append(temp).append("\n");
		}
		// 5. 출력
		System.out.println(sb.toString());
		// 연결 닫기
		br.close();
		isr.close();
		client.close();
		server.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

```
