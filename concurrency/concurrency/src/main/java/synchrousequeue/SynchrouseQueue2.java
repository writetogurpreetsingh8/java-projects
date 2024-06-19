package synchrousequeue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class SynchrouseQueue2 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		SynchronousQueue s = new SynchronousQueue();
		System.out.println(s);
		System.out.println(s.poll());
		s.offer("1");
		System.out.println(s);
		s.put("1");
		
		
		

	}

}
