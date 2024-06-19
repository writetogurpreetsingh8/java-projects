package blockingqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Buffer {
	
	private String value;
	private BlockingQueue<String> queue;

	public BlockingQueue<String> getQueue() {
		return queue;
	}

	public Buffer(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	private String getValue() throws InterruptedException {
		return value = queue.take();
	}

	private void setValue(String value) throws InterruptedException {
		this.value = value;
		this.queue.put(this.value);
	}

	public void produce(String value) throws InterruptedException{
		setValue(value);
	}
	
	public String consume() throws InterruptedException{
		return getValue();
	}

}
