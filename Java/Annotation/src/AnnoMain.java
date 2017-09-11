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
			// ��Ÿ�ӽÿ� ���� �ൿ�� ����...			
		}
	}
}

// Target = ������ ��� : ������, �������(�Ӽ�), Ÿ��(Ŭ����), �Ķ����, �޼ҵ�
// Retention = annotation ������ ���� �ܰ�
// 			       �ҽ��ڵ�, Ŭ����, ��Ÿ��...
// Documented = javadoc�� ����ȭ�Ǿ����� �ϴ� element
// Inherited = ��ӵǴ� annotation

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation{
	public String value() default "��";
	public String key();
}

// annotation�� ����� ��ü�� �����ڰ� �ƴ�
// �ַ� ������Ʈ �����ڳ� ���¼ҽ� �����ڵ���
// ���̺귯���� ���� �� ���� ����ϱ� ���Ͽ� ����� �����Ѵ�.


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Getconnection{
	public String url();
	public String id();
	public String pw();
}

class UseAnnotation{
	String key = "Student"; 
	// class�� annotation����� ��ü�� �޶� �̷��� �ߺ����� ���ϼ��� �ִ�
	
	@Getconnection(url = "�ּ�", id = "���̵�", pw = "�н�����")
	
	public void process() {
		int a = 156;
		int b=12312321;
		int result = a+b/1450;
	
		try {
			DriverManager.getConnection("�ּ�","���̵�","�н�����");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}