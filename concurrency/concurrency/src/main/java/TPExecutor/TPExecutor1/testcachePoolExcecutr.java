package TPExecutor.TPExecutor1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testcachePoolExcecutr {
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		es.submit(new Threadd());
		es.submit(new Threadd());
		es.submit(new Threadd());
		es.submit(new Threadd());
		es.submit(new Threadd());
	}
}
