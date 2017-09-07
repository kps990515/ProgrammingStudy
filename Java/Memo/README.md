# 메모장 만들기

### 설명
- c - create : 데이터 입력모드로 전환
- r - read   : 데이터 읽기모드로 전환
- u - update : 데이터 수정모드로 전환
- d - delete : 데이터 삭제모드로 전환
- l - list   : 전체 데이터 출력모드로 전환
- MVC 기법을 사용하여 객체 생성 & 분리

#### import부분
```java
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
```
#### main class
- MVC 중 Model과 View객체를 생성
```java
public class MemoMain {

	Model model = new Model();
	View view = new View();
```

### Controller(main)

```java
// scanner와 print는 모두 View에서 만들고 처리
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		MemoMain main = new MemoMain();

		// 명령어를 입력받아서 후속처리
		// c - create : 데이터 입력모드로 전환
		// r - read   : 데이터 읽기모드로 전환
		// u - update : 데이터 수정모드로 전환
		// d - delete : 데이터 삭제모드로 전환
        // l - list   : 전체 데이터 출력
        // exit       : 종료
		String command = "";
    //command가 exit이면 while문 종료하고 끝냄
		while(!command.equals("exit"))
		{
			main.view.println("-----------명령어를 입력하세요------------------");
			main.view.println("c : 쓰기, r : 읽기, u : 수정, d : 삭제, l : 목록");
			main.view.println("exit : 종료");
			main.view.println("------------------------------------------");
			command = main.view.scannerS(sc);
			//명령어를 분기처리
			switch(command)
			{
			case "c":
      // View create부분에 가서 이름&내용을 받고 memo를 반환
				Memo memo = main.view.create(sc);
      // Model create 부분에서 memo받아서 list에 add
      // 문서번호도 입력해줌
				main.model.create(memo);
				break;
			case "r":
      // View read부분에서 읽을 문서 번호를 받고
      // 해당 문서 번호가 존재하면 내용을 출력
      // list는 모델부분에서 받음
				main.view.read(main.model.list, sc);
				break;
			case "u":
      // View update부분에서 수정할 문서 번호 받고
      // 해당 문서 번호가 존재하면 내용 수정가능하도록 출력
      // DB가 없기 때문에 View에서 바로 수정
				main.view.update(main.model.list, sc);
				break;
			case "d":
      // View remove부분에서 삭제한 문서 번호 받고 반환
				int num = main.view.remove(main.model.list, sc);
      // for문을 통해 num과 문서번호가 같으면 해당 문서 삭제
				main.model.remove(num);
				break;
			case "l":
      // View showList부분에서 모든 리스트 값을 출력
				main.view.showList(main.model.list);
				break;
			}
		}
		main.view.println("시스템 종료");
	}
}
```
### Model부분
- 데이터를 저장하는 저장소를 관리하는 객체

#### 선언부분
``` java
class Model
{
	//전체 메모를 저장하는 저장소(ArrayList)
	ArrayList<Memo> list = new ArrayList<>();
	//선언 시 마지막 문서번호
	int lastIndex = 1;
```
#### Model.create
- 문서 글 번호입력 & list에 문서 추가
```java
	public void create(Memo memo)
	{
		//글번호
		memo.no = lastIndex++;
		//글 하나를 저장한 메모리를 저장소로 이동
		list.add(memo);
	}
```
#### Model.remove
- 삭제할 문서 번호 받아 온 뒤 해당 번호가 있으면 문서 삭제
```java
	public void remove(int num)
	{
		int deleteIndex = -1;
    //index를 알기 위해서 for문 사용
		for(int i = 0; i < list.size(); i++) {  
      // 문서 번호가 같은 문서 있을 시 인덱스 번호 저장
      if(list.get(i).no == num) {
				deleteIndex = i;
				break;
			}
		}
    // 인덱스가 -1에서 변경되었으면 해당 문서 삭제
		if(deleteIndex >= 0) list.remove(deleteIndex);
	}
}
```
### View부분
- 화면 입출력을 관리

#### View.create
- 문서 생성시 memo생성하여 이름 & 내용 받아오고 memo반환
```java
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
		//memo 반환
		return memo;
	}
```
#### View.read
- 읽을 문서 번호 받고 해당번호가 list에 있을 시 내용 출력
```java
// Model에서 선언한 리스트를 받아온다
	public void read(ArrayList <Memo> list,Scanner sc)
	{
		println("읽을 메모의 번호를 입력하세요 : ");
  // 스캐너로 숫자 받으면 입력버퍼 부분에 데이터가 남는 에러가능
  // 스트링으로 받아옴
		String temp = scannerS(sc);
  // 스트링을 int로 변경
		int readnum = Integer.parseInt(temp);
  // for문으로 list에 있는 memo값들을 불러오기
		for(Memo memo : list)
			{
        // 문서번호와 readnum의 번호가 같으면 출력
				if(memo.no==readnum)
				{
					print("No : " + memo.no);
					print(" | Author : " + memo.name);
					println(" | Content : " + memo.content);

					//숫자로 입력받은 날짜를 실제 날짜로 변경
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String formattedDate = sdf.format(memo.datetime);
					println("Date : " + formattedDate);
          /// 발견시 출력하고 for문 종료
					break;
				}
			}
	}
```
#### View.update
- 수정할 번호 받고 해당번호 있을 시 memo의 내용 변경
```java
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
				break;
			}
		}
	}
```
#### View.remove
- 삭제할 문서 번호 입력받고 Model.remove로 반환
```java
	public int remove(ArrayList <Memo> list, Scanner sc)
	{
		int num;
		println("삭제할 메모의 번호를 입력하세요 : ");
		String temp = scannerS(sc);
		num = Integer.parseInt(temp);
		return num;
	}
```
#### View.showList
- 전체 문서를 모두 출력
```java
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
```
##### View.print
```java
	public void print(String string)
	{
		System.out.print(string);
	}
```
##### View.println
```java
	public void println(String string)
	{
		System.out.println(string);
	}
```
##### View.Scanner.nextInt
```java
	public int scannerI(Scanner sc)
	{
		int num = sc.nextInt();
		return num;
	}
```
##### View.Scanner.nextLine
```java
	public String scannerS(Scanner sc)
	{
		String string = sc.nextLine();
		return string;
	}
}
```

#### Memo클래스
```java
class Memo
	{
		int no;
		String name;
		String content;
		long datetime;
	}
```
