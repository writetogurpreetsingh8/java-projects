package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ServiceTwo implements Runnable{
	
	private final CyclicBarrier cyclicBarrier;
	
	    public ServiceTwo(CyclicBarrier cyclicBarrier) {
	
	        this.cyclicBarrier = cyclicBarrier;
	
	    }
	
	    @Override
	
	    public void run() {
	
	        System.out.println("Starting service Two....");
	
	         
	
	        try {
	
	            Thread.sleep(5000);
	
	        } catch (InterruptedException e1) {
	        	System.out.println("service two interupted (InterruptedException)......");
	            e1.printStackTrace();
	
	        }
	
	       // System.out.println("Service Two has finished its work.. waiting for others...");
	
	        try {
	
	        	System.out.println("calling......... service Two await() method...");
	            cyclicBarrier.await();
	            System.out.println("called service Two await()... method....");
	
	        } catch (InterruptedException e) {
	
	            System.out.println("Service two interrupted! (InterruptedException using await()) ");
	
	            e.printStackTrace();
	
	        } catch (BrokenBarrierException e) {
	
	            System.out.println("Service two interrupted! - BrokenBarrierException");
	
	            e.printStackTrace();
	
	        }
	
	        System.out.println("The wait is over, lets complete Service two!");
	
	 
	
	    }


}
