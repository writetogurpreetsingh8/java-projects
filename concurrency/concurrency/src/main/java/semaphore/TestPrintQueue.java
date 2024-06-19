package semaphore;

import java.util.concurrent.Semaphore;


public class TestPrintQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Semaphore semaphore = new Semaphore(3);
		PrintQueue printQueue=new PrintQueue(semaphore);
		Thread thread[]=new Thread[10];
		for (int i=0; i<4; i++){
		thread[i]=new Thread(new Job(printQueue),"Thread "+ i);
		}
		for (int i=0; i<4; i++){
			thread[i].start();
			}
	}

}
