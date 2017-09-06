import java.util.Arrays;
import java.util.Random;

public class Math {
	
	public void testMath() {
		
		//절대값 구하기
		int a = java.lang.Math.abs(-123);
		
		//반올림
		long b = java.lang.Math.round(123.5);
		
		//올림
		double c = java.lang.Math.ceil(343.1543);
		
		//내림
		double d = java.lang.Math.floor(563.8);
		
		//0보다 크거나같고 1보다 작은 실수를 리턴
		java.lang.Math.random(); 
		
		Random random = new Random();
		
		//1부터 100사이의 랜덤한 숫자 가져오기
		random.nextInt(100); // -> 0~99까지의 정수 return
		int r = random.nextInt(100)+1;
		double r2 = java.lang.Math.random()*100;
	}
	public static void main(String[] args)
	{
		Math main = new Math();
		int[] result = main.getLottoNumbers();
		for(int num : result)
		{
			System.out.print(num+" ");
		}
		boolean check = main.checkAnagram("fuck","that");
		System.out.println("");
		System.out.println(check);
	}
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
		/*동적배열 사용하는 방법
		ArrayList<Integer> result = new ArrayList<>();
		Set<Integer> treeSet = new TreeSet<>(); -> Set은 중복값이 입력되지 않는다
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
		 */
	}
	
	public boolean checkAnagram(String a, String b)
	{
		//option. 받은 문자열 공백제거
		//1. 받은 문자열 길이 체크 
		//2. 받은 문자열을 정렬
		//3. 정렬된 문자열 비교
		boolean result = false;
		//대소문자 처리
		a=a.toLowerCase(); 
		b=b.toLowerCase();
		
		//공백 처리 후 문자열 쪼개기
		a = a.replace(" ","");
		b = b.replace(" ","");
		
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
		//두 스트링의 공백개수를 빼서 count에 더한다
		//그리고 길이가 긴 스트링은 첫번째 for문으로 짧은 스트링을 두번째 for문으로 설정한뒤
		//같은 값이 나오면 배열값들을 바꿔주고 count++한다
		//길이가 긴 스트링의 길이만큼 count가 나오면 True를 리턴한다
		/*
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
	*/
	}
