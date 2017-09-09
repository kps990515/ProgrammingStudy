

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	// �Է¹޴� �Լ�
	public Memo create(Scanner sc) //Scanner�޾ƿ;� ��!!!
	{	
		//�� �ϳ��� �����ϱ� ���� �޸� Ȯ��
		Memo memo = new Memo();
		println("�̸��� �Է��ϼ��� : ");
		memo.name = scannerS(sc);
		println("������ �Է��ϼ��� : ");
		memo.content = scannerS(sc);
		//��¥
		memo.datetime = System.currentTimeMillis();
		
		return memo;
		//model.create(memo)	
	}
	
	public int read(Scanner sc)
	{
		println("�� ��ȣ�� �Է��ϼ���");
		// ------ ���ڰ� �Էµ��� �ʾ��� ���� ���� ó�� --------------- //
		String tempNo = scannerS(sc);
		try
		{
			return Integer.parseInt(tempNo);
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public void showRead(Memo memo)
	{
		println("No. "+memo.no);
		println("Author : "+memo.name);
		println("Content : "+memo.content);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formattedDate = sdf.format(memo.datetime);
		println("Date:"+formattedDate);
	}
	
	public void update(ArrayList <Memo> list,Scanner sc)
	{
		println("������ �޸��� ��ȣ�� �Է��ϼ��� : ");
		String temp = scannerS(sc);
		int num = Integer.parseInt(temp);
		for(Memo memo : list)
		{
			if(memo.no==num)
			{
				println("�̸��� �Է��ϼ��� : ");
				memo.name = scannerS(sc);
				println("������ �Է��ϼ��� : ");
				memo.content = scannerS(sc);
				//��¥
				memo.datetime = System.currentTimeMillis();
				// �� �ϳ��� ������ �޸𸮸� ����ҷ� �̵�		
				break;	
			}
		}
	}
	
	public int remove(ArrayList <Memo> list, Scanner sc)
	{
		int num;
		println("������ �޸��� ��ȣ�� �Է��ϼ��� : ");
		String temp = scannerS(sc);
		num = Integer.parseInt(temp); 
		return num;
	}
	
	public void showList(ArrayList <Memo> list)
	{
		//ArrayList ����Ҹ� �ݺ����� ���鼭 ���پ� ���
		for(Memo memo : list)
		{
			print("No : " + memo.no);
			print(" | Author : " + memo.name);
			println(" | Content : " + memo.content);
		}
	}
	//����Ʈ
	public void print(String string)
	{
		System.out.print(string);
	}
	//�ٹٲ� ����Ʈ
	public void println(String string)
	{
		System.out.println(string);
	}
	
	public int scannerI(Scanner sc)
	{
		int num = sc.nextInt();
		return num;
	}
	
	public String scannerS(Scanner sc)
	{
		String string = sc.nextLine();
		return string;
	}
}
