import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

@CustomAnnotation(key = "Student") 
// sql�����ҋ��� ����ٰ� (url="�ּ�", id="���̵�", pw="�н�����" ������ ����ص� ����)
class UseAnnotation{
	String key = "Student"; 
	// class�� annotation����� ��ü�� �޶� �̷��� �ߺ����� ���ϼ��� �ִ�
}