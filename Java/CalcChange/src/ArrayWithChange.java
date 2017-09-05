
public class ArrayWithChange {

	public static void main(String[] args) {
		ArrayWithChange newClass = new ArrayWithChange();
		newClass.calc(10000, 3750);
		}
	public void calc(int pay, int buy)
	{	
		int changeArray[] = {5000,1000,500,100,50,10};
		int change = pay-buy;
		print("ÃÑ °Å½º¸§µ· : ", change);
		for(int money : changeArray)
		{
			System.out.println(money+"¿ø : "+change/money);
			change %= money;
		}
	}
	public void print(String flag, int count) {
		System.out.println(flag+count);
		
	}
}
