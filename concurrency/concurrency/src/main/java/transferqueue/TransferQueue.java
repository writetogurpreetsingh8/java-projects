package transferqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


// no consumer.........
class Producer implements Runnable {
	
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private String name;
  
    private Integer numberOfMessagesToProduce = 10;
  
    public AtomicInteger numberOfProducedMessages
      = new AtomicInteger();
 
    Producer(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToProduce; i++) {
            try {
            	System.out.println("trying to transfer message to consumer...........");
                transferQueue.put("A-" + i); // waiting
            	//transferQueue.transfer("A-" + i);
            	
                System.out.println("total size is in side loop "+transferQueue.size());
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(transferQueue.toString());
        System.out.println("total size is "+transferQueue.size());
    }
    // standard constructors
}

public class TransferQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		java.util.concurrent.TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
	    ExecutorService exService = Executors.newFixedThreadPool(1);
	    Producer producer = new Producer(transferQueue);
	 
	    // when
	    exService.execute(producer);
	 
	    // then
	    exService.shutdown();
	    exService.awaitTermination(2000, TimeUnit.MILLISECONDS);
	    System.out.println(transferQueue.toString());
	    System.out.println("system going to shutdown...........");

	}

}
