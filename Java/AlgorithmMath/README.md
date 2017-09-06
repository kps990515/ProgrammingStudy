# 수학공식 & 로또 & 아나그램

##### import

```java
import java.util.Arrays;
import java.util.Random;
```

### 수학공식

- 절대값 구하기 - Math.abs()

```java
int a = java.lang.Math.abs(-123);
```

- 반올림 하기 - Math.round()

```java
long b = java.lang.Math.round(123.5);
```

- 올림 하기 - Math.round()

```java
double b = java.lang.Math.ceil(123.2);
```

- 내림 하기 - Math.floor()

```java
double b = java.lang.Math.floor(123.7);
```

- Math random - Math.random() - 0~1사이의 실수

- Random

```java
//1부터 100사이의 랜덤한 숫자 가져오기
		random.nextInt(100); // -> 0~99까지의 정수 return
		int r = random.nextInt(100)+1;
		double r2 = java.lang.Math.random()*100;
```

### 로또

##### 메인

```java
public static void main(String[] args)
	{
		Math main = new Math();
		int[] result = main.getLottoNumbers();
		for(int num : result)
		{
			System.out.print(num+" ");
		}
	}
```

##### 1. 반복문 사용

>1. 첫번째 for문(i)으로 방 6개에 모두 random사용하여 숫자를 채운다
>2. 두번째 for문(j)으로 0번방부터 i-1번방까지 돌린다
>3. while문으로 돌리는 동안 j==i이면(방들끼리 숫자가 겹치면)  
 반복해서 random으로 다시 i번 방의 숫자를 변경한다

###### 코드

```java
public int[] getLottoNumbers() {
		int[] result = new int[6];
		Random random = new Random();
		for(int i=0; i<result.length; i++)
		{
			result[i]=random.nextInt(45)+1;
			//중복 예외처리
			for(int j=0;j<i;j++)
			{
				while(result[j]==result[i])
				{
					result[i]=random.nextInt(45)+1;
				}
			}
		}
		return result;
```

##### 2. 동적배열 사용

>동적배열 - 크기를 제한하지 않는 객체 배열  
>종류 : List, Set, Map  
>List = 인덱스와 값을 가지고 있는 배열  
>Set = 리스트와 유사한데 중복값이 입력되지 않는다  
>Map = 키와 값을 가지고 있는 동적 배열, 값의 중복 허용  

###### 방법
>1. Set을 이용하여 for문으로 6개가 찰때까지 random돌린다
>2. iterator를 사용하여 List에 값들을 집어넣는다

###### 코드

```java
		ArrayList<Integer> result = new ArrayList<>();
		Set<Integer> treeSet = new TreeSet<>();
		Random random = new Random();
		for( ; 6> treeSet.size() ; )
		{
			treeSet.add(random.nextInt(45)+1;
		}

		Iterator<Integer> iterator = treeset.iterator();
		while(iterator.hasnext())
		{
			result.add(iterator.next());
		}

		return result
	}
```

### 아나그램

##### 정석 알고리즘

###### 방법
>1. option. 받은 문자열 공백제거
>2. option. 대소문자 변경하기
>3. 받은 문자열 길이 체크
>4. 받은 문자열을 정렬
>5. 정렬된 문자열 비교

###### 코드

```java
public boolean checkAnagram(String a, String b)
	{
		boolean result = false;

		//공백 처리
		a = a.replace(" ","");
		b = b.replace(" ","");

		//대소문자 처리
		a=a.toLowerCase();
		b=b.toLowerCase();

		//길이비교
		if(a.length() != b.length())
		{
			return false;
		}

		//정렬을 위해 char배열로 변경
		char aTemp[] = a.toCharArray();
		char bTemp[] = b.toCharArray();

		//char배열 정렬
		Arrays.sort(aTemp);
		Arrays.sort(bTemp);

		//문자열 비교
		return Arrays.equals(aTemp, bTemp);
	}
```

##### 내가 푼 방식

###### 방법
>1. 두 스트링의 공백개수를 빼서 count에 더한다
>2. 길이가 긴 스트링은 첫번째 for문으로, 짧은 스트링은 두번째 for문으로 설정
>3. 같은 값이 나오면 배열값들을 바꿔주고 count++한다
>4. 길이가 긴 스트링의 길이만큼 count가 나오면 True를 리턴한다

###### 코드

```java
		int count=0;
		int length=0;
		int tempspace=0;
		int tempspace2=0;
		boolean result = false;
		String[] temp = a.split("");
		String[] temp2 = b.split("");
		for(int i=0;i<temp.length;i++)
		{
			if(temp[i].equals(" "))
			{
				tempspace++;
			}
		}
		for(int i=0;i<temp2.length;i++)
		{
			if(temp2[i].equals(" "))
			{
				tempspace2++;
			}
		}
		if(tempspace!=tempspace2)
		{
			count=java.lang.Math.abs(tempspace-tempspace2);
		}
		if(temp.length<=temp2.length)
		{
			length = temp2.length;
			for(int i=0;i<temp2.length;i++)
			{
				for(int j=0;j<temp.length;j++)
				{
					if(temp2[i].equals(temp[j]))
					{
						temp2[i]="0";
						temp[j]="1";
						count++;
					}
				}
			}
		}
		else
		{
			length = temp.length;
			for(int i=0;i<temp.length;i++)
			{
				for(int j=0;j<temp2.length;j++)
				{
					if(temp[i].equals(temp2[j]))
					{
						temp[i]="0";
						temp2[j]="1";
						count++;
					}
				}
			}
		}

		if(count==length)
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
}
```
