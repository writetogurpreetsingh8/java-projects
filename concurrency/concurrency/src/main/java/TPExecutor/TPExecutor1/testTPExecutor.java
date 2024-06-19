package TPExecutor.TPExecutor1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class testTPExecutor {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		/*ArrayBlockingQueue lb = new ArrayBlockingQueue(5);*/
		LinkedBlockingQueue lb = new LinkedBlockingQueue(4);
		int i = 0;
		ThreadPoolExecutor tp = new ThreadPoolExecutor(2, 3, 7, TimeUnit.MINUTES, lb,new ThreadPoolExecutor.DiscardOldestPolicy());
		while(true){
			tp.execute(new Threadd());
			i++;
			System.out.println("value of counter "+i);
			System.out.println("lb size is "+lb.size());
			System.out.println("lb size is "+lb.toString());
			if(i>7)
				break;
		}
		tp.awaitTermination(10000, TimeUnit.SECONDS);
		tp.shutdown();
System.out.println("main thread over............................................................................................................");
		System.out.println(lb.toString());
	}

}
