//설계파트(구현을 강제한다)
abstract public class Animal {
	pub
}


// 1. 기능변화가 자주 일어나는 경우 추상클래스로 설계
//추상클래스는 일반 메소드를 가질 수 있다.
// 2. 기능변화가 없는 경우 인터페이스로 설계
//인터페이스는 한번 정의되면 바꾸지 않는다(solid)
abstract class Base
{

	void create()
	{
		read();
	}
	void read()
	{
		update();
	}
	void update()
	{
		System.out.println("update");
	}
	void delete()
	{
		System.out.println("delete");
	}
	void showList()
	{
		System.out.println("showLIst");
	}

}


abstract class Memo extends Base
{
	public void deleteList() {
	delete();
	showList();
	}
}
//구현체
class MemoIm extends Memo{
	public void read() {
		update();
	}
}

interface Memo2
{

}

abstract class Bbs extends Base
{
	
}