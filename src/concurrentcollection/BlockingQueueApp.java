package concurrentcollection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author srini
 *
 *	BlockingQueue -> an interface that represents a queue that is thread safe
 *			put items or take items
 *
 *	One thread is put items and another thread is take items from the queue at the same time
 *		we can do it with producer and consumer methods with low level synchronization
 *	
 *	
 *
 */
public class BlockingQueueApp {

	public static void main(String[] args) {

		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
		
		FirstWorker firstWorker = new FirstWorker(blockingQueue);
		SecondWorker secondWorker = new SecondWorker(blockingQueue);
		
		new Thread(firstWorker).start();
		new Thread(secondWorker).start();
	
	}
}

class FirstWorker implements Runnable{

	private BlockingQueue<Integer> blockingQueue;
	
	public FirstWorker(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	@Override
	public void run() {

		int count = 0;
		while(true){
			try {
				blockingQueue.put(count++);
				System.out.println("putting items "+count);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class SecondWorker implements Runnable{

	private BlockingQueue<Integer> blockingQueue;
	
	public SecondWorker(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	@Override
	public void run() {

		while(true){
			try {
				int number = blockingQueue.take();
				System.out.println("taking items "+number);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
}