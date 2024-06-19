package transferqueue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		java.util.concurrent.LinkedTransferQueue q = new java.util.concurrent.LinkedTransferQueue();
		
		System.out.println("total capcacity "+q.remainingCapacity());
		System.out.println("q.add(1) "+q.add("1"));
		System.out.println("q.add(2) "+q.add("2"));
		System.out.println("q.add(3) "+q.add("3"));
		System.out.println("total capcacity is "+q.remainingCapacity());
		System.out.println("q.add(4) "+q.offer("4"));
		//System.out.println("q.element() "+q.remove());
		//System.out.println(q.poll(10L, TimeUnit.HOURS));
		for(Object x:q){
			System.out.println(x);
		}
		Iterator<String> itr = q.iterator();
		while(itr.hasNext()){
			System.out.println("itr "+itr.next());
		}
		System.out.println("toString() "+q.toString());
		System.out.println(q.toArray());
		
	}

}
