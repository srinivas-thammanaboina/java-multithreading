package com.srinithread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
 * @author srini
 *
 *	ExecutorService executoreService = Executors.newCachedThreadPool();
 *		- going to return an executorService that can dynamically reuse therads
 *	    - before startig a job -> it going to check whether there are any threads that 	
 *			finished the job. reuse them
 *		- if there are no waiting threads - > it is going to create another one
 *		- good for processor 
 *  ExecutorService executoreService = Executors.newCachedThreadPool(N);
 *  	- maximize the number of threads 
 *  	- if we want to start a job if all threads are busy then it has to wait for one to terminate
 *  ExecutorService executoreService = Executors.newSingleTheadExecutor();
 *  	- it uses a single thread for job
 *   	
 *   
 *   execute() -> runnable + callable
 *   submit() -> runnable
 */

class ExecutorDriver implements Runnable{

	@Override
	public void run() {

		for(int i=0; i<10;++i){
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
public class ExecutorsApp {

	public static void main(String[] args) {

		ExecutorService executorService =  Executors.newFixedThreadPool(8);
		
		for(int i=0; i<8;++i){
			executorService.submit(new ExecutorDriver());
		}
		
		ExecutorService executorService1 =  Executors.newCachedThreadPool();
		
		for(int i=0; i<8;++i){
			executorService1.submit(new ExecutorDriver());
		}
	}

}
