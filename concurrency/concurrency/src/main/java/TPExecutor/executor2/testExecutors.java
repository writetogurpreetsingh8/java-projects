package TPExecutor.executor2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;


public class testExecutors {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//ExecutorCompletionService<V>
		//Executor
		//SynchronousQueue<E>
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future f = es.submit(new Threadd(),"dfd");
		System.out.println(f.get());
		//new ScheduledThreadPoolExecutor()
		System.out.println("end main thread..");
	}
}
