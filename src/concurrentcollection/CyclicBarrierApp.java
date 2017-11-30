package concurrentcollection;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *  @author srini
 *
 *
 *	- Latch -> multiple threads can wait for each other
 *	- A cyclicBarrier can be used where we want to create group by tasks
 *		to perform work in parallel + wait until they are all finished before moving on the next step
 *	 		-> something like join()
 *			-> something like countDownLatch()
 *		countDownLatch -> one shot event
 *		CyclicBarrier -> it can be reused over and over again
 *		cyclieBarrier has better action: a runnable that will run automatically when the count reaches 0
 *	
 *	- we can't reuse Latch but we can reuse CyclicBarrier() --> reset() 
 */
public class CyclicBarrierApp {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("All the tasks are finished...");
				
			}
		});
		
		for (int i=0; i<5;++i){
			executorService.submit(new CyclicBarrierExample(i+1,  cyclicBarrier));
		}
		
		executorService.shutdown();
	}

}

class CyclicBarrierExample implements Runnable{

	private int id;
	
	private Random random;
	private CyclicBarrier cyclicBarrier ;
	
	 public CyclicBarrierExample(int id,CyclicBarrier cyclicBarrier) {
		this.id = id;
		this.random = new Random();
		this.cyclicBarrier = cyclicBarrier;		
	}
	 
	
	@Override
	public void run() {
		doWork();
	}


	private void doWork() {
		System.out.println("Thread with ID "+this.id+" starts working...");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread with ID "+this.id+" finished");
		
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Override
	public String toString() {
		return ""+this.id;
	}
	
}