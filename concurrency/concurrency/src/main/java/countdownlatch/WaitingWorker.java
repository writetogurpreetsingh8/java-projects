package countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

//we can block each child thread until all the others have started.

public class WaitingWorker implements Runnable {

	private List<String> outputScraper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;
 
    public WaitingWorker(
      List<String> outputScraper,
      CountDownLatch readyThreadCounter,
      CountDownLatch callingThreadBlocker,
      CountDownLatch completedThreadCounter) {
 
        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }
 
    @Override
    public void run() {
    	System.out.println(Thread.currentThread().getName()+" entered in run().......");
        readyThreadCounter.countDown();
        System.out.println(Thread.currentThread().getName()+" decreased count down.......");
        
        try {
        	//TimeUnit.SECONDS.sleep(100000000);
            callingThreadBlocker.await();
        	System.out.println("perform child thread business - logic....... by "+Thread.currentThread().getName());
           System.out.println(Thread.currentThread().getName()+" calling......");
            outputScraper.add(Thread.currentThread().getName()+" Counted down");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }
    }

}
