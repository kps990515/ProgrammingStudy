import java.util.Scanner;

public class AlgorithmMain {
	public static void main(String[] args)
	{
		AlgorithmMain main = new AlgorithmMain();
		Scanner sc = new Scanner(System.in);
		System.out.println("���ڸ� �Է��ϼ���");
		long max = sc.nextLong();
		long result = main.sum(max);
		long resultEven = main.sumEven(max);
		long resultOdd = main.sumOdd(max);
		System.out.println("���� ���� ����� : "+result);
		System.out.println("¦���� ���� ����� : "+resultEven);
		System.out.println("Ȧ���� ���� ����� : "+resultOdd);
	}
	public long sum(long max)
	{
		long result=0L;
		//max�� ¦���� �� ���ϴ� ����
		if(max%2==0) 
		{
			result = ((max+1)*(max/2)); 
		}
		//max�� Ȧ���� �� ���ϴ� ����
		else 
		{
			result = ((max+1)*(max/2))+(max/2+1);
		}
		return result;
	}
	//¦���� ���ϴ� ����
	public long sumEven(long max)
	{
		long resultEven=0L;
		resultEven = (max/2)*(max/2)+(max/2);
		return resultEven;
	}
	//Ȧ���� ���ϴ� ����
	public long sumOdd(long max)
	{
		long resultOdd = 0L;
		// max�� ¦���� �� 
		if(max%2==0)
		{
			resultOdd = (max/2)*(max/2);
		}
		// max�� Ȧ���� ��
		else
		{
			resultOdd = (max/2+1)*(max/2+1);
		}
		return resultOdd;
	}
}
		
		/*
		long resultOdd=0L;//1->+1 2->+0
			//123->1��, 1234 -> 1��, 12345 ->1��+3, 123456 -> 1�� +3, 1234567 -> 2�� 12345678 -> 2��, 123456789 -> 2�� +5 12345678910 -> 2�� +5
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
			//123->+2 , 12345 -> 1��, 1234567 -> 1�� +4, 123456789 -> 2��, 1234567891011 -> 2�� +6
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
			*/


