import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * ��� : System.out.pritn
 * �Է� : Scanner(System.in)
 */
public class MemoMain {
	
	Model model = new Model();
	View view = new View();
	
	//Controller
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		MemoMain main = new MemoMain();
		
		// ��ɾ �Է¹޾Ƽ� �ļ�ó��
		// c - create : ������ �Է¸��� ��ȯ
		// r - read   : ������ �б���� ��ȯ
		// u - update : ������ �������� ��ȯ
		// d - delete : ������ �������� ��ȯ
		String command = "";
		while(!command.equals("exit"))
		{
			main.view.println("-----------��ɾ �Է��ϼ���------------------");
			main.view.println("c : ����, r : �б�, u : ����, d : ����, l : ���");
			main.view.println("exit : ����");
			main.view.println("------------------------------------------");
			//Ű���� �Է��߿� EnterŰ�� �Էµɶ����� ���
			command = main.view.scannerS(sc);
			//��ɾ �б�ó��
			switch(command)
			{
			case "c":
				Memo memo = main.view.create(sc);
				//�޸� �����Ϳ� ���� ������ �ʿ��� ��� ��� ��Ʈ�ѷ����� �۾��Ѵ�
				main.model.create(memo);
				break;
			case "r":
				main.view.read(main.model.list, sc);
				break;
			case "u":
				main.view.update(main.model.list, sc);
				break;
			case "d":
				int num = main.view.remove(main.model.list, sc);
				main.model.remove(num);
				break;
			case "l":
				main.view.showList(main.model.list);
				break;
			}
		}
		main.view.println("�ý��� ����");
	}
}
//�����͸� �����ϴ� ����Ҹ� �����ϴ� ��ü
class Model
{
	//��ü �޸� �����ϴ� �����
	ArrayList<Memo> list = new ArrayList<>();
	//������ �۹�ȣ
	int lastIndex = 1;
	
	public void create(Memo memo)
	{
		//�۹�ȣ
		memo.no = lastIndex++;
		//�� �ϳ��� ������ �޸𸮸� ����ҷ� �̵�
		list.add(memo);
	}
	public void remove(int num)
	{
		int deleteIndex = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).no == num) {
				deleteIndex = i;
				break;
			}
		}
		if(deleteIndex >= 0) list.remove(deleteIndex);
	}
}
//ȭ�� ������� ����
class View
{
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
	
	public void read(ArrayList <Memo> list,Scanner sc)
	{
		println("���� �޸��� ��ȣ�� �Է��ϼ��� : ");
		String temp = scannerS(sc);
		int readnum = Integer.parseInt(temp); //scannerI(sc);
		for(Memo memo : list)
			{
				if(memo.no==readnum)
				{
					print("No : " + memo.no);
					print(" | Author : " + memo.name);
					println(" | Content : " + memo.content);
					
					//��¥�� �Է¹��� ��¥�� ���� ��¥�� ����
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String formattedDate = sdf.format(memo.datetime);
					println("Date : " + formattedDate);	
					break;
				}
				
			}	
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

class Memo
	{	
		int no;
		String name;
		String content;
		long datetime;
	}	
