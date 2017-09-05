# 조건 반복문을 사용한 알고리즘 문제

### 결과물

```java
Level 1

A   
AA   
AAA  
AAAA  
AAAAA  

Level 2    
    A  
   AA  
  AAA  
 AAAA  
AAAAA  

Level 3  
    A      
   AAA     
  AAAAA    
 AAAAAAA   
AAAAAAAAA

Level 3.5
    A    
   A A   
  A   A  
 A     A
A       A

Level 3.6
    A    
   A A   
  A   A  
 A     A
AAAAAAAAA

Level 3.8
    A    
   AAA   
  AAAAA  
 AAAAAAA
AAAAAAAAA
 AAAAAAA
  AAAAA  
   AAA   
    A    

Level 4
    A     
   A A    
  A A A   
 A A A A  
A A A A A
 AAAAAAA
  AAAAA  
   AAA   
    A    

Level 5
    A    
   A A   
  A   A  
 A     A
A       A
 A     A
  A   A
   A A  
    A   

Level 5.5
X       X
 X     X
  X   X  
   X X   
    X    
   X X   
  X   X  
 X     X
X       X

Level 5.7
AAAAAAAAA
A       A
A       A
A       A
A       A
A       A
A       A
A       A
AAAAAAAAA

Level 6
A      A      A      A      A      A      A
      A     A     A     A     A     A
           A    A    A    A    A
               A   A   A   A
                  A  A  A
                    A A
                     A

Level 7
               A            A
          A                      A
      A                              A
   A                                    A
 A                                        A
A                                          A
```

### 소스코드
##### 1. 메인

