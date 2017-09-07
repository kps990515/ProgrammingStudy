package com.kimjaeho.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionMain {

	public static void main(String[] args) {
		

	}
	
	public void checkArray() {
		//����
		//Ÿ�� + �̸�[] = new + Ÿ�� +[�迭����ũ��];
		// �⺻���� int,long ���� ������ �Ҵ��ϴ� �͸����� �⺻�� 0�� �Ҵ��
		int intArray[] = new int[10];
		System.out.println(intArray[7]); // ��� �� 0
		
		Item itemArray[] = new Item[10];
		int itemLength = itemArray.length; // itemArray.length�� ���� for���� ���� �ʴ� ���� ����(�� ���� ����)
		for(int i=0; i<itemLength; i++) //��ü �迭�� ��� �� ���� �� ��ü Ÿ������ new����� �Ѵ�
		{
			itemArray[i] = new Item();
		}
		System.out.println(itemArray[7]); // ��°� null
	}
	// index�� �����ϴ� ���� ��ü�迭
	public void checkList() {
		//������ �Ϲ� ��ü�� �ʱ�ȭ �ϴ°Ͱ� ����
		ArrayList list = new ArrayList();
		
		//�Է�
		list.add(new Item()); // 0�� index�� ����
		list.add(new Item()); // ������ ��½� ��ü�� �ּҰ� ��µ�!!!!
		list.add("Hello");
		list.add(123);
		list.add(new Student());
		//��ȸ
		list.get(0); // index�� 0��°�� ���� �����´�
		//����
		list.set(1, new Item()); // 1�� index�� �������� ���ο�  item���� ��ü�ȴ�
		//add�� �ٸ����(�о��)
		list.add(1, new Item()); // 1������ ������ ������ index�� �ϳ��� ������Ű��
		                         // �ڽ��� 1������ ����(�о��)
		//����(���ܿ���)
		list.remove(1); //Ư�� index ����
		                //�����ϸ� �ڿ� �����͵��� �ϳ��� ������ �̵��Ѵ�(���ܿ���)
		//���
		for(Object object : list)
		{
			Item item = (Item)object;
			//�̷��� �ϳ��� �������... �׷��� ���� <���׸�>�� ����
		}
	}
	public void checkGeneric() {
		//���׸��� �����ϸ� �� Ÿ�Թۿ� �� �ִ´�
		//Ÿ�� + <���׸�Ÿ��> + �����̸�; // <���׸�Ÿ��>�� Ŭ������ ����
		ArrayList<Item> list = new ArrayList<>();
		// list.add(123); -> ����
		list.add(new Item());
		//����� ����
		for(Item item : list)
		{
			item.getMyName();
		}
	}
	// List�� �����ѵ� �ߺ����� ������� �ʴ� ���� ��ü�迭
	public void checkSet() {
		HashSet<String> set = new HashSet<>();
		set.add("hello");
		set.add("good to see you");
		set.add("see you");
		
		//set�� iterator�� �޾Ƽ� ������� ó���� �� �� �ִ�.
		Iterator iterator = set.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next()); //object������ ����
		}
		//���� for���� ����
		for(String item : set)
		{
			System.out.println(item);
		}
		//����
		for(String item : set)
		{
			set.remove(item);
		}
		set.remove("hello");
	}
	// key, value�� ������ ���� ��ü�迭
	public void checkMap() {
		//����
		HashMap<String, Integer> map = new HashMap<>();
		//�Է�
		map.put("key01", 1234);
		map.put("key02", 123456);
		//��ȸ
		System.out.println(map.get("key01"));
		//�ݺ������� ��� (iterator�ȉ�)
		Set<String> keys = map.keySet(); //map�� key ��������
		for(String key : keys)
		{
			System.out.println(map.get(key));
		}
	}

}
class Item {
	private String myName = "����";
	public String getMyName() {
		return myName;
	}
}
class Student{
	public void move() {
	}
}