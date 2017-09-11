# Annotation

### 정의
- 어노테이션이 붙은 코드는 어노테이션의 구현된 정보에 따라 연결되는 방향이 결정
- 전체 소스코드에서 비즈니스 로직에는 영향을 주지는 않지만 해당 타겟의 연결 방법이나 소스코드의 구조를 변경할 수 있음
- 이 속성을 어떤 용도로 사용할까, 이 클래스에게 어떤 역할을 줄까?"를 결정
-  소스코드에 메타데이터를 삽입
>메타데이터 : 데이터에 관한 구조화된 데이터로, 다른 데이터를 설명해 주는 데이터

### 사용이유

#### 기존

##### 장점
- 기존의 자바 웹애플리케이션들은 선언적인 프로그래밍 방식을 사용
- 프로그램의 전체 및 각 레이어별
구성과 설정값들을 외부의 XML 설정파일에 명시하는 방식을 의미
- 변경 요구사항이 들어왔을 때, 재컴파일 없이도 쉽게 변경사항을 적용
- 유연성

##### 단점
- 프로그램 작성을 위해 매번 많은 설정파일을 작성
- 규모가 커질수록 설정의 양도 많아지게 되며 이를 잘 구조화 할 수 있는 방법도 필요
- 코드를 확인하기
위해서는 Model , Service 클래스와 XML 설정파일을 모두 뒤져야 한다는 것이다

#### Annotation 사용시
- 데이터에 대한 유효성 검사조건을 어노테이션을 사용하여
Model 클래스에 직접 명시
- 데이터들에 대한 유효조건을 쉽게 파악
- 코드가 깔끔해지고, 어노테이션의 재사용도 가능

#### Annotation 용도
- 문서화
- 컴파일러 체크
- 코드 분석

#### 쓰는 이유(옵션)
- 누군가와 공동작업 + 오픈소스 만들때(정보주거나, 작업지정, 별도처리)
- 완성된 코드에 특정 코드를 넣고 싶을 떄  
(annotation을 쓰면 코드에 안넣어도 되서 영향을 미치지 않는다)
-  특정 코드의 변수나 영향을 주고 싶을 때 특정 함수의 코드를 찾아가는것이 아닌 annotation만 바꾸주면 된다
- 통제력 상승

#### 분류(멤버 변수의 갯수로)
- Marker 어노테이션  
멤버 변수가 없으며, 단순히 표식으로서 사용되는 어노테이션이다. 컴파일러에게 어떤 의미를 전달한다.

- Single-value  어노테이션  
멤버로 단일변수만을 갖는 어노테이션이다. 단일변수 밖에 없기 때문에 (값)만을 명시하여 데이터를 전달할 수 있다.  

- Full 어노테이션  
멤버로 둘 이상의 변수를 갖는 어노테이션으로, 데이터를 (값=쌍)의 형태로 전달한다.

#### 빌트인 Annotation
- __@OVERRIDE__   
어노테이션은 현재 메소드가 수퍼클래스의 메소드를 오버라이드한 메소드임을 컴파일러에게 명시한다. 만일
수퍼 클래스에 해당하는 메소드가 없으면 컴파일러가 인지하고 에러를 발생시켜 준다.

- __@Deprecated__   
 마커 어노테이션으로  차후 버전에 지원되지 않을 수 있기 때문에 더 이상 사용되지  말아야할 메소드를 나타낸다.  

- __@SupressWarning__  
의미대로 경고를 제거하는 어노테이션이다. Object형을 엘리먼트로 하는 컬렉션을 사용하면,  컴파일러 경고가 발생하는데
이 어노테이션을 사용하여 프로그래머의 의도적인 Object 형 사용임을 알려 경고를 제거할 수 있다.

#### 커스텀 Annotation
![그림]()
1. 정의하기
```java
public @interface InProgress
```

2. 멤버 추가하기
```java
// 해당 태스크가 완료되야함 나타내는 어노테이션
public @interface TODO {
  String value();           
}
@TODO("Figure out the amount of interest per month")
public void calculateInterest(float amount, float rate) {
}
```

3. 디폴트값 추가하기
```java
public @interface GroupTODO {

  public enum Severity { CRITICAL, IMPORTANT, TRIVIAL, DOCUMENTATION };

  Severity severity() default Severity.IMPORTANT;
  String item();
  String assignedTo();
  String dateAssigned();
}  
@GroupTODO(
    item="Figure out the amount of interest per month",
    assignedTo="Brett McLaughlin",
    dateAssigned="08/04/2004"
  )
  public  void calculateInterest(float amount, float rate) {
    // ...
  }
```

#### 메타 Annotation
- 어노테이션에 사용되는 어노테이션으로 해당 어노테이션의 동작대상 및 보유타임을 결정한다.  

- __@Target__ 메타-어노테이션  
어노테이션이 적용되는 동작 프로그래밍 엘리멘트를 지정하며, 다음과 같은 값들이 있다.   
```java
public enum ElementType {
  TYPE,			      // Class, Interface, enum
  FIELD,		      // 프로퍼티 (enum 나열값들은 제외)
  METHOD,		      // 메서드
  PARAMETER,		  // 메서드 파라미터
  CONSTRUCTOR,    // 생성자
  LOCAL_VARIABLE,	// 로컬변수 또는 Catch문
  ANNOTATION_TYPE,//  어노테이션(메타 어노테이션)_
  PACKAGE		      //  자바 패키지
}
```
- __@Retention__ 메타-어노테이션   
자바컴파일러가 어노테이션을 다루는 방법과 관련이 있다.  
소스파일, 클래스파일, 런타임 중 어느시점까지 어노테이션을 보유하고 있을 것인지를 결정한다.
```java
public enum RetentionPolicy {
  SOURCE,		// 어노테이션이 컴파일러에 의해 버려짐
  CLASS,		// 어노테이션이 클래스파일에 저장되지만, JVM에게 무시됨
  RUNTIME		// 어노테이션이 클래스파일에 저장되며, JVM에 의해 읽혀짐
}
```

## 코드예시

### main class

```java
public class AnnoMain {

	public static void main(String[] args) {
		UseAnnotation use = new UseAnnotation();

		String key = use.getClass().getAnnotation(CustomAnnotation.class).key();
		if(key.equals("Student")) {
			// 런타임시에 해줄 행동을 정의...			
		}
	}
}
```

### CustomAnnoation 부분
```java
// Target = 적용할 대상 : 생성자, 멤버변수(속성), 타입(클래스), 파라미터, 메소드
// Retention = annotation 정보의 유지 단계
// 			       소스코드, 클래스, 런타입...
// Documented = javadoc에 문서화되어져야 하는 element
// Inherited = 상속되는 annotation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation{
	public String value() default "값";
	public String key();
}
```

### GetConnection 부분
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Getconnection{
	public String url();
	public String id();
	public String pw();
}
```

### UseAnnotation class
```java
class UseAnnotation{
	String key = "Student";
	// class와 annotation만드는 주체가 달라서 이렇게 중복으로 쓰일수도 있다

	@Getconnection(url = "주소", id = "아이디", pw = "패스워드")

	public void process() {
		int a = 156;
		int b=12312321;
		int result = a+b/1450;

		try {
			DriverManager.getConnection("주소","아이디","패스워드");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
```

참고사이트 : 사용이유(http://www.nextree.co.kr/p5864/)
참고사이트 : 그 외(http://hiddenviewer.tistory.com/88) 
