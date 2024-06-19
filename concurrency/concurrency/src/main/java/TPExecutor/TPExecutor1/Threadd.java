package TPExecutor.TPExecutor1;


public class Threadd implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("calling worker thread.... "+Thread.currentThread());
			Thread.sleep(7000);
		} catch (Exception e) {
			System.out.println("exception is "+e);
			e.printStackTrace();
		}
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+" i is "+i);
		}

	}

}
