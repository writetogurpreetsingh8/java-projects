package schedulerTPE;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledTPE {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);
	stpe.scheduleAtFixedRate(new schedulerTPE.Threadd(), 0L, 6L, TimeUnit.SECONDS);
	}

}
