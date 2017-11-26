package com.srinithread.basic;


class Processor {
	
public void producer() throws InterruptedException{
		
		//class object level 
		synchronized (this) {
			System.out.println("we are in producer method");
			wait();
			System.out.println("finish produce method");
		}
	}
	
	public void consumer() throws InterruptedException{
		
		synchronized (this) {
			
			System.out.println("we are in consumer method");
			notify(); //notifyAll() to let all other threads which are having class object level lock
			Thread.sleep(2000); //this will execute before other thread methods which acquired lock
		}
	}
}
public class WaitNotifyApp {

	public static void main(String[] args) {
	
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.producer();
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
					processor.consumer();
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
