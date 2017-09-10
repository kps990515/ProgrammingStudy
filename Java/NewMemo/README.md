# FIle I/O 로 메모장 만들기

### main class
```java
public class MemoMain {

	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller control = new Controller(model, view);
		control.process();
	}
}
```

### Memo class
```java
public class Memo {
	int no;
	String name;
	String content;
	long datetime;
}
```

### Controller

```java
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	Model model;
	View view;

	//Controller
	//생성자 부분 클래스를 new하게 되면 실행되는 영역!
	public Controller(Model model, View view)
	{
		this.model = model;
		this.view = view;
	}
	public void process()
	{
		Scanner sc = new Scanner(System.in);

		// 명령어를 입력받아서 후속처리
		// c - create : 데이터 입력모드로 전환
		// r - read   : 데이터 읽기모드로 전환
		// u - update : 데이터 수정모드로 전환
		// d - delete : 데이터 삭제모드로 전환
		String command = "";
		while(!command.equals("exit"))
		{
			view.println("-----------명령어를 입력하세요------------------");
			view.println("c : 쓰기, r : 읽기, u : 수정, d : 삭제, l : 목록");
			view.println("exit : 종료");
			view.println("------------------------------------------");
			//키보드 입력중에 Enter키가 입력될때까지 대기
			command = view.scannerS(sc);
			//명령어를 분기처리
			switch(command)
			{
			case "c":
				Memo memo = view.create(sc);
				// 이름, 내용을 입력받는다
				model.create(memo);
				// memo에 저장된 내용을 파일에 저장한다
				break;
			case "r":
				int num = view.read(sc);
				// 읽을 메모의 번호를 입력받는다
				memo = model.read(num);
				// 메모의 번호가 일치하면 내용을 불러온다
				view.showRead(memo);
				// 내용을 출력한다
				break;
			case "u":
				num = view.read(sc);
				// 읽을 메모의 번호를 입력받는다
				memo = model.read(num);
				// 메모의 번호가 일치하면 내용을 불러온다
				memo = view.update(memo, sc);
				// 수정할 내용을 입력받는다
				model.update(memo);
				// 수정된 내용을 파일에 저장한다
				break;
			case "d":
				num = view.remove(sc);
				// 삭제할 메모의 번호를 입력받는다
				model.remove(num);
				// 삭제할 번호가 있으면 파일에서 삭제하고 저장한다
				break;
			case "l":
				ArrayList<Memo>list = model.getList();
				// 파일에서 모든 내용들을 받아오고 list에 저장한다
				view.showList(list);
				// 출력한다
				break;
			}
		}
		view.println("시스템 종료");
	}
}
```
### Model부분
- 데이터를 저장하는 저장소를 관리하는 객체

#### 선언부분
- 메모 내용이 저장된 파일 & 메모 번호가 저장된 파일을 선언
- 파일이나 디렉토리가 없으면 만들어준다

``` java
public class Model {
	//전체 메모를 저장하는 저장소
		ArrayList<Memo> list = new ArrayList<>();
		//마지막 글번호

		//데이터베이스 주소 : 상수
		private final String DB_DIR ="G:\\Programming\\Java\\Database";
		//파일 이름 : 상수
		private final String DB_FILE = "memo.txt";
		private File database = null;
		//번호데이터베이스 주소 : 상수
		private final String NO_DIR = "G:\\Programming\\Java\\Database";
		private final String NO_FILE = "number.txt";
		private File noDatabase = null;

		// 내용을 구분하기 위한 :: 을 상수로 선언
		private final String COLUMN_SEP = "::";

		//new하는 순간 이 영역이 실행된다
		public Model()
		{
			File dir = new File(DB_DIR);
			File dir2 = new File(NO_DIR);
			// 디렉토리 존재 유무 체크
			if(!dir.exists()||!dir2.exists())
			{
				dir.mkdirs(); // 경로상에 디렉토리가 없으면 자동생성
				//dir.mkdir() -> 경로상에 비어있는게 있으면 에러
			}
			// seperator = OS마다 다른 디렉토리 사이에 있는 슬래쉬 추가해주는 것(\\)
			File file = new File(DB_DIR + File.separator + DB_FILE);
			File file2 = new File(NO_DIR + File.separator + NO_FILE);
			// 파일의 존재유무
			if(!file.exists()||!file.exists())
			{
				try
				{
					file.createNewFile();
					file2.createNewFile();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			database = file;
			noDatabase = file2;
		}

```
#### Model.create - 내용을 파일에 넣기

1. number파일에서 파일번호들을 읽어온다
1-1. 저장된 번호가 없으면 memo.no = 1  
1-2. 번호가 있으면 memo.no = 가장 큰 번호 + 1  
2. 내용파일에 view.create에서 입력된 내용들을 입력한다

