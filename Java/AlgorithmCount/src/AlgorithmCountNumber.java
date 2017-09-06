/*
 * 입력값을 정수 m,n으로 받았을 때
 * 1부터 정수m까지 정수 n이 몇개 있는지
 * 
 * 예 10000,8
 * 
 * 
 */


public class AlgorithmCountNumber {

	public static void main(String[] args)
	{
		AlgorithmCountNumber main = new AlgorithmCountNumber();
		int result = main.count(10,8);
		System.out.println(result);
	}
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
