package concurrent_collections.currentlinedqueue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable{
	
	ConcurrentLinkedQueue<String> queue;
	
	   Consumer(ConcurrentLinkedQueue<String> queue){
	      this.queue = queue;
	   }
	   
	   public void run() {
	      String str;
	      System.out.println("Consumer Started");
	      for (int x = 0; x < 10; x++) {
	         while ((str = queue.poll()) != null) {
	            System.out.println("Removed: " + str);
	         }
	         try {
	            //Thread.currentThread().sleep(1000);
	         } catch (Exception ex) {
	            ex.printStackTrace();
	         }
	      }
	   }
}
