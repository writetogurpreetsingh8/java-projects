package exchanger.producerconsumerproblem1;

import java.util.concurrent.Exchanger;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Buffer buffer = new Buffer();
		
		Exchanger<Buffer> exchanger = new Exchanger<Buffer>();
		
		Consumer con = new Consumer(buffer, exchanger);
		Producer pro = new Producer(buffer, exchanger);
		
		Thread conT = new Thread(con);
		conT.setPriority(10);
		conT.start();
		
		Thread proT = new Thread(pro);
		proT.setPriority(1);
		proT.start();

	}

}