```java
public class Main {
	public static void main(String[] args) {
		Test test01 = new Test();
		System.out.println("Level 1");
		test01.level1("A",5);
		System.out.println("\n"+"Level 2");
		test01.level2("A",5);
		System.out.println("\n"+"Level 3");
		test01.level3("A",5);
		System.out.println("\n"+"Level 3.5");
		test01.level3point5("A",5);
		System.out.println("\n"+"Level 3.6");
		test01.level3point6("A",5);
		System.out.println("\n"+"Level 3.8");
		test01.level3point8("A",5);
		System.out.println("\n"+"Level 4");
		test01.level4("A",5);
		System.out.println("\n"+"Level 5");
		test01.level5("A",5);
		System.out.println("\n"+"Level 5.5");
		test01.level5point5("X",5);
		System.out.println("\n"+"Level 5.7");
		test01.level5point7("A",5);
		System.out.println("\n"+"Level 6");
		test01.level6("A",7);
		System.out.println("\n"+"Level 7");
		test01.level7("A",6);
	}
```
##### 2. 메소드
```java
public class Test {

	public void level1(String mark,int lines) {
		for(int index=0;index<lines;index++)
		{
			for(int innerindex=0;innerindex<=index; innerindex++)
			{
				System.out.print(mark);
			}
			System.out.println("");
		}
	}

	public void level2(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			for(int innerindex=0;innerindex<=lines-index; innerindex++)
			{
				System.out.print(mark);
			}
			System.out.println("");
		}
	}

	public void level3(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			for(int innerindex=0;innerindex<=(lines-index)*2; innerindex++)
			{
				System.out.print(mark);
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level3point5(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			for(int innerindex=0;innerindex<(lines-index)*2-1; innerindex++)
			{
				System.out.print(" ");
			}
			if(index!=lines) {
				System.out.print(mark);
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level3point6(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			if(index!=1) {
			for(int innerindex=0;innerindex<(lines-index)*2-1; innerindex++)
				{
					System.out.print(" ");
				}
			}else {
				for(int innerindex=0;innerindex<(lines-index)*2-1; innerindex++)
				{
					System.out.print(mark);
				}
			}
			if(index!=lines) {
				System.out.print(mark);
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level3point8(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			for(int innerindex=0;innerindex<=(lines-index)*2; innerindex++)
			{
				System.out.print(mark);
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
		for(int index=1;index<lines;index++)
		{
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			for(int innerindex=(lines-index)*2-1;innerindex>0; innerindex--)
			{
				System.out.print(mark);
			}
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level4(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			for(int innerindex=0;innerindex<=(lines-index); innerindex++)
			{
				System.out.print(mark);
				System.out.print(" ");
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
		for(int index=1;index<lines;index++)
		{
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			for(int innerindex=(lines-index)*2-1;innerindex>0; innerindex--)
			{
				System.out.print(mark);
			}
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level5(String mark,int lines) {
		for(int index=lines;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			for(int innerindex=0;innerindex<(lines-index)*2-1; innerindex++)
			{
				System.out.print(" ");
			}
			if(index!=lines) {
				System.out.print(mark);
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
		for(int index=1;index<lines;index++)
		{
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			for(int innerindex=(lines-index)*2-2;innerindex>1; innerindex--)
			{
				System.out.print(" ");
			}
			if(index!=lines-1) {
				System.out.print(mark);
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level5point5(String mark,int lines) {
		for(int index=0;index<lines;index++)
		{
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			for(int innerindex=(lines-index)*2-3;innerindex>0; innerindex--)
			{
				System.out.print(" ");
			}
			if(index!=lines-1) {
				System.out.print(mark);
			}
			for(int innerspace=0;innerspace<index;innerspace++)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
		for(int index=lines-1;index>0;index--)
		{
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			for(int innerindex=0;innerindex<(lines-index)*2-1; innerindex++)
			{
				System.out.print(" ");
			}
			System.out.print(mark);
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public void level5point7(String mark,int lines) {

		for(int index=0; index<lines*2-1; index++){
			if(index==0){
				for(int innerindex=0; innerindex<lines*2-1; innerindex++){
				System.out.print(mark);
				}
				System.out.println("");
			}
			if(index!=0&&index!=lines*2-2){
				System.out.print(mark);
				for(int innerindex=0; innerindex<lines*2-3; innerindex++) {
					System.out.print(" ");
				}
				System.out.print(mark);
				System.out.println("");
			}
			if(index==lines*2-2)
			{
				for(int innerindex=0; innerindex<lines*2-1; innerindex++){
				System.out.print(mark);
				}
				System.out.println("");
			}
		}
	}
	public void level6(String mark,int lines) {
		for(int index=lines;index>0;index--)//6,5,4,3,2,1
		{
			for(int innerspace3=lines;innerspace3>index;innerspace3--)//1,2,3,4,5,6
			{
				for(int innerspace4=innerspace3;innerspace4>1;innerspace4--)//1->0 2->1 3->2
				{
					System.out.print(" ");
				}
			}
			for(int innerspace=index;innerspace>1;innerspace--)
			{
				System.out.print(mark);
				int innerspace2=index-1;
				while(innerspace2>0) {
						System.out.print(" ");
						innerspace2--;
				}		
			}
				System.out.print(mark);
				System.out.println("");
		}
	}
	public void level7(String mark, int lines) {
		/*
		 for(int index=lines;index>0;index--)//6,5,4,3,2,1
		{
			for(int innerspace3=lines;innerspace3>index;innerspace3--)//1,2,3,4,5,6
			{
				for(int innerspace4=innerspace3;innerspace4>1;innerspace4--)//1->0 2->1 3->2
				{
					System.out.print(" ");
				}
			}
		 */
		for(int index=lines;index>0;index--) //6,5,4,3,2,1 6번
		{
			for(int leftspace=index;leftspace>0;leftspace--)//6->6,5,4,3,2,1 6번
			{
				for(int leftspace2=leftspace;leftspace2>1;leftspace2--) //6->6->5+4+3+2+1
				{
					System.out.print(" ");
				}
			}
			System.out.print(mark);
			for(int innerspace=lines;innerspace>=index;innerspace--)// 6->1,2,3,4,5,6
			{
				for(int innerspace2=innerspace*2;innerspace2>0;innerspace2--) //6->
				{
					System.out.print(" ");
				}
			}
			System.out.print(mark);
			System.out.println("");
		}
	}

}
```
