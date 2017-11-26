package com.srinithread.basic;


class Thread1 extends Thread {

	@Override
	public void run() {

		for(int i=0; i<=10;++i){
			System.out.println("Runner 1 -> "+i);
			//pausing thread for some time
			try{
				Thread.sleep(100);
			}catch(InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
}

class Thread2 extends Thread {

	@Override
	public void run() {

		for(int i=0; i<=10;++i){
			System.out.println("Runner 2 -> "+i);
			//pausing thread for some time
			try{
				Thread.sleep(100);
			}catch(InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
}


public class ThreadApp {

	public static void main(String[] args) {

		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();

		t1.start();
		t2.start();
		
		System.out.println("threads finished");
	}

}
