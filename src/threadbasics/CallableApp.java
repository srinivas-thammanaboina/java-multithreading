package threadbasics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ProcessorDriver implements Callable<String>{

	private int id;
	
	public ProcessorDriver(int id){
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return "id - "+id;
	}
	
}
public class CallableApp {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();
		
		for(int i=0;i<5;++i){
			Future<String> future = executorService.submit(new ProcessorDriver(i+1));
			list.add(future);
		}
		executorService.shutdown();
		for(Future<String> future: list){
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
