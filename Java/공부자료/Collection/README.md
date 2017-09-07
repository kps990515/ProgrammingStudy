# collection

### Array, ArrayList, HashSet, HashMap

##### Array

- 선언 : 타입 + 이름[] = new + 타입 +[배열공간크기]  
- 기본형인 int,long 등은 공간을 할당하는 것만으로 기본값 0이 할당됨

```java

	public void checkArray() {

		int intArray[] = new int[10];
		System.out.println(intArray[7]);
    // 출력 기본값 0
		Item itemArray[] = new Item[10];
		int itemLength = itemArray.length;
    // itemArray.length를 직접 for문에 쓰지 않는 것은 권장(값 변경 위험)
		for(int i=0; i<itemLength; i++)
    //객체 배열의 경우 각 방을 그 객체 타입으로 new해줘야 한다
		{
			itemArray[i] = new Item();
		}
		System.out.println(itemArray[7]); // 출력값 null
	}
```

### 동적 객체배열

##### 1. List(ArrayList)

- index를 포함하는 동적 객체배열

- 선언은 일반 객체를 초기화 하는것과 같다
```java
  	ArrayList list = new ArrayList();
```
- 입력 - add
```java
		list.add(new Item());
    // 0번 index로 생성
		list.add(new Item());
		list.add("Hello");
		list.add(123);
		list.add(new Student());
```
- 조회 - get
```java
		list.get(0); // index가 0번째인 값을 가져온다
```
- 출력 - print + get
```java
    System.out.println(list.get(index));
    //get 함수 없이 출력하면 list의 주소값이 출력!!
```
- 수정 - set(index, 새로 넣을 값)
```java
		list.set(1, new Item());
    // 1번 index의 아이템이 새로운  item으로 대체된다
```
- 밀어내기 - add(index, 넣을 값)
```java
    list.add(1, new Item());
    // index번부터 이후의 아이템 index를 하나씩 증가시키고
    // 자신이 index번으로 들어간다(밀어내기)
```
- 삭제(땡겨오기) - remove(index)
```java
		list.remove(1);
    //특정 index 삭제
    //삭제하면 뒤에 데이터들은 하나씩 앞으로 이동한다(땡겨오기)
```
- 객체 배열일 때 출력(Generic없을 시)
```java
		for(Object object : list) // 내부 값 몰라서 Object
		{
			Item item = (Item)object;
			//이렇게 하나씩 해줘야함... 그래서 귀찮 <제네릭>을 쓰자
		}
```
##### 2. ArrayList + Generic

- 제네릭을 설정하면 그 타입밖에 못 넣는다
- 선언 : 타입 + <제네릭타입> + 변수이름
```java
ArrayList<Item> list = new ArrayList<>();
```
- <제네릭타입>은 클래스만 가능  

- 입력 - add()
```java
list.add(new Item());
// list.add(123); -> 에러
```
- 출력
```java
		for(Item item : list)
		{
			item.getMyName();
		}
```

##### Set
- List와 유사한데 중복값을 허용하지 않는 동적 객체배열  
- 선언 & 입력
```java
		HashSet<String> set = new HashSet<>();
		set.add("hello");
		set.add("good to see you");
		set.add("see you");
```
- 출력(Iterator)
```java
		//set은 iterator을 달아서 순서대로 처리해 줄 수 있다.
		Iterator iterator = set.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next()); //object형으로 리턴
		}
```
- 출력(향상된 For문)
```java
		//향상된 for문도 가능
		for(String item : set)
		{
			System.out.println(item);
		}
```
- 삭제 - set.remove(객체나 내부 값)
```java
		for(String item : set)
		{
			set.remove(item);
		}
		set.remove("hello");
```

##### Map(HashMap)

- key, value로 구성된 동적 객체배열  

- 선언
```java
		HashMap<String, Integer> map = new HashMap<>();
    // <Key타입, 내부 값 타입>
```
- 입력 - put(key,value)
```java
		map.put("key01", 1234);
		map.put("key02", 123456);
```
- 조회 - get(key)
```java
		System.out.println(map.get("key01"));
```
- 출력 - 반복문사용 (iterator안됌)
```java
//map의 key 꺼내오기(Set collection으로)
		Set<String> keys = map.keySet();
//index대신 key를 사용하여 불러온다
		for(String key : keys)
		{
			System.out.println(map.get(key));
		}
```
##### 사용한 클래스
```java
class Item {
	private String myName = "지코";
	public String getMyName() {
		return myName;
	}
}
class Student{
	public void move() {
	}
```
