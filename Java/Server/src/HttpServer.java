import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

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
			// 1. Ŭ���̾�Ʈ ������
			try {
				Socket client = server.accept();
				// 2. ��û�� ���� ó���� ���ο� Thread���� ó��
				new Thread() {
					public void run() {
						try {
							//  3. ��Ʈ������
							InputStreamReader isr = new InputStreamReader(client.getInputStream());
							BufferedReader br = new BufferedReader(isr);
							// 4. ������������ ��û�� �ּҷ� �ٴ����� ��ɾ ������� ���� ������ ó��
							String line = br.readLine();
							System.out.println("line = "+line);
							// 5. ��û�� ��ɾ��� ù�ٸ� parsing�ؼ� ������ ����
							// Method[] + �������� ������ �ּ�[] + ���������� ����
							// line = GET /test.txt HTTP/1.1
							String [] cmd = line.split(" ");
							if("/hello".equals(cmd[1])){
								String msg = "<h1>Hello~~~~~~~~~~~~~~~~~</h1>";
								OutputStream os = client.getOutputStream();
								// ȭ�鿡 ������ �ʴ� ��Ÿ����
								os.write("HTTP/1.1 200 OK \r\n".getBytes());
								os.write("Content-Type: text/html \r\n".getBytes());
								os.write(("Content-Length: "+msg.getBytes().length+"\r\n").getBytes());
								// ����� �ٵ� �����ڷ� ����
								os.write("\r\n".getBytes());
								// ���� ���۵Ǵ� ������
								os.write(msg.getBytes());
								os.flush();
							}else{
								// Path�� ����� ����ó��
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
									// ������ �а� byte�迭�� ��ȯ �� ����� ���
									int size = content.length;
									os.write(("Content-Length: "+size+"\r\n").getBytes());
									// ����� �ٵ� �����ڷ� ����
									os.write("\r\n".getBytes());
									// ���� ���۵Ǵ� ������
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
