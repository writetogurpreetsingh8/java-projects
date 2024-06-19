package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	
	public static void main(String args[]) {
		
	       final CountDownLatch latch = new CountDownLatch(3);
	       
	       Thread cacheService = new Thread(new Service("CacheService", 5000, latch));
	       Thread alertService = new Thread(new Service("AlertService", 3000, latch));
	       Thread validationService = new Thread(new Service1("ValidationService1", 10000, latch));
	       
	       cacheService.setName("CacheService");
	       alertService.setName("AlertService");
	       validationService.setName("ValidationService1");
	       
	       cacheService.start(); //separate thread will initialize CacheService
	       alertService.start(); //another thread for AlertService initialization
	       validationService.start();
	      
	       // application should not start processing any thread until all service is up
	       // and ready to do there job.
	       // Countdown latch is idle choice here, main thread will start with count 3
	       // and wait until count reaches zero. each thread once up and read will do
	       // a count down. this will ensure that main thread is not started processing
	       // until all services is up.
	      
	       //count is 3 since we have 3 Threads (Services)
	        
	       try{
	    	  // alertService.interrupt();
	    	 // validationService.interrupt();
	    	  // cacheService.interrupt();
	    	   System.out.println("Thread.currentThread() is "+Thread.currentThread().getName());
	    	  // Thread.currentThread().interrupt();
	           latch.await();  //main thread is waiting on CountDownLatch to finish
	            System.out.println("All services are up, Application is starting now");
	       }catch(Exception ie){
	    	   System.out.println("interrupted into main thread");
	           ie.printStackTrace();
	       }
	      
	    }

}
