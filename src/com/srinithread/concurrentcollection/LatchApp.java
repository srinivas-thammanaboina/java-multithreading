package com.srinithread.concurrentcollection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
 * @author srini
 * 
 *  - This is used to synchronize one or more tasks by forcing them wait for set of operations being performed by other tasks
 *	- we will give an intial countDownLatch object with some value and we will call await() on that object 
 *		it will block until the count becomes zero
 *	- other tasks may call countDown() on the object to reduce the count, when tasks finishes its jobs a CountLatch it can't be reset again
 *		if you need a version that resets the count use CyclieBarrier instead
 *	- the tasks that called countDown() method will not be blocked only call to await() is blocked until the count reaches zero
 *	- A typical use is to divide a problem into N independent slovable tasks and create countDownLatch(N) . when each tasks finished it 
 *		calls to countDown() on the latch. Tasks waiting for the problem to be solved call await() on the latch to hold themseleves
 *		back until it is completed
 *	- Example :
 *				Classical example of using CountDownLatch in Java is any server side core Java application which uses services architecture, 
 *				where multiple services are provided by multiple threads and application can not start processing until all services 
 *				have started successfully 
 */
public class LatchApp {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		CountDownLatch countDownLatch = new CountDownLatch(5);
		
		for(int i=0;i<5;++i){
			
			executorService.submit(new LatchExample(i+1, countDownLatch));
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}

}
class LatchExample implements Runnable{
	private int id;
	private CountDownLatch countDownLatch;
	
	public LatchExample(int id, CountDownLatch countDownLatch){
		this.id=id;
		this.countDownLatch=countDownLatch;
	}

	@Override
	public void run() {
		doWork();
		countDownLatch.countDown();
	}

	private void doWork() {
		System.out.println("Thread with id : "+this.id+" starts working");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
