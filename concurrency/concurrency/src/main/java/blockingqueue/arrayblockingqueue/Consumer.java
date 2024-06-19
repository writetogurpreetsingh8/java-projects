package blockingqueue.arrayblockingqueue;

public class Consumer  implements Runnable{

	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					System.out.println("Consumed value is "+buffer.consume());
					//System.out.println("Consumer - queue size is "+buffer.getQueue().size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
	
	
}
