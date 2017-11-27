package com.srinithread.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author srini
 * 
 * Condition class has equivalent wait and notify methods when we using Lock instead of synchronized
 *
 */
class Worker{
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void producer() throws InterruptedException{
		
		lock.lock();
		System.out.println("producer method");
		condition.await();
		System.out.println("producer method again");
		lock.unlock();		
	}
	
	public void consumer() throws InterruptedException{
		Thread.sleep(2000);
		lock.lock();
		System.out.println("consumer method");
		condition.signal();
		lock.unlock();
	}
}

public class ProducerAndConsumerWithLocksApp {

	public static void main(String[] args) {

		Worker worker = new Worker();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				try {
					worker.producer();
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
					worker.consumer();;
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
