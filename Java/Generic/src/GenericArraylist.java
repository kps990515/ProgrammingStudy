
public class GenericArraylist<T> {
	// Ÿ���� �ΰ� �̻� ���� �� �ִ� �����迭��ü�� �����
	
	private Object list[];
	
	// ���� ���� ���� ���¿��� ������ ���� üũ�� �� �� �ֱ� ������
	// ����Ҹ� �ʱ�ȭ���ִ� �۾� �ʿ�
	public GenericArraylist() {
		
		//if(Type instanceOf target)
		list = new Object[1];
	}
	
	public void add(T item) {
		// �迭�� ũ�⸦ �ӽ÷� �÷��� ���(��ĭ �ø���)
		Object tempList[] = new Object[size()+1];
		//���� �迭�� ������ �ø� �迭���ٰ� �����ϱ�
		for(int i=0; i<list.length; i++) {
			tempList[i] = list[i];
		}
		//������ ��ĭ�� item�� ����ֱ�
		tempList[list.length] = item;
		//�ø� �迭�� �ٽ� list�� ����ֱ�
		list = tempList;
	}
	
	public void remove(int position) {
		// �迭�� ũ�⸦ �ӽ÷� �ٿ��� ���(��ĭ ���̱�)
		Object tempList[] = new Object[size()-1];
		// position ������ �����͸� �ӽð������� ����
		for(int i=0; i<position-1; i++) {
			tempList[i] = list[i];
		}
		// position ������ �����͸� �ӽð������� ����
		for(int i=position+1; i<list.length; i++)
		{
			tempList[i-1] = list[i];
		}
		//���� �迭�� �ٽ� list�� ����ֱ�
		list = tempList;
	}
	
	public void update(int position, T item) {
		// �迭�� ũ�⸦ �ӽ÷� �ٿ��� ���(��ĭ ���̱�)
		Object tempList[] = new Object[size()];
		// position ������ �����͸� �ӽð������� ����
		for(int i=0; i<position-1; i++) {
			tempList[i] = list[i];
		}
		// position�� update�� item �ֱ�
		tempList[position] = item;
		// position ������ �����͸� �ӽð������� ����
		for(int i=position+1; i<list.length; i++)
		{
			tempList[i-1] = list[i];
		}
		//���� �迭�� �ٽ� list�� ����ֱ�
		list = tempList;
	}
	
	public Object read(int position) {
		Object item = list[position];
		return item;
	}
	
	public Object[] getList() {
		return list;
	}
	
	public int size() {
		return list.length-1;
	}
	
}
	