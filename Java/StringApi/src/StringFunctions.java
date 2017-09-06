
public class StringFunctions {
	
	public static void main(String[] args)
	{
		StringFunctions main = new StringFunctions();
		main.text();
		main.builderVsBuffer();
		main.stringConstantPool();
	}
	public void text() {
		
		String a = "String Test";
		
		// return타입이 int인 String 길이 구하는 함수
		System.out.println(a.length()); 
		
		// 위치검색
		System.out.println(a.indexOf("t"));
		
		// 특정 구분자로 분해(문자 배열로)
		String temp[] = a.split(" ");
		for(String item : temp)
		{
			System.out.println(item);
		}
		
		// 문자열 자르기 (여기서 index는 문자사이에 확립된다)
		// ex) [0]S[1]t[2]r[3]i[4]n[5]g[6]
		System.out.println(a.substring(2,5));
		
		//빈문자열로 자르면 글자 하나 단위로 쪼개진다
		String temp2[] = a.split("");
		for(String item : temp2)
		{
			System.out.println(item);
		}
		
		// 문자열 바꾸기
		System.out.println(a.replace("st", "xt"));
		
		// 특정 문자열로 시작되는지를 검사
		boolean tf=a.startsWith("Str");
		System.out.println(tf);
		// 주소검색할 떄 유용!
		/* 
		if(!address.statrsWith("http:"))
		{
			address = "http://" + address;
		}
		*/
	}
		public void builderVsBuffer(){
			//jdk1.5버전 이상부터는 일반적 스트링 연산을 StringBuilder로
			//컴파일러가 자동변환
			String result = "안" + "녕하" + "세요" + "! 반갑습니다";
			
			//속도가 빠름
			//비동기에서 안전성 보장
			StringBuffer buffer = new StringBuffer();
			buffer.append("안");
			buffer.append("녕하");
			buffer.append("세요");
			buffer.append("! 반갑습니다");
			System.out.println(buffer.toString());
			
			// 속도 가장 빠름
			// 하지만 비동기에서 안전성 보장되지 않는다
			// 다중 스레드 환경에서는 사용하지 않는다
			StringBuilder builder = new StringBuilder();
			builder.append("안");
			builder.append("녕하");
			builder.append("세요");
			builder.append("! 반갑습니다");
			
			System.out.println(buffer.toString());
		}
		
		public void stringConstantPool()
		{
			String a = "안녕하세요";
			String b = "안녕하세요";
			
			System.out.println(a==b);  //주소체크
			System.out.println(a.equals(b)); //값 체크
			
			String c = new String("안녕하세요");
			
			String d = c.intern(); // new로 만들어진 객체를 constant pool로 이동시킨다.
			
			System.out.println(a==d);  //주소체크(같다)
			System.out.println(a.equals(d)); //값 체크
			
		}		
	}

