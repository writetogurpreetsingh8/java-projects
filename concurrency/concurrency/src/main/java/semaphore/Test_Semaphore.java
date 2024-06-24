package semaphore;

import java.util.concurrent.Semaphore;

public class Test_Semaphore {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(Integer.MAX_VALUE);
		
		System.out.println("Total available Semaphore permits : "
				+ semaphore.availablePermits());
		
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		System.out.println(semaphore.availablePermits());
		
	}

}
