package com.srinithread.basic;


class Thread1 extends Thread {

	@Override
	public void run() {

		for(int i=0; i<=10;++i){
			System.out.println("Runner 1 -> "+i);
			}
	}
}

class Thread2 extends Thread {

	@Override
	public void run() {

		for(int i=0; i<=10;++i){
			System.out.println("Runner 2 -> "+i);
			}
	}
}


public class ThreadApp {

	public static void main(String[] args) {

		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();

		t1.start();
		t2.start();
		
		//this statement will get executed before both threads finishes
		System.out.println("threads finished");
	}

}
