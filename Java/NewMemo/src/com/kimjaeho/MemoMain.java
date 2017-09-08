package com.kimjaeho;

public class MemoMain {

	public static void main(String[] args) {
		View view = new View();
		ModelWithDB model = new ModelWithDB();
		Controller control = new Controller(model, view);
		control.process();
	}
}
