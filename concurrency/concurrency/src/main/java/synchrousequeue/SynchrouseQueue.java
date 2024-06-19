package synchrousequeue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


class ConsumerSQ implements Runnable {
	  
    private SynchronousQueue transferQueue;
  
    private int numberOfMessagesToConsume = 10;
  
    ConsumerSQ(SynchronousQueue transferQueue){
    	this.transferQueue = transferQueue;
    }
 
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
            	System.out.println("sleeping consumer............");
            	//Thread.sleep(1000);
                transferQueue.add("A-"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

public class SynchrouseQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		SynchronousQueue s = new SynchronousQueue();
		
		System.out.println(s.poll());
		
		System.out.println(s);
		System.out.println(s.getClass());
		
		
		
		ConsumerSQ consumer = new ConsumerSQ(s);
	    new Thread(consumer).start();
	 Thread.sleep(3000);
	    // when
	    System.out.println("over main therad......");
		System.out.println( s);

	}

}
