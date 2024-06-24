package locks.writereadlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Buffer{

	ReadWriteLock readWriteLock;
	Lock readLock;
	Lock writeLock;
	
	Buffer(ReadWriteLock readWriteLock){
		
		this.readWriteLock = readWriteLock;
		readLock = readWriteLock.readLock();
		writeLock = readWriteLock.writeLock();
	}
	
	public void readData() throws InterruptedException{
		System.out.println("entered into readData() method... "+Thread.currentThread().getName());
		//TimeUnit.SECONDS.sleep(2);
		readLock.lock();
		System.out.println("got readLock & executing... readData().....");
		writeData();
		readLock.unlock();
	}
	
	public void writeData() throws InterruptedException{
		System.out.println("entered into writeData() method.. "+Thread.currentThread().getName());
		writeLock.lock();
		System.out.println("got writeLock & executing writeData() method "+Thread.currentThread().getName());
		TimeUnit.SECONDS.sleep(1);
		//writeData();
		writeLock.unlock();
	}
}

class ReadThread extends Thread{

	private Buffer buffer;
	
	ReadThread(Buffer buffer){
		this.buffer = buffer;
	}
	
	public void run(){
		try {
			buffer.readData();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class WriteThread extends Thread{

	private Buffer buffer;
	
	WriteThread(Buffer buffer){
		this.buffer = buffer;
	}
	
	public void run(){
		try {
			buffer.writeData();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



public class ReadWriteLocks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Buffer buffer = new Buffer(new ReentrantReadWriteLock(true));
		
		ReadThread readThread = new ReadThread(buffer);
		WriteThread writeThread = new WriteThread(buffer);
		
		readThread.setPriority(1);
		writeThread.setPriority(10);
		
		readThread.start();
		//writeThread.start();

		/*java.util.concurrent.locks.ReadWriteLock lock1 = new ReentrantReadWriteLock(true);
		java.util.concurrent.locks.ReadWriteLock lock2 = new ReentrantReadWriteLock(true);
		
		Lock writeLock1 = lock1.writeLock();
		Lock readLock1 = lock1.readLock();
		
		Lock writeLock2 = lock2.writeLock();
		Lock readLock2 = lock2.readLock();
		
		System.out.println("getting write lock....");
		readLock1.lock();
		System.out.println("got write lock.....");
		System.out.println("getting read lock....");
		writeLock2.lock();
		System.out.println("got read lock.....");
		
		System.out.println("now write lock getting release...");
		writeLock2.unlock();
		System.out.println("write lock released...");
		
		System.out.println("now read lock getting release...");
		readLock1.unlock();
		System.out.println("read lock released...");*/

	}

}
