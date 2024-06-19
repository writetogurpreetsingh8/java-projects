package locks;


class buffer1{
	
	private int value;
	private boolean isExecute;
	
	synchronized void set(int value){
		System.out.println("Thread calling.... set() method "+Thread.currentThread().getName());
			while(isExecute){
				try {
					System.out.println(Thread.currentThread().getName()+" waiting,.........");
					wait();
					System.out.println(Thread.currentThread().getName()+" resume,.........");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
				this.value = value;
				System.out.println(Thread.currentThread().getName()+" produce "+this.value);
				isExecute = true;
				notify();
				System.out.println("Thread leaving.... set() method "+Thread.currentThread().getName());
	}
	synchronized void  get(){
		System.out.println("Thread calling.... get() method "+Thread.currentThread().getName());
			while(!isExecute){
				try {
					System.out.println(Thread.currentThread().getName()+" waiting,.........");
					wait();
					System.out.println(Thread.currentThread().getName()+" resume,.........");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
			System.out.println(Thread.currentThread().getName()+" consume "+value);
			isExecute = false;
			notify();
			System.out.println("Thread leaving.... get() method "+Thread.currentThread().getName());
	}
	
	
}
class consume1 extends Thread{
	
	private buffer1 resource;
	
	public consume1(buffer1 resource) {
		this.resource = resource;
	}
	
	public void run(){
		System.out.println("consumer has started............");
		for(int i=0;i<10;i++){
			resource.get();
		}
	}
	
}
class produce1 extends Thread{
	
	private buffer1 resource;
	
	public produce1(buffer1 resource) {
		this.resource = resource;
	}
	
	public void run(){
		System.out.println("producer has started............");
		for(int i=0;i<10;i++){
			resource.set(i);
		}
	}
	
}
public class ConsumerProducerProblemWithOutLocks {

	public static void main(String[] args) {
		buffer1 resource = new buffer1();
		consume1 c = new consume1(resource);
		c.setName("consumer");
		produce1 p = new produce1(resource);
		p.setName("producer");
		c.start();p.start();
	}

}
