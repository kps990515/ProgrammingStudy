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
		//선언
		//타입 + 이름[] = new + 타입 +[배열공간크기];
		// 기본형인 int,long 등은 공간을 할당하는 것만으로 기본값 0이 할당됨
		int intArray[] = new int[10];
		System.out.println(intArray[7]); // 출력 값 0
		
		Item itemArray[] = new Item[10];
		int itemLength = itemArray.length; // itemArray.length를 직접 for문에 쓰지 않는 것은 권장(값 변경 위험)
		for(int i=0; i<itemLength; i++) //객체 배열의 경우 각 방을 그 객체 타입으로 new해줘야 한다
		{
			itemArray[i] = new Item();
		}
		System.out.println(itemArray[7]); // 출력값 null
	}
	// index를 포함하는 동적 객체배열
	public void checkList() {
		//선언은 일반 객체를 초기화 하는것과 같다
		ArrayList list = new ArrayList();
		
		//입력
		list.add(new Item()); // 0번 index로 생성
		list.add(new Item()); // 문제는 출력시 객체의 주소가 출력됨!!!!
		list.add("Hello");
		list.add(123);
		list.add(new Student());
		//조회
		list.get(0); // index가 0번째인 값을 가져온다
		//수정
		list.set(1, new Item()); // 1번 index의 아이템이 새로운  item으로 대체된다
		//add의 다른방법(밀어내기)
		list.add(1, new Item()); // 1번부터 이후의 아이템 index를 하나씩 증가시키고
		                         // 자신이 1번으로 들어간다(밀어내기)
		//삭제(땡겨오기)
		list.remove(1); //특정 index 삭제
		                //삭제하면 뒤에 데이터들은 하나씩 앞으로 이동한다(땡겨오기)
		//출력
		for(Object object : list)
		{
			Item item = (Item)object;
			//이렇게 하나씩 해줘야함... 그래서 귀찮 <제네릭>을 쓰자
		}
	}
	public void checkGeneric() {
		//제네릭을 설정하면 그 타입밖에 못 넣는다
		//타입 + <제네릭타입> + 변수이름; // <제네릭타입>은 클래스만 가능
		ArrayList<Item> list = new ArrayList<>();
		// list.add(123); -> 에러
		list.add(new Item());
		//출력이 쉬움
		for(Item item : list)
		{
			item.getMyName();
		}
	}
	// List와 유사한데 중복값을 허용하지 않는 동적 객체배열
	public void checkSet() {
		HashSet<String> set = new HashSet<>();
		set.add("hello");
		set.add("good to see you");
		set.add("see you");
		
		//set은 iterator을 달아서 순서대로 처리해 줄 수 있다.
		Iterator iterator = set.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next()); //object형으로 리턴
		}
		//향상된 for문도 가능
		for(String item : set)
		{
			System.out.println(item);
		}
		//삭제
		for(String item : set)
		{
			set.remove(item);
		}
		set.remove("hello");
	}
	// key, value로 구성된 동적 객체배열
	public void checkMap() {
		//선언
		HashMap<String, Integer> map = new HashMap<>();
		//입력
		map.put("key01", 1234);
		map.put("key02", 123456);
		//조회
		System.out.println(map.get("key01"));
		//반복문으로 출력 (iterator안됌)
		Set<String> keys = map.keySet(); //map의 key 꺼내오기
		for(String key : keys)
		{
			System.out.println(map.get(key));
		}
	}

}
class Item {
	private String myName = "지코";
	public String getMyName() {
		return myName;
	}
}
class Student{
	public void move() {
	}
}