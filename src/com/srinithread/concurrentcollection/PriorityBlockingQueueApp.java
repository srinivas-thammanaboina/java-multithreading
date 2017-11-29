package com.srinithread.concurrentcollection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 
 * @author srini
 *
 *	PriorityBlockingQueue is the implementation of BlockingQueue interface
 *	
 *	unbounded concurrent queue
 *	
 *	it uses the same ordering rules as the java.util.PriorityQueue class -> have to implement comparable interface
 *	the comparable interface will determine what will the order in the queue
 *	priority can be same compare() == 0
 *	
 *	no null items!!!
 */
public class PriorityBlockingQueueApp {

	public static void main(String[] args) {
		BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
		new Thread(new FirstPriorityBlokingQueueWorker(queue)).start();
		new Thread(new SecondPriorityBlokingQueueWorker(queue)).start();
		
	}
}

class FirstPriorityBlokingQueueWorker implements Runnable{

	private BlockingQueue<Person> blockingQueue;
	public FirstPriorityBlokingQueueWorker(BlockingQueue<Person> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	@Override
	public void run() {

		try{
			blockingQueue.put(new Person(21,"Sravy"));
			blockingQueue.put(new Person(21,"Meena"));
			blockingQueue.put(new Person(29,"Divay"));
			Thread.sleep(1000);
			blockingQueue.put(new Person(30,"Subba"));
			blockingQueue.put(new Person(31,"Goutham"));
			Thread.sleep(1000);
			blockingQueue.put(new Person(23,"Rahua"));
			blockingQueue.put(new Person(20,"Raju"));
			Thread.sleep(1000);
			blockingQueue.put(new Person(5,"Parthu"));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
}
class SecondPriorityBlokingQueueWorker implements Runnable{

	private BlockingQueue<Person> blockingQueue;
	public SecondPriorityBlokingQueueWorker(BlockingQueue<Person> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	@Override
	public void run() {

		try{
			Thread.sleep(10000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			Thread.sleep(3000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
}

class Person implements Comparable<Person>{
	
	private int age;
	private String name;
	
	public Person(int age,String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.age+" - "+this.name;
	}

	@Override
	public int compareTo(Person o) {
		if(this.age < ((Person)o).getAge()){
			return -1;
		}
		if(this.age > ((Person)o).getAge()){
			return 1;
		}
		return 0;
	}
}