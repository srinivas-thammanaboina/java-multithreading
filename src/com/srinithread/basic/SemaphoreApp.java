package com.srinithread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * 
 * @author srini
 * 
 *  - semaphore maintains a set of permits
 *  - acquire()  -> if the permits is available then take it
 *  - release()  -> add a permit
 *  
 *  semaphore just keeps a count of the number available
 *  
 *  new Semaphore(int permits, boolean fair)
 *
 */

enum Downloader {
	INSTANTCE;
	
	private Semaphore semaphore = new Semaphore(3,true);
	
	public void downloadData(){
		
		try{
			semaphore.acquire();
			download();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}

	private void download() {

		System.out.println("Downlading from web");
		
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
public class SemaphoreApp {

	public static void main(String[] args) {


		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i=0;i<12;++i){
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					Downloader.INSTANTCE.downloadData();	
				}
			});
		}
	}

}
