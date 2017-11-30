package threadbasics;

import java.util.ArrayList;
import java.util.List;


class ProducerAndConsumer {
	
	private List<Integer> list = new ArrayList<>();
	
	private final int LIMIT = 5;
	
	private final int BOTTOM = 0;
	
	private final Object lock = new Object();
	
	private int value = 0;
	
	public void producer() throws InterruptedException{
		
		synchronized (lock) {
			
			while(true){
			
				if(list.size() == LIMIT){
					System.out.println("waiting for removing items");
					lock.wait();
				}else{
					System.out.println("adding item "+value);
					list.add(value);
					++value;
					lock.notify(); // this will notify only after list reaches threashold
				}
			Thread.sleep(300);
		   }
		}
	}
	
	public void consumer() throws InterruptedException{
		
		synchronized (lock) {
			
			while (true){
				
				if(list.size() == BOTTOM){
					System.out.println("waiting for adding items");
					lock.wait();
				}else{
					System.out.println("removing item "+list.remove(--value));
					lock.notify(); // this will notify only after removing all items
				}
			Thread.sleep(300);
		   }
		}
	}

}
public class ProducerAndConsumerApp {

	public static void main(String[] args) {

		ProducerAndConsumer pc = new ProducerAndConsumer();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					pc.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					pc.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
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
	}

}
