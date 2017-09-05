
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
