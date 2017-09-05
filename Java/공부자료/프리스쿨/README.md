# 프리스쿨

### 리눅스 명령어
> ls : 파일리스트  
ls-al : 숨김도 보여줌  
pwd : 경로  
cd : 디렉토리 변경  
cd.. : 상위 디렉토리로 이동  
cd + 파일/ : 파일 디렉토리로 이동  

### Git 명령어
> add 파일명 : 깃에 올리기  
status : 상태확인  
commit -m "메시지" : 지금 상태를 스냅샷하기  
push : 허브에 올리기  
pull : 땡겨오기  

### ArrayList
> 어레이리스트 장점(add,get)  
 저장되는 값은 값의 <주소>!!!
1. 크기 선언 필요X
2. 한 배열에 문자+숫자+소수점 모두 가능
3. 클래스 전체를 담을 수도 있음!!!!(기본 : OBJECT 형태)  
(문제는 리스트의 형태를 지정안하면 주소값을 출력!)  
(출력할때 리스트의 형태를 지정해줘야함!!)  
ex) 입력 : list.add(student) -> Call by Reference!!!  
    할때 마다 new로 초기화 해줘야함(주소바꾸기)!!!  
    안하면 주소값은 동일해서 0,1,2번방 값 동일해짐!!!   
    출력 : Student studentInput = (Student) list.get(0)  

### Generic
>제네릭 = 입력받을 값을 제한할 때 사용  
ex) ArrayList<Integer> students = new ArrayList<>();

### HaspMap
> put, get

### 객체, 클래스, 인스턴스
>
객체 : 물건 그 자체  
클래스 : 물건의 특성을 코드화  
인스턴스화 : new를 통해 메모리로 불러온 것  
new = 메모리에 올라감  
extends = new와 같이 메모리를 할당하지만 시점이 다르다(시작 시)
### 디자인패턴, 싱글톤
> <디자인패턴> = 코드작성의 패턴을 정리해놓음  
싱글톤 = new의 남용을 막히 위한 코드패턴

### Setter, Getter
> 정보를 보호할 필요가 있을 때
                  new를 막고 /  
                  setter = 정보 입력  
                  getter = 정보 가져오기 사용
		  (Set name / Get name)

### Overriding, Overloading
> Overriding  
상속받은 메소드와 메인에 있는 메소드 이름이 동일하면  
-> 메인에 있는 메소드가 우선     

> Overloading  
같은 메소드지만 파라미터값을 다르게 해서 여러개 생성
