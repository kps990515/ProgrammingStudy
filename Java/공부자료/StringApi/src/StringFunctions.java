
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
		
		// returnŸ���� int�� String ���� ���ϴ� �Լ�
		System.out.println(a.length()); 
		
		// ��ġ�˻�
		System.out.println(a.indexOf("t"));
		
		// Ư�� �����ڷ� ����(���� �迭��)
		String temp[] = a.split(" ");
		for(String item : temp)
		{
			System.out.println(item);
		}
		
		// ���ڿ� �ڸ��� (���⼭ index�� ���ڻ��̿� Ȯ���ȴ�)
		// ex) [0]S[1]t[2]r[3]i[4]n[5]g[6]
		System.out.println(a.substring(2,5));
		
		//���ڿ��� �ڸ��� ���� �ϳ� ������ �ɰ�����
		String temp2[] = a.split("");
		for(String item : temp2)
		{
			System.out.println(item);
		}
		
		// ���ڿ� �ٲٱ�
		System.out.println(a.replace("st", "xt"));
		
		// Ư�� ���ڿ��� ���۵Ǵ����� �˻�
		boolean tf=a.startsWith("Str");
		System.out.println(tf);
		// �ּҰ˻��� �� ����!
		/* 
		if(!address.statrsWith("http:"))
		{
			address = "http://" + address;
		}
		*/
	}
		public void builderVsBuffer(){
			//jdk1.5���� �̻���ʹ� �Ϲ��� ��Ʈ�� ������ StringBuilder��
			//�����Ϸ��� �ڵ���ȯ
			String result = "��" + "����" + "����" + "! �ݰ����ϴ�";
			
			//�ӵ��� ����
			//�񵿱⿡�� ������ ����
			StringBuffer buffer = new StringBuffer();
			buffer.append("��");
			buffer.append("����");
			buffer.append("����");
			buffer.append("! �ݰ����ϴ�");
			System.out.println(buffer.toString());
			
			// �ӵ� ���� ����
			// ������ �񵿱⿡�� ������ ������� �ʴ´�
			// ���� ������ ȯ�濡���� ������� �ʴ´�
			StringBuilder builder = new StringBuilder();
			builder.append("��");
			builder.append("����");
			builder.append("����");
			builder.append("! �ݰ����ϴ�");
			
			System.out.println(buffer.toString());
		}
		
		public void stringConstantPool()
		{
			String a = "�ȳ��ϼ���";
			String b = "�ȳ��ϼ���";
			
			System.out.println(a==b);  //�ּ�üũ
			System.out.println(a.equals(b)); //�� üũ
			
			String c = new String("�ȳ��ϼ���");
			
			String d = c.intern(); // new�� ������� ��ü�� constant pool�� �̵���Ų��.
			
			System.out.println(a==d);  //�ּ�üũ(����)
			System.out.println(a.equals(d)); //�� üũ
			
		}		
	}

