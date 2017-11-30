package concurrentcollection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
