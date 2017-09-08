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
		
		// 명령어를 입력받아서 후속처리
		// c - create : 데이터 입력모드로 전환
		// r - read   : 데이터 읽기모드로 전환
		// u - update : 데이터 수정모드로 전환
		// d - delete : 데이터 삭제모드로 전환
		String command = "";
		while(!command.equals("exit"))
		{
			view.println("-----------명령어를 입력하세요------------------");
			view.println("c : 쓰기, r : 읽기, u : 수정, d : 삭제, l : 목록");
			view.println("exit : 종료");
			view.println("------------------------------------------");
			//키보드 입력중에 Enter키가 입력될때까지 대기
			command = view.scannerS(sc);
			//명령어를 분기처리
			switch(command)
			{
			case "c":
				Memo memo = view.create(sc);
				//메모 데이터에 대한 조작이 필요할 경우 모두 컨트롤러에서 작업한다
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
		main.view.println("시스템 종료");
	}
}
