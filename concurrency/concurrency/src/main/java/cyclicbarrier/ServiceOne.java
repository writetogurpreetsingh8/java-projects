package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ServiceOne implements Runnable{
	
	private final CyclicBarrier cyclicBarrier;
	
	 
	
	    public ServiceOne(CyclicBarrier cyclicBarrier) {
	
	        this.cyclicBarrier = cyclicBarrier;
	
	    }
	
	 
	
	    @Override
	
	    public void run() {

	        System.out.println("Starting service One...");
	
	        try {

	            Thread.sleep(10000);
	
	        } catch (InterruptedException e1) {

	        	System.out.println("service one interupted...(InterruptedException)...");
	            e1.printStackTrace();

	        }

	       // System.out.println("Service One has finished its work... waiting for others...");

	        try {
	        	System.out.println("calling......... service one await() method...");
	            cyclicBarrier.await();
	            System.out.println("called service one await()... method....");

	        } catch (InterruptedException e) {
	
	            System.out.println("Service one interrupted! (InterruptedException using await()).............");
	
	            e.printStackTrace();
	
	        } catch (BrokenBarrierException e) {
	
	            System.out.println("Service one interrupted! - BrokenBarrierException");
	
	            e.printStackTrace();
	
	        }
	
	        System.out.println("The wait is over, lets complete Service One!");
	
	 
	
	    }


}
