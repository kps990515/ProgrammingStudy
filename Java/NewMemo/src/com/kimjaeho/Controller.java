package com.kimjaeho;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	Model model;
	View view;
	
	//Controller
	public Controller(Model model, View view)
	{
		this.model = model;
		this.view = view;
	}
	public void process()
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
			view.println("-----------��ɾ �Է��ϼ���------------------");
			view.println("c : ����, r : �б�, u : ����, d : ����, l : ���");
			view.println("exit : ����");
			view.println("------------------------------------------");
			//Ű���� �Է��߿� EnterŰ�� �Էµɶ����� ���
			command = view.scannerS(sc);
			//��ɾ �б�ó��
			switch(command)
			{
			case "c":
				Memo memo = view.create(sc);
				//�޸� �����Ϳ� ���� ������ �ʿ��� ��� ��� ��Ʈ�ѷ����� �۾��Ѵ�
				model.create(memo);
				break;
			case "r":
				int num = view.read(sc);
				memo = model.read(num);
				view.showRead(memo);
				break;
			case "u":
				view.update(main.model.list, sc);
				break;
			case "d":
				int num = main.view.remove(main.model.list, sc);
				main.model.remove(num);
				break;
			case "l":
				ArrayList<Memo>list = model.getList();
				view.showList(list);
				break;
			}
		}
		main.view.println("�ý��� ����");
	}
}
