package locks.writereadlocks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	private static String message = "a";
	
	    public static void main(String[] args) throws InterruptedException{
	    	
	        Thread t1 = new Thread(new Read());
	
	        Thread t2 = new Thread(new WriteA());

	        Thread t3 = new Thread(new WriteB());

	        t1.start();
	        t2.start();
	        t3.start();
	        
	        t1.join();
	        t2.join();
	        t3.join();
	        
	        System.out.println("final message is "+message);
	        
	       // t1 = new Thread(new Read());
	       // t1.start();
	    }

	    
	    static class Read implements Runnable {
	    	        public void run() {
	    	        	System.out.println("Read is running................");
	    	            for(int i = 0; i<= 10; i ++) {
	    	                if(lock.isWriteLocked()) {
	    	                    System.out.println("I'll take the lock from Write");
	    	                }
	    	                System.out.println("lock.isWriteLocked() "+lock.isWriteLocked());
	    	                lock.readLock().lock();
	    	                System.out.println("Thread Read accquire the lock.");
	    	                System.out.println("ReadThread " + Thread.currentThread().getId() + " ---> Message is " + message );
	    	                lock.readLock().unlock();
	    	                System.out.println("Thread Read leaving the lock.............");
	    	
	    	                }
	    	            System.out.println("Read is ending................");
	    	            }
	    	        }
	    
	    static class WriteA implements Runnable {
	    	        public void run() {
	    	        	System.out.println("writeA is running................");
	    	            for(int i = 0; i<= 10; i ++) {
	    	                try {
	    	                    lock.writeLock().lock();
	    	                    System.out.println("Thread writeA accquire the lock.");
	    	                    try {
									Thread.sleep(10000);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	    	                    message = message.concat("a");
	    	                    System.out.println(" Thread writeA contactinate the message "+message);
	    	                } finally {
	    	                    lock.writeLock().unlock();
	    	                    System.out.println("Thread writeA leaving the lock.............");
	    	                }
	    	            }
	    	            System.out.println("writeA is ending................");
	    	            }
	    	        }
	    
	    static class WriteB implements Runnable {
	        public void run() {
	        	System.out.println("writeB is running................");
	            for(int i = 0; i<= 10; i ++) {
	                try {
	                    lock.writeLock().lock();
	                    System.out.println("Thread writeB accquire the lock.");
	                    message = message.concat("b");
	                    		try {
									Thread.sleep(10000);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								};
	                    System.out.println(" Thread writeB contactinate the message "+message);
	                } finally {
	                    lock.writeLock().unlock();
	                    System.out.println("Thread writeB leaving the lock.............");
	                }
	            }
	            System.out.println("writeB is ending................");
	            }
	        }

}
