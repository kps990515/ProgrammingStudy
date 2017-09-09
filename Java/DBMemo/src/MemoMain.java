

public class MemoMain {

	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller control = new Controller(model, view);
		control.process();
	}
}
