package com.srinithread.basic;

/**
 * the volatile keyword in Java is used as an indicator to Java compiler and Thread that do not cache value of this variable
 *  and always read it from main memory. So if you want to share any variable in which read and 
 *  write operation is atomic by implementation e.g. read and write in an int or a boolean variable 
 *  then  you can declare them as volatile variable.
  *
 */

class VolatileThead extends Thread {
	
	private volatile boolean isTerminated = false;
	
	@Override
	public void run(){
	
		while(!isTerminated){
			System.out.println("hello from volatile thead");
		}
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setTerminated(boolean isTerminated){
		this.isTerminated = isTerminated;
	}
	
	public boolean getTerminated(){
		return isTerminated;
	}
}
public class VolatileApp {

	public static void main(String[] args) {

		VolatileThead vt = new VolatileThead();
		
		Thread t1 = new Thread(vt);
		t1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vt.setTerminated(true);
		
		System.out.println("Finished");
	}

}
