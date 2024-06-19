package blockingqueue.arrayblockingqueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayblockingQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		List<String> list = new ArrayList();
		list.add("1");list.add("2");
		
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(3,true,list);
		
		System.out.println("total remainingCapacity is "+q.remainingCapacity());
		
		//System.out.println("q.add(1) "+q.add("1"));

		System.out.println(q.offer("1"));
		System.out.println(q.offer("1"));
		System.out.println(q.offer("1"));

		
		
		/*System.out.println("q.add(2) "+q.add("2"));
		System.out.println("q.add(3) "+q.add("3"));
		System.out.println("total capcacity is "+q.remainingCapacity());
		System.out.println("q.add(4) "+q.offer("4"));
		//System.out.println("q.element() "+q.remove());
		//System.out.println(q.poll(10L, TimeUnit.HOURS));
		
		for(String x:q){
			System.out.println(x);
		}
		Iterator<String> itr = q.iterator();
		while(itr.hasNext()){
			System.out.println("itr "+itr.next());
		}
		System.out.println("toString() "+q.toString());
		System.out.println(q.toArray());
		*/
	}

}
