package com.srinithread.concurrentcollection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author srini
 *
 *	This is an unbounded BlockingQueue of objects that implements the Delayed interface
 *
 *	- DelayQueue keeps the elements internally until a certain delay has expired
 *	- an object can only be taken from the queue when its delay has expired
 *	- we can't place null items in the queue 
 *	- the queue is sorted so that the objectat the head has a delay that has expired for the longer time
 *	- if no delay has expired then there is no head element and poll() will return null
 *  - size() will returns both expired and unexpired items
 *  
 */
public class DelayQueueApp {

	public static void main(String[] args) {
		
		BlockingQueue<Delayed> delayedQueue = new DelayQueue<>();
		
		try {
			delayedQueue.put(new DelayQueueWorker(1000, "This is the first message"));
			delayedQueue.put(new DelayQueueWorker(10000, "This is the second message"));
			delayedQueue.put(new DelayQueueWorker(4000, "This is the third message"));
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		while(!delayedQueue.isEmpty()){
			
			try {
				System.out.println(delayedQueue.take());
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
	}

}

class DelayQueueWorker implements Delayed{

	private long duration;
	private String message;
	
	public DelayQueueWorker(long duration,String message) {
		this.duration = System.currentTimeMillis()+duration;
		this.message = message;
	}
	@Override
	public int compareTo(Delayed otherDelayed) {

		if(this.duration < ((DelayQueueWorker)otherDelayed).getDuration()){
			return -1;
		}
		if(this.duration > ((DelayQueueWorker)otherDelayed).getDuration()){
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	@Override
	public String toString() {
		return this.message;
	}
}
