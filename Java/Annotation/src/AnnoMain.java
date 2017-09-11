import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AnnoMain {

	public static void main(String[] args) {
		UseAnnotation use = new UseAnnotation();
		
		String key = use.getClass().getAnnotation(CustomAnnotation.class).key();
		if(key.equals("Student")) {
			// 런타임시에 해줄 행동을 정의...			
		}
	}
}

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

// annotation을 만드는 주체는 개발자가 아닌
// 주로 공통파트 개발자나 오픈소스 개발자들이
// 라이브러리를 조금 더 쉽게 사용하기 위하여 만들어 제공한다.


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Getconnection{
	public String url();
	public String id();
	public String pw();
}

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