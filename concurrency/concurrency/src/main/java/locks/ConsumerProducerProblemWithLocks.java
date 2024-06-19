package locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class buffer{
	
	private int value;
	private ReentrantLock lock = new ReentrantLock();
	private Condition consumer = lock.newCondition();
	private Condition producer = lock.newCondition();
	
	private boolean isExcute;
	
	void set(int value){
		System.out.println("trying to get producer lock..... by thread "+Thread.currentThread().getName());
		lock.lock();
		System.out.println("got producer lock..... by thread "+Thread.currentThread().getName());
			while(isExcute){
				try {
					System.out.println("producer waited..... by thread "+Thread.currentThread().getName());
					producer.await();
					System.out.println("producer awaking..... by thread "+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.value = value;
			System.out.println(Thread.currentThread().getName()+" produce "+this.value);
			isExcute = true;
			System.out.println("producer..... giving signaling to the consumer - by thread "+Thread.currentThread().getName());
			consumer.signal();
			System.out.println("producer..... gave signaling to the consumer - by thread "+Thread.currentThread().getName());
			lock.unlock();
			System.out.println("producer..... unlock itself - by thread "+Thread.currentThread().getName());
	}
	void get(){
		System.out.println("trying to get consume lock..... by thread "+Thread.currentThread().getName());
		lock.lock();
		System.out.println("got consumer lock..... by thread "+Thread.currentThread().getName());
		while(!isExcute){
			try {
				System.out.println("consumer waited..... by thread "+Thread.currentThread().getName());
				consumer.await();
				System.out.println("consumer awaking..... by thread "+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			System.out.println(Thread.currentThread().getName()+" consume "+value);
			isExcute = false;
			System.out.println("consumer..... giving signaling to the producer - by thread "+Thread.currentThread().getName());
			producer.signal();
			System.out.println("consumer..... gave signaling to the producer - by thread "+Thread.currentThread().getName());
			lock.unlock();
			System.out.println("consumer..... unlock itself - by thread "+Thread.currentThread().getName());
	}
	
}
class consume extends Thread{
	
	private buffer resource;
	
	public consume(buffer resource) {
		this.resource = resource;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
			resource.get();
		}
	}
	
}
class produce extends Thread{
	
	private buffer resource;
	
	public produce(buffer resource) {
		this.resource = resource;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
			resource.set(i);
		}
	}
	
}
public class ConsumerProducerProblemWithLocks {

	public static void main(String[] args) {
		buffer resource = new buffer();
		consume c = new consume(resource);
		c.setName("Consumer - Thread");
		produce p = new produce(resource);
		p.setName("Producer - Thread");
		c.start();p.start();
	}

}