```java
public void create(Memo memo)
{
	//Number파일 읽어오기
	try(FileInputStream fis = new FileInputStream(noDatabase))
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
			String tempRow[]=row.split("\n");
			int length = tempRow.length;
			if(length==0)
			{
				memo.no = 0;
			}
			else
			{
			//maxNum = 모든 번호들의 배열중에서 가장 큰 수를 return하는 함수
			memo.no = Integer.parseInt(maxNum(tempRow,length))+1;
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	//쓰는(output) Stream을 선언
	FileOutputStream fos = null;
	FileOutputStream fos2 = null;

	try
	{
		//1. 쓰는(output) Stream을 연다
		fos = new FileOutputStream(database, true);
		fos2 = new FileOutputStream(noDatabase, true);
		//2. Stream을 중간처리(텍스트 enconding 변경) -> 래퍼 Stream
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
		//3. 버퍼처리
		BufferedWriter bw = new BufferedWriter(osw);
		BufferedWriter bw2 = new BufferedWriter(osw2);
		//저장할 내용을 구분자로 분리하여 한줄의 문자열로 바꾼다.
		String row = memo.no + COLUMN_SEP + memo.name + COLUMN_SEP + memo.content + COLUMN_SEP + memo.datetime + "\n";
		String number = memo.no + "\n";
		// 내용 넣어주기(안끄면 버퍼 다 찰 때까지 계속 저장만함)
		bw.append(row);
		bw2.append(number);
		//버퍼 내용을 강제로 내보내기
		bw.flush();
		bw2.flush();
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

```
#### Model.read
- 읽을 문서의 번호를 받아서 일치하면 memo에 모든 내용 입력 후 return
```java
public Memo read(int num)
{
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
			if(memo.no==num)
			{
				return memo;
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}
```

#### Model.update
1. view.update에서 바뀔 내용을 가져오고
2. temprow에다가 1번부터 파일의 내용을 계속 넣어준다
3. update할 내용의 number가 나오면 view에서 가져온 내용을 temprow에 넣는다
4. 전에 존재했던 number의 내용은 temprow에 넣지 않는다
5. temprow의 내용을 파일로 보내 저장한다

```java
public void update(Memo memo)
{
	String temprow="";
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
			// update할 메모의 넘버와 일치하면 메모에 저장되어있던 내용들을 넣는다
			if(Integer.parseInt(tempRow[0])==memo.no)
			{
				temprow += memo.no + COLUMN_SEP +
							memo.name + COLUMN_SEP +
							memo.content + COLUMN_SEP +
							System.currentTimeMillis() + "\n";				
			}
			// 넘버가 일치하지 않으면 기존 내용들을 temprow에 계속 넣는다
			else
			{
				temprow += row + "\n";
			}

		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	//쓰는stream 열기 (처음부터 다시 쓰기 위해 false!!)
	try(FileOutputStream fos = new FileOutputStream(database, false))
	{
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.append(temprow);
		bw.flush();

	}catch(Exception e)
	{
		e.printStackTrace();
	}
}
```

#### Model.remove
1. temprow에다가 1번부터 파일의 내용을 계속 넣어준다
2. delete할 내용의 number가 나오면 temprow에 저장하지 않는다
3. temprow의 내용을 파일로 보내 저장한다
```java
public void remove(int num)
{
	String temprow="";
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
			//내용의 넘버가 delete할 넘버와 일치하지 않으면 temprow에 저장
			if(Integer.parseInt(tempRow[0])!=num)
			{
				temprow += row+"\n";
			}
			// 일치하면 저장X
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	//쓰는stream 열기 (처음부터 다시 쓰기 위해 false!!)
	try(FileOutputStream fos = new FileOutputStream(database, false))
	{
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.append(temprow);
		bw.flush();

	}catch(Exception e)
	{
		e.printStackTrace();
	}
}
```

#### Model.getLIst
1. 파일의 내용들을 읽어오고 memo로 만들어서 list에 넣어준다
2. list를 반환해준다
```java
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
```

#### Model.maxNum
- 파일넘버 파일을 읽어서 가장 큰 넘버를 반환
```java
public String maxNum(String tempRow[],int length)
	{
		for(int i=0;i<length-1;i++)
		{
			/// 방2개의 파일의 번호를 읽어서 큰 수를 오른쪽방으로 보낸다
			if(Integer.parseInt(tempRow[i])>Integer.parseInt(tempRow[i+1]))
			{
				String temp = tempRow[i];
				tempRow[i] = tempRow[i+1];
				tempRow[i+1] = temp;
			}
		}
		return tempRow[length-1];
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
- 읽을 문서 번호 받고 반환
```java
public int read(Scanner sc)
{
	println("글 번호를 입력하세요");
	// ------ 숫자가 입력되지 않았을 때의 예외 처리 --------------- //
	// string으로 입력받는 이유는 int로 받으면 입력버퍼 에러 가능성 때문에
	// 특히 개행문자!!!
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
```

#### View.showRead
- 읽을 문서의 정보가 Model.read에서 오면 출력
```java
public void showRead(Memo memo)
	{
		println("No. "+memo.no);
		println("Author : "+memo.name);
		println("Content : "+memo.content);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formattedDate = sdf.format(memo.datetime);
		println("Date:"+formattedDate);
	}
```

#### View.update
- memo의 내용을 덮어쓸 내용을 입력
```java
public Memo update(Memo memo,Scanner sc)
{
		println("이름을 입력하세요 : ");
		memo.name = scannerS(sc);
		println("내용을 입력하세요 : ");
		memo.content = scannerS(sc);
		//날짜
		memo.datetime = System.currentTimeMillis();
		// 글 하나를 저장한 메모리를 저장소로 이동		
		return memo;
}
```
#### View.remove
- 삭제할 문서 번호 입력받고 Model.remove로 반환
```java
public int remove(Scanner sc)
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
#### View.print
```java
	public void print(String string)
	{
		System.out.print(string);
	}
```
#### View.println
```java
	public void println(String string)
	{
		System.out.println(string);
	}
```
#### View.Scanner.nextInt
```java
	public int scannerI(Scanner sc)
	{
		int num = sc.nextInt();
		return num;
	}
```
#### View.Scanner.nextLine
```java
	public String scannerS(Scanner sc)
	{
		String string = sc.nextLine();
		return string;
	}
}
```
