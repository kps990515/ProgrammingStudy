# 정수를 스트링으로 변환해서 푸는 알고리즘

### 알고리즘 내용

 > 입력값을 정수 m,n으로 받았을 때  
  1부터 정수m까지 정수 n이 몇개 있는지
  예 100,8 -> 20개

```java
public class AlgorithmCountNumber {

```

##### 메인

```java
	public static void main(String[] args)
	{
		AlgorithmCountNumber main = new AlgorithmCountNumber();
		int result = main.count(10,8);
		System.out.println(result);
	}
```

##### 알고리즘

> 1. n을 스트링으로 변환(P)
2. for문으로 통해 m을 1부터 입력값까지 돌린다
3. 돌릴 때 마다 m을 스트링으로 변환(O)
4. split("")으로 m을 각 숫자스트링단위로 쪼갠다(temp[])
5. temp[]의 길이만큼 for문을 돌리면서  
temp[index].equals(p)를 통해 개수를 ++ 한다. 

```java

	public int count(int m, int n)
	{
		int result = 0;
		String p=Integer.toString(n);
		for(int number=1;number<=m;number++)
		{
			String o=Integer.toString(number);
			String temp[] = o.split("");
			for(int index=0;index<temp.length;index++)
			{
				if(temp[index].equals(p))
				{
					result++;
				}
			}
		}
		return result;
	}
}
```
