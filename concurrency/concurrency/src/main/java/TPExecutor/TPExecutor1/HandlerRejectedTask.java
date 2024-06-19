package TPExecutor.TPExecutor1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class HandlerRejectedTask implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("r is "+r);
		System.out.println("executor is "+executor);

	}

}
