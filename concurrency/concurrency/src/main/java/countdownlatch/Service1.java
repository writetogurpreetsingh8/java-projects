package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Service1 implements Runnable{
	
	
	private final String name;
    private final int timeToStart;
    private final CountDownLatch latch;
  
    public Service1(String name, int timeToStart, CountDownLatch latch){
        this.name = name;
        this.timeToStart = timeToStart;
        this.latch = latch;
    }
  
    @Override
    public void run() {
        try {
        	System.out.println(Thread.currentThread().getName()+" is couting down ");
        	System.out.println(Thread.currentThread().getName()+" is going to sleep..");
            Thread.sleep(timeToStart);
            latch.countDown(); //reduce count of CountDownLatch by 1
            System.out.println(Thread.currentThread().getName()+" is awaking");
        } catch (InterruptedException ex) {
        	System.out.println(Thread.currentThread().getName()+" interrupted Exception........");
            ex.printStackTrace();
           // latch.countDown();
        }
       try{	
        	//latch.countDown(); //reduce count of CountDownLatch by 1
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println( name + " is Up");
    }
}
