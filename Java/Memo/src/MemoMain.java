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
	
	public void create(Memo memo)
	{
		//글번호
		memo.no = lastIndex++;
		//글 하나를 저장한 메모리를 저장소로 이동
		list.add(memo);
	}
	public void remove(int num)
	{
		int deleteIndex = -1;
		for(int i = 0; i < list.size(); i++) {
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
