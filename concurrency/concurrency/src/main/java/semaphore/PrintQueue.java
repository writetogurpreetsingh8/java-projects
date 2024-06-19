package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

	//private final Lock queueLock=new ReentrantLock();
	private final Semaphore semaphore;
	
	public PrintQueue(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	public void printJob(Object document){
		try {
			System.out.println(Thread.currentThread().getName()+" getting semaphore ");
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName()+" semaphore value is  "+semaphore.availablePermits());
			//System.out.println(Thread.currentThread().getName()+" now trying to get lock");
			//queueLock.lock();
			//System.out.println(Thread.currentThread().getName()+" got the lock");
			Long duration=(long)(Math.random()*10000);
			System.out.println(Thread.currentThread().getName()+ ":PrintQueue: Printing a Job during "+(duration/1000)+
			" seconds");
			Thread.sleep(duration);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}finally {
			//	queueLock.unlock();
				//System.out.println(Thread.currentThread().getName()+" release the lock");
				semaphore.release();
				System.out.println(Thread.currentThread().getName()+" releasing semaphore ");
				System.out.println(Thread.currentThread().getName()+" semaphore value is  "+semaphore.availablePermits());
			}
		
	}
}
