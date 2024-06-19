package transferqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

// 1 consumer and 1 producer initially created with collection 
class Consumer3 implements Runnable {
	  
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private int numberOfMessagesToConsume = 10;
  
    Consumer3(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
 
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToConsume; i++) {
            try {
            	System.out.println("sleeping consumer............");
            	System.out.println("from consumer - transferQueue is "+transferQueue.toString());
            	//Thread.sleep(20000);
                String element = transferQueue.take();
                System.out.println("received value is "+element);
               // Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

class Producer3 implements Runnable {
	
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private Integer numberOfMessagesToProduce = 10;
  
    Producer3(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToProduce; i++) {
            try {
            	System.out.println("trying to produce value is A-"+i);
            	Thread.sleep(2000);
                transferQueue.put("A-" + i);
                System.out.println("from producer - transferQueue is "+transferQueue.toString());
                System.out.println("produced value is A-"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

public class TransferQueue3 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		List<String> list = new ArrayList<String>();
		
		list.add("add by list-1");list.add("add by list-2");list.add("add by list-3");
		list.add("add by list-4");list.add("add by list-5");list.add("add by list-6");
		
		java.util.concurrent.TransferQueue<String> transferQueue = new LinkedTransferQueue<String>(list);
		
		
		System.out.println("transferQueue is "+transferQueue.toString());
		System.out.println("transferQueue size is "+transferQueue.size());
	    ExecutorService exService = Executors.newFixedThreadPool(2);
	    
	    Producer3 producer = new Producer3(transferQueue);
	    Consumer3 consumer = new Consumer3(transferQueue);
	 
	    // when
	    exService.execute(producer);
	    exService.execute(consumer);
	 
	    // then
	    exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
	    System.out.println("transferQueue is "+transferQueue.toString());
		System.out.println("transferQueue size is "+transferQueue.size());
	    exService.shutdown();

	}

}
