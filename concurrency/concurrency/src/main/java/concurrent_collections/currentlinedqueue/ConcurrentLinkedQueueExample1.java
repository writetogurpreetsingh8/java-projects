package concurrent_collections.currentlinedqueue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("1");queue.add("2");queue.add("3");
		queue.add("4");queue.add("5");queue.add("6");
		
		Iterator<String> itr = queue.iterator();
		while(itr.hasNext()){
			queue.remove("1");
			System.out.println(itr.next());
			//System.out.println(itr.next());
		}
		System.out.println(queue.toString());

	}

}
