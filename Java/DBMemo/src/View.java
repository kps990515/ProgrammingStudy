

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
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
	
	public int read(Scanner sc)
	{
		println("글 번호를 입력하세요");
		// ------ 숫자가 입력되지 않았을 때의 예외 처리 --------------- //
		String tempNo = scannerS(sc);
		try
		{
			return Integer.parseInt(tempNo);
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public void showRead(Memo memo)
	{
		println("No. "+memo.no);
		println("Author : "+memo.name);
		println("Content : "+memo.content);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formattedDate = sdf.format(memo.datetime);
		println("Date:"+formattedDate);
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
