import java.util.Arrays;
import java.util.Random;

public class Math {
	
	public void testMath() {
		
		//���밪 ���ϱ�
		int a = java.lang.Math.abs(-123);
		
		//�ݿø�
		long b = java.lang.Math.round(123.5);
		
		//�ø�
		double c = java.lang.Math.ceil(343.1543);
		
		//����
		double d = java.lang.Math.floor(563.8);
		
		//0���� ũ�ų����� 1���� ���� �Ǽ��� ����
		java.lang.Math.random(); 
		
		Random random = new Random();
		
		//1���� 100������ ������ ���� ��������
		random.nextInt(100); // -> 0~99������ ���� return
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
			//�ߺ� ����ó��
			for(int j=0;j<i;j++)
			{
				while(result[j]==result[i])
				{
					result[i]=random.nextInt(45)+1;
				}
			}
		}
		return result;
		/*�����迭 ����ϴ� ���
		ArrayList<Integer> result = new ArrayList<>();
		Set<Integer> treeSet = new TreeSet<>(); -> Set�� �ߺ����� �Էµ��� �ʴ´�
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
		//option. ���� ���ڿ� ��������
		//1. ���� ���ڿ� ���� üũ 
		//2. ���� ���ڿ��� ����
		//3. ���ĵ� ���ڿ� ��
		boolean result = false;
		//��ҹ��� ó��
		a=a.toLowerCase(); 
		b=b.toLowerCase();
		
		//���� ó�� �� ���ڿ� �ɰ���
		a = a.replace(" ","");
		b = b.replace(" ","");
		
		//���̺�
		if(a.length() != b.length())
		{
			return false;
		}
		
		//������ ���� char�迭�� ����
		char aTemp[] = a.toCharArray();
		char bTemp[] = b.toCharArray();
		
		//char�迭 ����
		Arrays.sort(aTemp);
		Arrays.sort(bTemp);

		//���ڿ� �� 
		return Arrays.equals(aTemp, bTemp);
		}
		//�� ��Ʈ���� ���鰳���� ���� count�� ���Ѵ�
		//�׸��� ���̰� �� ��Ʈ���� ù��° for������ ª�� ��Ʈ���� �ι�° for������ �����ѵ�
		//���� ���� ������ �迭������ �ٲ��ְ� count++�Ѵ�
		//���̰� �� ��Ʈ���� ���̸�ŭ count�� ������ True�� �����Ѵ�
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
