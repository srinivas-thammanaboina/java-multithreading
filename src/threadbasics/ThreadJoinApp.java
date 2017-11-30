package threadbasics;

class ThreadJoin1 extends Thread {

	@Override
	public void run() {

		for(int i=0; i<=10;++i){
			System.out.println("Runner 1 -> "+i);
			//pausing thread for some time
			try{
				Thread.sleep(100);
			}catch(InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
}

class ThreadJoin2 extends Thread {

	@Override
	public void run() {

		for(int i=0; i<=10;++i){
			System.out.println("Runner 2 -> "+i);
			//pausing thread for some time
			try{
				Thread.sleep(100);
			}catch(InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
}


public class ThreadJoinApp {

	public static void main(String[] args) {

		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();

		t1.start();
		t2.start();
		
		//waits until t1 thead completes
		try {
			t1.join();
			//waits until t2 thead completes
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("threads finished");
	}

}
