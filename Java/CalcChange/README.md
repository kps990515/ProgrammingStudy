## 1. 조건문, 반복문 설명
```java
public class ControlFlowEx {

	// 조건문
	// if
	public void checkIf() {
		int a = 10;
		int b = 5;
		if(a > b) {
			//a가 b보다 크면 실행되는 영역
			System.out.println("a가 b보다 큽니다");
		}else if(a == b) {
			//a와 b가 같으면 실행되는 영역
			System.out.println("a와 b가 같습니다");
		}else {
			System.out.println("a가 b보다 작습니다");
			//a가 b보다 작으면 실행되는 영역
		}
	}

	//switch
	public void checkSwitch() {
		int a = 10;
		switch(a) {
			case 5:
				System.out.println("a가 값이 5입니다");
				break;
			case 10:
				System.out.println("a의 값이 10입니다");
				break;
			default:
				System.out.println("a가 5,10이 아닌 다른 값 입니다");
				break;
		}
	}

	//반복문
	//for
	public void checkFor() {
		// 배열은 Index 0부터 시작
		int array[] = {1,2,3,4,5,6,7};

		//과거 for문
		for(int i=0; i<array.length; i++){
			System.out.println(array[i]);
		}

		//향상된 for문 <왼쪽 : 오른쪽 자료형에 맡게 / 오른쪽 : for문에 쓸 ...>
		for(int j : array) {
			System.out.println(j);
		}
	}

	//while
	public void checkWhile() {
		int array[] = {1,2,3,4,5,6,7};

		//while문이란 반복이 가능한 if문
		int count=0;
		while(count<array.length) {
			System.out.println(array[count]);
			count++;
		}
	}

	//do while
	//while문의 조건에 상관없이 do 블럭이 한번은 실행된다
	public void checkDoWhile() {
		int array[] = {1,2,3,4,5,6,7};
		int count = 0;
		do {
			System.out.println(array[count]);
			count++;			
		}while(count < array.length);
	}

}
```


## 2. 잔돈 구하기

### 1. 반복문 없이

```java
public class CalcChange {

	public static void main(String[] args) {
		CalcChange newClass = new CalcChange();
		newClass.calc(10000, 3750);
	}
	public void calc(int pay, int buy)
	{
		//1번째 방법
		int gross = pay-buy;
		int a = (pay-buy)/5000;
		int b = (pay-(buy+5000*a))/1000;
		int c = (pay-(buy+5000*a+1000*b))/500;
		int d = (pay-(buy+5000*a+1000*b+500*c))/100;
		int e = (pay-(buy+5000*a+1000*b+500*c+100*d))/50;
		int f = (pay-(buy+5000*a+1000*b+500*c+100*d+50*e))/10;
		System.out.println("총 거스름돈 : "+gross+"\n5000원 : "+a+"\n1000원 : "+b+"\n500원 : "+c+"\n100원 : "+d+"\n50원 : "+e+"\n10원 : "+f);


		/*2번째 방법
		int change = pay-buy;
		System.out.pritnln("거스름돈 = %d \n",change);
		int temp - change/5000;
		System.out.pritnln("5000원 = %d \n",temp);
		change = change %5000;
		temp = change/1000;
		System.out.pritnln("1000원 = %d \n",temp);
		change = change%1000;
		temp = change/500;
		System.out.pritnln("500원 = %d \n",temp);
		change = change%500;
		temp = change/100;
		System.out.pritnln("100원 = %d \n",change/100);
		change = change%100;
		System.out.pritnln("100원 = %d \n",change/50);
		change = change%50;
		System.out.pritnln("100원 = %d \n",change/10);
		*/
	}

}

```
### 2. 배열 + 반복문 사용
```java
public class ArrayWithChange {

	public static void main(String[] args) {
		ArrayWithChange newClass = new ArrayWithChange();
		newClass.calc(10000, 3750);
		}
	public void calc(int pay, int buy)
	{
		int changeArray[] = {5000,1000,500,100,50,10};
		int change = pay-buy;
		print("총 거스름돈 : ", change);
		for(int money : changeArray)
		{
			System.out.println(money+"원 : "+change/money);
			change %= money;
		}
	}
	public void print(String flag, int count) {
		System.out.println(flag+count);

	}
}
```
