
public class ThreadMain {
	
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				a.process();
			}
	};
	thread.start();
	
		Thread thread2 = new Thread() {
		@Override
		public void run() {
			b.process();
		}
	};
	thread2.start();
	
	}
}

class A{
	public void process() {
		for(int i=0; i<20; i++) {
			System.out.println("A =============" +i);
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class B{
	public void process() {
		for(int i=0; i<20; i++) {
			System.out.println("B =============" +i);
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

