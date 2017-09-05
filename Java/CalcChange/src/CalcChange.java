
public class CalcChange {
	
	public static void main(String[] args) {
		CalcChange newClass = new CalcChange();
		newClass.calc(10000, 3750);
	}
	public void calc(int pay, int buy)
	{
		//1번째 방법
		int gross = pay-buy;
		int a = (pay-buy)/5000;
		int b = (pay-(buy+5000*a))/1000;
		int c = (pay-(buy+5000*a+1000*b))/500;
		int d = (pay-(buy+5000*a+1000*b+500*c))/100;
		int e = (pay-(buy+5000*a+1000*b+500*c+100*d))/50;
		int f = (pay-(buy+5000*a+1000*b+500*c+100*d+50*e))/10;
		System.out.println("총 거스름돈 : "+gross+"\n5000원 : "+a+"\n1000원 : "+b+"\n500원 : "+c+"\n100원 : "+d+"\n50원 : "+e+"\n10원 : "+f);
	
		
		/*2번째 방법
		int change = pay-buy;
		System.out.pritnln("거스름돈 = %d \n",change);
		int temp - change/5000;
		System.out.pritnln("5000원 = %d \n",temp);
		change = change %5000;
		temp = change/1000;
		System.out.pritnln("1000원 = %d \n",temp);
		change = change%1000;
		temp = change/500;
		System.out.pritnln("500원 = %d \n",temp);
		change = change%500;
		temp = change/100;
		System.out.pritnln("100원 = %d \n",change/100);
		change = change%100;
		System.out.pritnln("100원 = %d \n",change/50);
		change = change%50;
		System.out.pritnln("100원 = %d \n",change/10);
		*/
	}

}
