package transferqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 1 consumer and 2 producer
class Consumer2 implements Runnable {
	  
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private int numberOfMessagesToConsume = 10;
  
    Consumer2(java.util.concurrent.TransferQueue<String> transferQueue){
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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

class FirstProducer implements Runnable {
	
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private Integer numberOfMessagesToProduce = 10;
  
    FirstProducer(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToProduce; i++) {
            try {
            	System.out.println("FirstProducer, producing value is FirstProducer-"+i);
            	System.out.println("FirstProducer - transferQueue.toString() "+transferQueue.toString());
                transferQueue.transfer("FirstProducer-" + i);
                System.out.println("FirstProducer, produced value is FirstProducer-"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

class SecondProducer implements Runnable {
	
    private java.util.concurrent.TransferQueue<String> transferQueue;
  
    private Integer numberOfMessagesToProduce = 10;
  
    SecondProducer(java.util.concurrent.TransferQueue<String> transferQueue){
    	this.transferQueue = transferQueue;
    }
    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToProduce; i++) {
            try {
            	System.out.println("SecondProducer, producing value is SecondProducer-"+i);
            	System.out.println("SecondProducer - transferQueue.toString() "+transferQueue.toString());
                transferQueue.transfer("SecondProducer-" + i);
                System.out.println("SecondProducer, produced value is SecondProducer-"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // standard constructors
}

public class TransferQueue2 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		java.util.concurrent.TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
		
	    ExecutorService exService = Executors.newFixedThreadPool(4);
	    
	    SecondProducer producer = new SecondProducer(transferQueue);
	    FirstProducer producerSub = new FirstProducer(transferQueue);
	    Consumer2 consumer = new Consumer2(transferQueue);
	 
	    // when
	    exService.execute(producer);
	    exService.execute(producerSub);
	  //  exService.execute(consumer);
	 
	    // then
	    exService.awaitTermination(10000, TimeUnit.MILLISECONDS);
	    exService.shutdown();
	    System.out.println("after main thread end up "+transferQueue.toString());
	    System.out.println(" transferQueue.take() "+transferQueue.take());
	    System.out.println("after main thread end up "+transferQueue.toString());

	    Thread.sleep(2000);
	    
	    System.out.println("after main thread end up 1 "+transferQueue.toString());
	    Thread.sleep(2000);
	    System.out.println(" transferQueue.take() "+transferQueue.take());
	    System.out.println("after main thread end up "+transferQueue.toString());
	    
	}

}
