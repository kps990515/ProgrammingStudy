package com.kimjaeho;

public class MemoMain {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		
		Controller control = new Controller(model, view);
		control.process();
	}
}
