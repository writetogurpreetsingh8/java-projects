package exchanger.producerconsumerproblem1;

import java.util.concurrent.Exchanger;

public class Producer implements Runnable{

	private Buffer buffer;
	private Exchanger<Buffer> exchanger;
	
	public Producer(Buffer buffer, Exchanger<Buffer> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		System.out.println("Producer Thread "+Thread.currentThread().getName()+" started........");
		for(int i=0; i<10; i++){
			buffer.setCounter((i+1));
			System.out.println("Producer puting buffer into exchanger "+buffer.getCounter());
			try {
				System.out.println("Produced Value is "+buffer.getCounter());
				buffer = exchanger.exchange(buffer);
				System.out.println("buffer has been send to consumer "+buffer.getCounter());
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		System.out.println("Producer Thread "+Thread.currentThread().getName()+" ended........");
	}

}
