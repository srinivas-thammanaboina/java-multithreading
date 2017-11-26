package com.srinithread.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * @author srini
 *
 *	Reentrant lock
 *		- it has the same approach as the "sunchronozed approach"
 *		- it has some additional features
 *				new ReentrantLock (boolean fairnessParameter){
 *							~ fairnessParameter : if it is set to true - > the longest waiting thread will get the lock
 *												   if it is false -> there is no access order
 *
 *	IMPORTANT: 
 *  	we have to use try catch block when doing critical section that may throw exception
 *  	We can call unlock() in the finally block !!!								
 */
public class ReentrantLockApp {

	private static int counter = 0;
	private static Lock lock = new ReentrantLock();
	
	public static void increment(){
		
		lock.lock();
		//try -catch -finally to handle exceptions otherwise unlock() will never get called if any exceptions occur
		try{
			for (int i=0;i<1000;i++)
				++counter;
		}finally{
			lock.unlock();
		}
	}
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				increment();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				increment();
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
		
		System.out.println("counter value is "+counter);
	}

}
