package transferqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 1 consumer and 1 producer
class Consumer1 implements Runnable {
	  
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private int numberOfMessagesToConsume = 10;
  
    Consumer1(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
 
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToConsume; i++) {
            try {
            	System.out.println("sleeping consumer............");
            	Thread.sleep(10000);
                String element = transferQueue.take();
                System.out.println("received value is "+element);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

class Producer1 implements Runnable {
	
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private Integer numberOfMessagesToProduce = 10;
  
    Producer1(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToProduce; i++) {
            try {
            	System.out.println("producing value is A-"+i);
                transferQueue.put("A-" + i);
                System.out.println("produced value is A-"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

public class TransferQueue1 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		java.util.concurrent.TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
		
	    ExecutorService exService = Executors.newFixedThreadPool(2);
	    Producer1 producer = new Producer1(transferQueue);
	    Consumer1 consumer = new Consumer1(transferQueue);
	 
	    // when
	    exService.execute(producer);
	    exService.execute(consumer);
	 
	    // then
	    exService.awaitTermination(1000, TimeUnit.MILLISECONDS);
	    exService.shutdown();

	}

}
