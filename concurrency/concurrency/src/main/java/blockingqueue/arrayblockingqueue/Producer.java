package blockingqueue.arrayblockingqueue;


public class Producer implements Runnable{

	private Buffer buffer;
	
	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					int k = (int)(Math.random() * 10000);
					buffer.produce(String.valueOf(k));
					System.out.println("Produced value is "+k);
					//System.out.println("Producer - queue size is "+buffer.getQueue().size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
	
	
	
}
