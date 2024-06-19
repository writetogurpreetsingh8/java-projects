package schedulerTPE;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Threadd implements Runnable {

	@Override
	public synchronized void run() {
		for(int i=0;i<5;i++){
			System.out.println(Thread.currentThread().getName()+" i is "+i);
			try {
				for(;;);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
