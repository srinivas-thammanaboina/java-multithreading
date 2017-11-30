package threadbasics;

public class SynchronizedBlockApp {
	
	private static int counter1 = 0;
	private static int counter2 = 0;
	
	/*public static synchronized void add(){
		for(int i=0; i<100;++i){
			++counter1;
		}
	}
	
	public static synchronized void addAgain(){
		for(int i=0; i<100;++i){
			++counter2;
		}
	}*/

	//Note: above two synchronozed blocks are class level. so in this case t1 & t2 will not access synchronzed methods concurrently.
	
	/**
	 * 
	 * below is the solution to make both threads work concurrently on sychronized methods
	 * 
	 */
	
	private static Object lock1 = new Object();
	
	private static Object lock2 = new Object();
	
	public static synchronized void add(){
		synchronized (lock1) {
			++counter1;
		}
	}
	
	public static synchronized void addAgain(){
		synchronized (lock2) {
			++counter2;
		}
		
	}

	
	public static void compute(){
		for(int i=0; i<100;++i){
			add();
			addAgain();
		}
	}
	
	
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compute();
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compute();
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("counter1 --"+counter1+" counter2 --"+counter2);
	}

}
