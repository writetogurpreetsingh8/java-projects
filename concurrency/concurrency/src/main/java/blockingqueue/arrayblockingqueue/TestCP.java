package blockingqueue.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestCP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		Buffer buffer = new Buffer(queue);
		
		Consumer c = new Consumer(buffer);
		Thread tc = new Thread(c);
		tc.setPriority(10);
		tc.start();
		
		Producer p = new Producer(buffer);
		Thread tp = new Thread(p);
		tp.setPriority(1);
		tp.start();
	}

}
