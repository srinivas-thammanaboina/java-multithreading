package concurrentcollection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * @author srini
 * 
 * ConcurrentHashMap is undoubtedly most popular collection class introduced in Java 5 and most of us are already using it. 
 * ConcurrentHashMap provides a concurrent alternative of Hashtable or Synchronized Map classes with aim to support
 *  higher level of concurrency by implementing fined grained locking. Multiple reader can access the Map concurrently  
 *  while a portion of Map gets locked for write operation depends upon concurrency level of Map. 
 *  ConcurrentHashMap provides better scalability than there synchronized counter part.
 *   Iterator of ConcurrentHashMap are fail-safe iterators which doesn't throw ConcurrencModificationException thus eliminates 
 *   another requirement of locking during iteration which result in further scalability and performance

 *
 */
public class ConcurrentMapsApp {

	public static void main(String[] args) {

		ConcurrentMap< String, Integer> map = new ConcurrentHashMap<>();
		new Thread(new FirstMapWorker(map)).start();
		new Thread(new SecondMapWorker(map)).start();
	}
}

class FirstMapWorker implements Runnable{

	private ConcurrentMap<String, Integer> map;
	
	public FirstMapWorker(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}
	
	@Override
	public void run() {

		try{
			map.put("sravy", 21);
			map.put("meena", 21);
			Thread.sleep(1000);
			map.put("divya", 25);
			map.put("subba", 31);
			Thread.sleep(1000);
			map.put("goutham", 30);
			map.put("parthu", 2);
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
	class SecondMapWorker implements Runnable{

		private ConcurrentMap<String, Integer> map;
		
		public SecondMapWorker(ConcurrentMap<String, Integer> map) {
			this.map = map;
		}
		
		@Override
		public void run() {

			try{
				Thread.sleep(5000);
				System.out.println(map.get("sravy"));
				System.out.println(map.get("meena"));
				Thread.sleep(1000);
				System.out.println(map.get("subba"));
				System.out.println(map.get("parthu"));
				System.out.println(map.get("divya"));
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	
}
