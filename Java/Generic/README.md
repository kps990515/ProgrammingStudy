# 타입을 두개 이상 담을 수 있는 동적배열객체

###  GenericArrayList <T> 타입
```java
public class GenericArraylist<T> {
	// 타입을 두개 이상 담을 수 있는 동적배열객체를 만들기
	private Object list[];
	// 값을 넣지 않은 상태에서 사이즈 등의 체크를 할 수 있기 때문에
	// 저장소를 초기화해주는 작업 필요
	public GenericArraylist() {
		//if(Type instanceOf target)
		list = new Object[1];
	}
```

### add
```java
public void add(T item) {
  // 배열의 크기를 임시로 늘려서 사용(한칸 늘리기)
  Object tempList[] = new Object[size()+1];
  //원래 배열의 값들을 늘린 배열에다가 복사하기
  for(int i=0; i<list.length; i++) {
    tempList[i] = list[i];
  }
  //마지막 한칸에 item을 집어넣기
  tempList[list.length] = item;
  //늘린 배열을 다시 list에 집어넣기
  list = tempList;
}
```

### remove
```java
public void remove(int position) {
  // 배열의 크기를 임시로 줄여서 사용(한칸 줄이기)
  Object tempList[] = new Object[size()-1];
  // position 이전의 데이터를 임시공간으로 복사
  for(int i=0; i<position-1; i++) {
    tempList[i] = list[i];
  }
  // position 이후의 데이터를 임시공간으로 복사
  for(int i=position+1; i<list.length; i++)
  {
    tempList[i-1] = list[i];
  }
  //줄인 배열을 다시 list에 집어넣기
  list = tempList;
}
```

### update
```java
public void update(int position, T item) {
  // 배열의 크기를 임시로 줄여서 사용(한칸 줄이기)
  Object tempList[] = new Object[size()];
  // position 이전의 데이터를 임시공간으로 복사
  for(int i=0; i<position-1; i++) {
    tempList[i] = list[i];
  }
  // position에 update할 item 넣기
  tempList[position] = item;
  // position 이후의 데이터를 임시공간으로 복사
  for(int i=position+1; i<list.length; i++)
  {
    tempList[i-1] = list[i];
  }
  //줄인 배열을 다시 list에 집어넣기
  list = tempList;
}
```

### read
```java
public Object read(int position) {
  Object item = list[position];
  return item;
}
```

### getList
```java
public Object[] getList() {
  return list;
}
```

### size()
```java
	public int size() {
		return list.length-1;
	}
```  
