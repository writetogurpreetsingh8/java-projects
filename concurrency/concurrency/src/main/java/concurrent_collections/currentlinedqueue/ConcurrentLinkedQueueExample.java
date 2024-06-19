package concurrent_collections.currentlinedqueue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample {
	
	   public static void main(String[] args) {
		   
	      ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	      Thread producer = new Thread(new Producer(queue));
	      Thread consumer = new Thread(new Consumer(queue));
	      producer.setPriority(10);
	      consumer.setPriority(1);
	      producer.start();
	      consumer.start();
	  }
	   
}
