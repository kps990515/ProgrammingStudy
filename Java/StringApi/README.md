# String Api

### String 함수

- length - 문자열의 길이를 구한다

```java
	String a = "String/Test"
	System.out.println(a.length());
```

- indexOf - 문자열의 위치를 구한다

```java
	System.out.println(a.indexOf("t"));
```

- split() - 특정 구분자로 분해(문자 배열로)

```java
	String temp[] = a.split(" ");
	for(String item : temp)
	{
		System.out.println(item);
	}
	//빈문자열로 자르면 글자 하나 단위로 쪼개진다
	String temp2[] = a.split("");
	for(String item : temp2)
	{
		System.out.println(item);
	}
```

- substring() - 문자열 자르기 (여기서 index는 문자사이에 확립된다)

```java
	ex) [0]S[1]t[2]r[3]i[4]n[5]g[6]
	System.out.println(a.substring(2,5));
```

- replace() - 문자열 바꾸기

```java
	System.out.println(a.replace("st", "xt"));
```

- statrsWith() - 특정 문자열로 시작되는지를 검사

```java
	boolean tf=a.startsWith("Str");
	System.out.println(tf);

	주소검색할 떄 유용!
	if(!address.statrsWith("http:"))
	{
		address = "http://" + address;
	}
```
### String & StringBuffer & StringBuilder
- 기본 차이점
>String은 immutable하고, StringBuffer & Builder는 mutable

- String(성능은 나쁨 but Tread-Safe)
>String 객체는 한 번 생성되면 할당된 메모리 공간이 변하지 않습니다.   
[+로] 다른 문자열을 붙여도 기존 문자열에 새로운 문자열이 붙는 것이 아니라,   
새로운 String 객체를 만든 후, 새 String 객체에 연결된 문자열을 저장하고,  
그 객체를 참조하도록 합니다.   
즉, String 클래스 객체는 Heap 메모리 영역(가비지 컬렉션이 동작하는 영역)에  
생성되며, 한 번 생성된 객체의 내부 내용을 변화시킬 수 없습니다.   
기존 객체가 제거 되면 Java의 가비지 컬렉션이 회수합니다.

- StringBuffer & Builder  
> 공통점   
StringBuffer & StringBuilder는 객체에 공간이 부족하지않게 버퍼크기를 유연하게 조절  

	> 차이점  
	StringBuffer는 멀티스레드에서도 동기화 지원  
	StringBuilder는 동기화 지원X

	>기타  
	JDK1.5이상부터 Default값은 StringBuilder  
	다중스레드이면 StringBuffer사용해야함

##### 코드

```java
public void builderVsBuffer(){
	//jdk1.5버전 이상부터는 일반적 스트링 연산을 StringBuilder로
	//컴파일러가 자동변환
	String result = "안" + "녕하" + "세요" + "! 반갑습니다";

	//속도가 빠름
	//비동기에서 안전성 보장
	StringBuffer buffer = new StringBuffer();
	buffer.append("안");
	buffer.append("녕하");
	buffer.append("세요");
	buffer.append("! 반갑습니다");
	System.out.println(buffer.toString());

	// 속도 가장 빠름
	// 하지만 비동기에서 안전성 보장되지 않는다
	// 다중 스레드 환경에서는 사용하지 않는다
	StringBuilder builder = new StringBuilder();
	builder.append("안");
	builder.append("녕하");
	builder.append("세요");
	builder.append("! 반갑습니다");

	System.out.println(buffer.toString());
}
```
##### 출처 : 이러쿵저러쿵(http://ooz.co.kr/298)

### String Constant pool

- 원래는 constant pool이 heap영역의 permanant영역에 있었지만  
메모리 문제가 발생해서 heap영역으로 이동했다

- intern() 메서드를 사용할 경우.   
  상수풀에 동일한 값의 문자열이 있을경우   
	상수풀에 존재하는 문자열의 주소를 반환받고  
	아닌경우 새로 생성된 주소를 반환받는다.  

##### 코드

```java

	public void stringConstantPool()
	{
		String a = "안녕하세요";
		String b = "안녕하세요";

		System.out.println(a==b);  //주소체크
		System.out.println(a.equals(b)); //값 체크

		String c = new String("안녕하세요");

		String d = c.intern(); // new로 만들어진 객체를 constant pool로 이동시킨다.

		System.out.println(a==d);  //주소체크(같다)
		System.out.println(a.equals(d)); //값 체크

	}		
}
```
