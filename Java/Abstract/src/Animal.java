//������Ʈ(������ �����Ѵ�)
abstract public class Animal {
	pub
}


// 1. ��ɺ�ȭ�� ���� �Ͼ�� ��� �߻�Ŭ������ ����
//�߻�Ŭ������ �Ϲ� �޼ҵ带 ���� �� �ִ�.
// 2. ��ɺ�ȭ�� ���� ��� �������̽��� ����
//�������̽��� �ѹ� ���ǵǸ� �ٲ��� �ʴ´�(solid)
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
//����ü
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