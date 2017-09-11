# 추상클래스 vs 인터페이스 & callback
- 상속받는 클래스, 구현 인터페이스 안에 있는 추상메소드를 구현하도록 강제

### 추상클래스

#### 정의
- 일단 일반 적인 클래스는 세부적이며 아주 구체적이다.  
ex) 사자, 독수리, 금붕어 등

- 반면에 추상 클래스는 일반 클래스에 비해 구체적이지 않으며 약간 추상적이다.  
ex) 포유류, 조류, 어류 등

- 추상클래스는 반드시 하나이상의 추상메소드를 가지며, 객체를 생성할 수 없다.
- 하지만 슈퍼클래스로 사용할 수는 있다

- 추상메소드: 내용이 없는 비어있는 메소드

- 추상클래스는 일반메소드를 가질 수 있다.

- 추상메소드를 사용하기 위해서는 반드시 __해당 메소드를 재정의 해야만 한다.__

#### 사용이유
- 추상클래스는 그 추상클래스를 상속받아서 기능을 이용하고 확장시키는데 이용된다.
-  추상클래스는 같은 종류나 행동들을 구현할게 많을때 사용
-  상속에 대한 계층구조를 명확히 표현할때 추상클래스를 이용
- 추상클래스는 일반변수들과 일반메소드들도 쓸수 있고 아직 구현하지 않아도될 메소드는 그냥 내버려둘수 있어 상황에 따라 편리하다.

#### 사용방법
- 추상클래스나 추상메소드를 선언하기 위해서는 이름앞에 abstract이라는 키워드를 추가하면 된다.

```java
public class Test {

	public static void main(String[] args) { // 메인 클래스내의 메인 메소드
		Rectangle r = new Rectangle(); // 서브클래스 객체생성
		Circle c = new Circle(); // 서브클래스 객체생성
		r.draw(); // 서브클래스의 상속받은 메소드 호출
		c.draw(); // 서브클래스의 상속받은 메소드 호출
	}
}
// 추상클래스 (슈퍼클래스)
abstract class Shape {
	int x, y;
	public void move(int x, int y){
		this.x=x;
		this.y=y;
	}
	// 추상메소드
	public abstract void draw();
}
 // 서브클래스
class Rectangle extends Shape {
	int width,height;
	 // 메소드 재정의
	public void draw() {
		System.out.println("사각형 그리기 메소드");
	}
}
 // 서브클래스
class Circle extends Shape {
	int radius;
	 // 메소드 재정의
	public void draw() {
		System.out.println("원그리기메소드");
	}
}
```

### 인터페이스

#### 정의
- 인터페이스는 추상메소드들로만 이루어져 있으며, 메소드의 선언만 가능하다.

#### 사용이유
- 함수의 껍데기만 가지고 있어서 함수의 구현을 강제할 때 사용
- 디자인을 구성하는 요소들이 자주 바뀔때 쓰면 유용
- 메소드 형태만 서로 공유해서 구현되는 상황일때 적합
- 클래스 전체가 아닌 메소드들만 쓰고 싶을때 인터페이스를 이용하면 효과적
- 인터페이스안의 메소드 내용을 변경하더라도 그와 관련된 모든 클래스들을 변경할 필요없이 해당 메소드의 구현되는 내용만 변경하면 모든 처리가 가능

#### 사용방법
- 인터페이스로 인해 구현된 구현체는 구현체로 바로 접근하는 것이 아닌 interface로 접근한다
- 인터페이스를 사용하기위해서는 implements 키워드를 사용하여야한다.
- 인터페이스는 서로다른 클래스를 연결시켜주는 연결 장치라고 보면된다

```java
public class Test {
	public static void main(String[] args) { // 메인 클래스내의 메인 메소드

		/* 각클래스별로 객체생성 */
		Television tv = new Television();
		Refrigerator r = new Refrigerator();
		/* 상속받은 메소드 호출 */
		tv.turnOn();
		r.turnOn();
		tv.turnOff();
		r.turnOff();
	}
}

interface RemoteControl { // 인터페이스
	public void turnOff(); // 메소드선언
	public void turnOn(); // 메소드선언
}

class Television implements RemoteControl {

	/* 상속받은 메소드 재정의 */
	public void turnOff() {System.out.println("TV를 끔니다.");	}
	public void turnOn() {System.out.println("TV를 켭니다.");	}
}

class Refrigerator implements RemoteControl {

	/* 상속받은 메소드 재정의 */
	public void turnOff() {System.out.println("냉장고를 끔니다.");	}
	public void turnOn() {System.out.println("냉장고를 켭니다.");	}
}
```

### CallBack

#### 정의
- A가 B를 호출하여 B가 작업을 수행하다 어떤시점에서 다시 B는 A를 호출, 그 때 A가 정해놓은 작업을 수행하는 것
- 결국 function이 자신의 일부를 넘기는 행위

#### 사용이유
- 서버로 부터 빠른 데이타를 수신하기 위해서 AJAX가 아닌 JSON을 많이 사용하게 된다.
- 이렇게 하면 문제는 동기화 방식으로 동작을 하기 때문에 순차적으로 데이타를 전달받게 된다.__(느림)__
- AJAX요청과 함께 사용하면 정말 강력하다. AJAX특성상 서버의 응답도 영향이 있기 때문에 같은 요청도 같은 컴퓨터일지라도 네트워크 환경 등 여러가지 변수 때문에 반응 속도가 천차만별이다.
- 하지만 콜백함수를 이용하게 되면 콜백함수가 완료 메시지를 전달함으로써 확실히 가져온 데이터를 가지고 처리를 할 수 있게 된다.

#### 예시
![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Java/%EA%B3%B5%EB%B6%80%EC%9E%90%EB%A3%8C/%EC%B6%94%EC%83%81%ED%81%B4%EB%9E%98%EC%8A%A4%26%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4/KakaoTalk_20170911_204030423.jpg)

### 참고사항
- module = 독립적으로 compile가능한것(제작자가 다름)
- 여기서는 class A / class C + interface interfaceC
- 상속 받을 시 메모리 할당 방법
>상위계층이 맨 처음 할당  
그 위에 다음 하위계층이 메모리 할당  
그 위에 다음 하위계층이 메모리 할당  
결국 상위 -> 하위 계층 순으로 점차 쌓이는 구조  
그래서 다형성으로 맨 하위계층에 override된것이 실행

### 참고사이트

- 참고사이트 : 사용이유(https://brunch.co.kr/@kd4/6)
- 참고사이트 : 사용이유 심화(http://alecture.blogspot.kr/2011/05/abstract-class-interface.html)
- 참고사이트 : 코드예시(http://enter.tistory.com/122)
- 참고사이트 : callback(https://brunch.co.kr/@kimkm4726/1)
- 참고사이트 : callback 사용이유( http://lovestudycom.tistory.com/entry/콜백함수를-왜-사용하나)
