import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 출력 : System.out.pritn
 * 입력 : Scanner(System.in)
 */
public class MemoMain {
	
	Model model = new Model();
	View view = new View();
	
	//Controller
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		MemoMain main = new MemoMain();
		
		// 명령어를 입력받아서 후속처리
		// c - create : 데이터 입력모드로 전환
		// r - read   : 데이터 읽기모드로 전환
		// u - update : 데이터 수정모드로 전환
		// d - delete : 데이터 삭제모드로 전환
		String command = "";
		while(!command.equals("exit"))
		{
			main.view.println("-----------명령어를 입력하세요------------------");
			main.view.println("c : 쓰기, r : 읽기, u : 수정, d : 삭제, l : 목록");
			main.view.println("exit : 종료");
			main.view.println("------------------------------------------");
			//키보드 입력중에 Enter키가 입력될때까지 대기
			command = main.view.scannerS(sc);
			//명령어를 분기처리
			switch(command)
			{
			case "c":
				Memo memo = main.view.create(sc);
				//메모 데이터에 대한 조작이 필요할 경우 모두 컨트롤러에서 작업한다
				main.model.create(memo);
				break;
			case "r":
				main.view.read(main.model.list, sc);
				break;
			case "u":
				main.view.update(main.model.list, sc);
				break;
			case "d":
				int num = main.view.remove(main.model.list, sc);
				main.model.remove(num);
				break;
			case "l":
				main.model.getList();
				main.view.showList(main.model.list);
				break;
			}
		}
		main.view.println("시스템 종료");
	}
}
//데이터를 저장하는 저장소를 관리하는 객체
class Model
{
	//전체 메모를 저장하는 저장소
	ArrayList<Memo> list = new ArrayList<>();
	//마지막 글번호
	int lastIndex = 1;
	
	//데이터베이스 주소 : 상수
	private final String DB_DIR = "G:\\Programming\\Java\\Database";
	private final String DB_FILE = "memo.txt";
	private File database = null;

	//new하는 순간 이 영역이 실행된다
	public Model()
	{
		File dir = new File(DB_DIR);
		// 디렉토리 존재 유무 체크
		if(dir.exists())
		{
			dir.mkdirs(); // 경로상에 디렉토리가 없으면 자동생성
			//dir.mkdir() -> 경로상에 비어있는게 있으면 에러
		}
		// seperator = OS마다 다른 디렉토리 사이에 있는 슬래쉬 추가해주는 것(\\)
		File file = new File(DB_DIR + File.separator + DB_FILE);
		// 파일의 존재유무
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		database = file;
	}

	// 내용을 구분하기 위한 :: 을 상수로 선언
	private final String COLUMN_SEP = "::";
	
