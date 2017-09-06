
public class HelloJava {
	/**
	 * 
	 * @param args
	 */
		public static void main(String[] args) {
			System.out.println("Hello Java");
			// long형은 뒤에 L을 붙인다
			long z = 99999999999999L;
			// 정수형의 연산은 기본 int로 계산된다
			byte a = 10;
			byte b = 11;
			int c = a+b;
			// byte로 하고 싶으면 강제형변환 한다
			byte l = (byte)(a+b);
			
			// 실수형의 연산은 double형으로 계산된다
			// float형은 뒤에 f를 붙인다
			
			float d = 3.14f;
			float e = 4.1928f;
			double u = d+e;
			// 실수 연산은 직접하지 않는다.
			float f = Float.sum(d, e);
			
			double g = 5.232;
			double h = 6.323131;
			double i = Double.sum(g, h);
			
			int number = changeStringToInteger("123");
			int result = number+123123;
			test();
			
		}
		/**
		 * 
		 * @param number
		 * @return
		 */
		public String changeNumberToString(int number)
		{
			//숫자를 스트링으로 바꿀때는 Integer.tostring을 써도 되지만 아래방법을 주로 사용한다
			return number+"";
		}
		/**
		 * 
		 * @param word
		 * @return
		 */
		public static int changeStringToInteger(String word)
		{
			//문자열을 숫자로 변환
			return Integer.parseInt(word);
		}
		public static void test()
		{
			int a=2+3;
			int b=2-3;
			int c=2*3;
			int d=2/3;
			int e=5%3;
			int f=10%3;
			double dou=5.0%4.2;
			System.out.println(" 2+3 : "+a+"\n 2-3 : "+b+"\n 2*3 : "+c+"\n 2/3 : "+d+"\n 5%3 : "+e+"\n 10%3 : "+f+"\n 5.0%4.2 : "+dou);
		}
}
