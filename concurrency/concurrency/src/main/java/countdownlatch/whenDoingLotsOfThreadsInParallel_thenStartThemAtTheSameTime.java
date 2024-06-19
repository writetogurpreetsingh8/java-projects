package countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		List<String> outputScraper = Collections.synchronizedList(new ArrayList());
		
	    CountDownLatch readyThreadCounter = new CountDownLatch(5);
	    CountDownLatch callingThreadBlocker = new CountDownLatch(1);
	    CountDownLatch completedThreadCounter = new CountDownLatch(5);
	    
	    for(int i=0; i<5; i++){
	    	Thread t = new Thread(new WaitingWorker(outputScraper, readyThreadCounter, 
	    			callingThreadBlocker, completedThreadCounter));
	    	
	    	t.setName("Thread - "+(i+1));
	    	t.start();
	    }
	    
	    readyThreadCounter.await(); 
	    System.out.println("workers are ready......");
	    outputScraper.add("Workers ready");
	    callingThreadBlocker.countDown(); 
	    completedThreadCounter.await(); 
	    System.out.println("workers complete.....");
	    outputScraper.add("Workers complete");
	    System.out.println(outputScraper);
	    System.out.println("main finished..........");
	}

}
