package threading.thread;

class DemoDaemon {
	
	private Thread t;
	
	void runDaemonThread(Runnable daemonTask, boolean daemon) {
		 this.t = new Thread(daemonTask);
		t.setDaemon(daemon); // Set this thread as a daemon thread
		t.start();
		System.out.println("child deamon thread has started.......");
	}

	public Thread getT() {
		return t;
	}
	
}
public class Daemon_Threads {

	public static void main(String[] args) {
		
		DemoDaemon daemon = new DemoDaemon();
		
		Runnable task = () -> {
			while(true) {
				System.out.println("Daemon thread is running... "+Thread.currentThread().getName());
				System.out.println("is Daemon "+Thread.currentThread().isDaemon());
				try {
					System.out.println("Daemon thread is sleeping...........");
					Thread.sleep(10000); // sleep for 10 sec
					System.out.println("Daemon thread is awaking.............");
				} catch (InterruptedException e) {
					System.out.println("Daemon thread inside catch... "+Thread.currentThread().getName());
					System.out.println("Daemon thread interrupt status catch... "+Thread.currentThread().isInterrupted());
                   // Thread.currentThread().interrupt();   // Restore the interrupt status.
                  //By doing this, if there is any further code or outer method that checks the thread’s interrupt status, 
                    //it will see that the thread has been interrupted. 
                    //This can be crucial for ensuring that interruption signals are handled properly throughout the application
                    System.out.println("Daemon thread interrupted.");
                    //Thread.interrupted();
                }
			}
		};
		
		daemon.runDaemonThread(task, false);
		
		try {
            Thread.sleep(3000); // Main thread sleeps for 3 seconds
            System.out.println("Main threading is awaking.........");
            daemon.getT().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();   // Restore the interrupt status. 
            //By doing this, if there is any further code or outer method that checks the thread’s interrupt status, 
            //it will see that the thread has been interrupted. 
            //This can be crucial for ensuring that interruption signals are handled properly throughout the application
            System.out.println("Main thread interrupted.");
        }
		System.out.println(daemon.getT().isInterrupted());
        System.out.println("Main thread is finished.");
	}

}
