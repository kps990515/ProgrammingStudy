# 1~Max까지 더하는 알고리즘

### 수학적 공식
- 짝수
>  * (max+1)*(max/2)  
- 홀수
> 1. max가 짝수일 때 : (max/2)*(max/2)
> 2. max가 홀수일 때 : (max/2+1)*(max/2+1)  

### 성공작

#### 메인
```java
import java.util.Scanner;

public class AlgorithmMain {
	public static void main(String[] args)
	{
		AlgorithmMain main = new AlgorithmMain();
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		long max = sc.nextLong();
		long result = main.sum(max);
		long resultEven = main.sumEven(max);
		long resultOdd = main.sumOdd(max);
		System.out.println("전부 더한 결과값 : "+result);
		System.out.println("짝수만 더한 결과값 : "+resultEven);
		System.out.println("홀수만 더한 결과값 : "+resultOdd);
	}
	```
#### 모두 다 더하는 공식
```java
	public long sum(long max)
	{
		long result=0L;
		//max가 짝수일 때 더하는 공식
		if(max%2==0)
		{
			result = ((max+1)*(max/2));
		}
		//max가 홀수일 때 더하는 공식
		else
		{
			result = ((max+1)*(max/2))+(max/2+1);
		}
		return result;
	}
```

#### 짝수만 더하는 공식
```java

	public long sumEven(long max)
	{
		long resultEven=0L;
		resultEven = (max/2)*(max/2)+(max/2);
		return resultEven;
	}
```

#### 홀수만 더하는 공식
```java

	public long sumOdd(long max)
	{
		long resultOdd = 0L;
		// max가 짝수일 때
		if(max%2==0)
		{
			resultOdd = (max/2)*(max/2);
		}
		// max가 홀수일 때
		else
		{
			resultOdd = (max/2+1)*(max/2+1);
		}
		return resultOdd;
	}
}
```
### 실패작
```java

		long resultOdd=0L;//1->+1 2->+0
			//123->1번, 1234 -> 1번, 12345 ->1번+3, 123456 -> 1번 +3, 1234567 -> 2번 12345678 -> 2번, 123456789 -> 2번 +5 12345678910 -> 2번 +5
			if(max==1)
			{
				resultOdd = 1;
				return resultOdd;
			}
			if((max%4)==1||(max&4)==2)
			{
				if((max&4)==1)
				{
					resultOdd = (max+1)*(max/4)+(max/2+1);
				}
				else
				{
					resultOdd = (max)*(max/4)+(max/2);
				}
			}
			//123->+2 , 12345 -> 1번, 1234567 -> 1번 +4, 123456789 -> 2번, 1234567891011 -> 2번 +6
			else
			{
				if(max%4==0)
				{
					resultOdd = (max)*(max/4);
				}
				else
				{
					resultOdd = (max+1)*((max+1)/4);
				}
			}
```
