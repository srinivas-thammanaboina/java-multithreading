package concurrentcollection;

import java.util.concurrent.Exchanger;

/**
 * 
 * @author srini
 *
 *	two threads can exchange objects with the help of exchanger
 *	exchange() -> exchanging operation done via one of two exchange() methods
 *	
 */
public class ExchangerApp {

	public static void main(String[] args) {

		Exchanger<Integer> exchanger = new Exchanger<>();
		
		new Thread(new FirstExchanger(exchanger)).start();
		
		new Thread(new SecondExchanger(exchanger)).start();
	}
}

class FirstExchanger implements Runnable{
	
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public FirstExchanger(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while(true){
			System.out.println("before increment "+counter);
			counter = counter+1;
			System.out.println("counter value from first thread "+counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

class SecondExchanger implements Runnable{
	
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public SecondExchanger(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while(true){
			System.out.println("before decrementing "+counter);
			counter = counter-1;
			System.out.println("counter value from second thread "+counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}