	public void create(Memo memo)
	{
		//글번호
		memo.no = lastIndex++;

		//쓰는(output) Stream을 선언
		FileOutputStream fos = null;
		
		try 
		{
			//1. 쓰는(output) Stream을 연다
			fos = new FileOutputStream(database, true);
			//2. Stream을 중간처리(텍스트 enconding 변경) -> 래퍼 Stream
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			//3. 버퍼처리
			BufferedWriter bw = new BufferedWriter(osw);
			
			//저장할 내용을 구분자로 분리하여 한줄의 문자열로 바꾼다.
			String row = memo.no + COLUMN_SEP + memo.name + COLUMN_SEP + memo.content + COLUMN_SEP + memo.datetime + "\n";
			// 내용 넣어주기(안끄면 버퍼 다 찰 때까지 계속 저장만함)
			bw.append(row);
			//버퍼 내용을 강제로 내보내기
			bw.flush();
			// bw.close() -> 버퍼 끄기 
		} 
		/* <원래는 1번 & append 예외처리 각각 해줘야하지만> 상위인 Exception으로 해결
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		*/
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Stream 끄기 -> finally쓰는 이유는 catch에 오류 잡히면 안꺼질 수도 있어서
		finally
		{
			//Stream이 생성되기 전에 오류가 발생할 수도 있으므로 null 체크
			if(fos != null)
			{
				try 
				{
					fos.close();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<Memo> getList()
	{
		//데이터가 중복으로 쌓이지 않도록 저장소를 지워주는 작업이 필요하다(누군가가 불러오기 계속 클릭 할 시)
		list.clear();
		
		//1. 읽는 스트림을 연다
		//java8에서 바뀐 방법
		//이렇게 하면 stream을 따로 끌 필요가 없다!!!
		//try-with 절에서 자동으로 fis.close가 발생
		try(FileInputStream fis = new FileInputStream(database)) 
		{
			//2. 실제 file encoding을 바꿔주는 래퍼클래스를 삿용
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			//3. 버퍼처리
			BufferedReader br = new BufferedReader(isr);
			
			String row;
			// 새로운 줄을 한줄씩 읽어서 row에 저장하고
			// row가 null값이 될 때 까지 읽기 그리고 종료
			while((row = br.readLine()) != null)
			{
				String tempRow[] = row.split(COLUMN_SEP);
				Memo memo = new Memo();
				memo.no = Integer.parseInt(tempRow[0]);
				memo.name = tempRow[1];
				memo.content = tempRow[2];
				memo.datetime = Long.parseLong(tempRow[3]);
				
				list.add(memo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void remove(int num)
	{
		int deleteIndex = -1;
		for(int i = 0; i < list.size(); i++) {  //index를 알기 위해서 for문 사용
			if(list.get(i).no == num) {
				deleteIndex = i;
				break;
			}
		}
		if(deleteIndex >= 0) list.remove(deleteIndex);
	}
}
//화면 입출력을 관리
class View
{
	// 입력받는 함수
	public Memo create(Scanner sc) //Scanner받아와야 함!!!
	{	
		//글 하나를 저장하기 위한 메모리 확보
		Memo memo = new Memo();
		println("이름을 입력하세요 : ");
		memo.name = scannerS(sc);
		println("내용을 입력하세요 : ");
		memo.content = scannerS(sc);
		//날짜
		memo.datetime = System.currentTimeMillis();
		
		return memo;
		//model.create(memo)	
	}
	
	public void read(ArrayList <Memo> list,Scanner sc)
	{
		println("읽을 메모의 번호를 입력하세요 : ");
		String temp = scannerS(sc);
		int readnum = Integer.parseInt(temp); //scannerI(sc);
		for(Memo memo : list)
			{
				if(memo.no==readnum)
				{
					print("No : " + memo.no);
					print(" | Author : " + memo.name);
					println(" | Content : " + memo.content);
					
					//숫짜로 입력받은 날짜를 실제 날짜로 변경
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String formattedDate = sdf.format(memo.datetime);
					println("Date : " + formattedDate);	
					break;
				}
				
			}	
	}
	
	public void update(ArrayList <Memo> list,Scanner sc)
	{
		println("수정할 메모의 번호를 입력하세요 : ");
		String temp = scannerS(sc);
		int num = Integer.parseInt(temp);
		for(Memo memo : list)
		{
			if(memo.no==num)
			{
				println("이름을 입력하세요 : ");
				memo.name = scannerS(sc);
				println("내용을 입력하세요 : ");
				memo.content = scannerS(sc);
				//날짜
				memo.datetime = System.currentTimeMillis();
				// 글 하나를 저장한 메모리를 저장소로 이동		
				break;	
			}
		}
	}
	
	public int remove(ArrayList <Memo> list, Scanner sc)
	{
		int num;
		println("삭제할 메모의 번호를 입력하세요 : ");
		String temp = scannerS(sc);
		num = Integer.parseInt(temp); 
		return num;
	}
	
	public void showList(ArrayList <Memo> list)
	{
		//ArrayList 저장소를 반복문을 돌면서 한줄씩 출력
		for(Memo memo : list)
		{
			print("No : " + memo.no);
			print(" | Author : " + memo.name);
			println(" | Content : " + memo.content);
		}
	}
	//프린트
	public void print(String string)
	{
		System.out.print(string);
	}
	//줄바꿈 프린트
	public void println(String string)
	{
		System.out.println(string);
	}
	
	public int scannerI(Scanner sc)
	{
		int num = sc.nextInt();
		return num;
	}
	
	public String scannerS(Scanner sc)
	{
		String string = sc.nextLine();
		return string;
	}
}

class Memo
	{	
		int no;
		String name;
		String content;
		long datetime;
	}	
