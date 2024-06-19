package blockingqueue.linkedblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class LinkedblockingQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<String> q = new LinkedBlockingQueue<String>(3);
		
		System.out.println("total capcacity is "+q.remainingCapacity());
		
		q.add("1");
		q.add("2");
		q.add("3");
		q.add("4");
		q.add("5");
		q.add("5");
		//System.out.println(q.poll(10L, TimeUnit.HOURS));
		System.out.println("total capcacity is "+q.remainingCapacity());
		
		for(String x:q){
			System.out.println(x);
		}
		
		/*SynchronousQueue<String> s = new SynchronousQueue<>();
		System.out.println("s queu si "+s);
		s.add("1");
		System.out.println("s queu si "+s);
*/
		
	}

}
