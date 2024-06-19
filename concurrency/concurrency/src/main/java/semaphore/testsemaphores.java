package semaphore;

import java.util.concurrent.Semaphore;

public class testsemaphores {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Semaphore semaphore = new Semaphore(2);
		System.out.println("Total available Semaphore permits : "
				+ semaphore.availablePermits());
		
		MyLockerThread t1 = new MyLockerThread("Thread-t1", semaphore);
		MyLockerThread t2 = new MyLockerThread("Thread-t2", semaphore);
		MyLockerThread t3 = new MyLockerThread("Thread-t3", semaphore);
		
		t1.start();t2.start();t3.start();
		
		/*t1.join();t2.join();t3.join();
		
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());*/
		System.out.println("finish main thread.....................");
	}

}
