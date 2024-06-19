package TPExecutor.TPExecutor1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class testTPExecutorSnchronizedQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		/*ArrayBlockingQueue lb = new ArrayBlockingQueue(5);*/
		SynchronousQueue lb = new SynchronousQueue();
		int i = 0;
		ThreadPoolExecutor tp = new ThreadPoolExecutor(2, 3, 7, TimeUnit.MINUTES, lb,new HandlerRejectedTask());
			tp.execute(new Threadd());
			tp.execute(new Threadd());
			tp.execute(new Threadd());
			tp.execute(new Threadd());

			System.out.println("lb size is "+lb.size());
			System.out.println("lb size is "+lb.toString());
			tp.shutdown();
		tp.awaitTermination(10000, TimeUnit.SECONDS);
System.out.println("main thread over............................................................................................................");
		System.out.println(lb.toString());
	}

}
