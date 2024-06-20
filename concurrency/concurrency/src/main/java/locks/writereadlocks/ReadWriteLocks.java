package locks.writereadlocks;

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
	
	public void readData(){
		System.out.println("entered into readData() method... "+Thread.currentThread().getName());
		readLock.lock();
		System.out.println("calling writeData().....");
		//writeData();
		readLock.unlock();
		
	}
	
	public void writeData(){
		System.out.println("entered into writeData() method.. "+Thread.currentThread().getName());
		writeLock.lock();
		readData();
		System.out.println("writeData() method "+Thread.currentThread().getName());
		writeLock.unlock();
	}
}

class ReadThread extends Thread{

	private Buffer buffer;
	
	ReadThread(Buffer buffer){
		this.buffer = buffer;
	}
	
	public void run(){
		buffer.readData();
	}
}

class WriteThread extends Thread{

	private Buffer buffer;
	
	WriteThread(Buffer buffer){
		this.buffer = buffer;
	}
	
	public void run(){
		buffer.writeData();
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
		
		readThread.setPriority(10);
		writeThread.setPriority(1);
		
		//readThread.start();
		writeThread.start();

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
