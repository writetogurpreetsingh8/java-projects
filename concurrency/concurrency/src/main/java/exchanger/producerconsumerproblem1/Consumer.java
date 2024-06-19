package exchanger.producerconsumerproblem1;

import java.util.concurrent.Exchanger;

public class Consumer implements Runnable{

	private Buffer buffer;
	private Exchanger<Buffer> exchanger;
	
	public Consumer(Buffer buffer, Exchanger<Buffer> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		System.out.println("Consumer Thread "+Thread.currentThread().getName()+" started........");
		for(int i=0; i<10; i++){
			try {
				System.out.println("consumer thread getting buffer from exchanger......");
				buffer = exchanger.exchange(new Buffer());
				System.out.println("consumer thread got the buffer from exchanger...... ");
				System.out.println("Consumed Value is "+buffer.getCounter());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Consumer Thread "+Thread.currentThread().getName()+" ended........");
		
	}

}
