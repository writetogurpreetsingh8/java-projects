package semaphore;

import java.util.concurrent.Semaphore;

/**
 * It's just like producer/consumer problem
 */
class EvenOddGenerator{

	private final Semaphore semaphoreEven;
	private final Semaphore semaphoreOdd;
	
	public EvenOddGenerator(Semaphore semaphoreEven, Semaphore semaphoreOdd) {
		this.semaphoreEven = semaphoreEven;
		this.semaphoreOdd = semaphoreOdd;
	}
	
	void printEven() {
		try {
			semaphoreEven.acquire();
			System.out.println("EVEN");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphoreOdd.release();
		}
	}
	
	void printOdd() {
		try {
			semaphoreOdd.acquire();
			System.out.println("ODD");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphoreEven.release();
		}
	}
}



class Even implements Runnable{

	private final EvenOddGenerator sm;
	

	public Even(EvenOddGenerator sm) {
		this.sm = sm;
	}

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			sm.printEven();
		}
	}
}

class Odd implements Runnable{
	
private final EvenOddGenerator sm;

	public Odd(EvenOddGenerator sm) {
		this.sm = sm;
	}

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			sm.printOdd();
		}
	}
}

/**
 * It's just like producer/consumer problem
 */
public class Odd_Even_with_Semaphore {

	public static void main(String[] args) {
		
		Semaphore evenSem = new Semaphore(1);
		Semaphore oddSem = new Semaphore(0);
		
		EvenOddGenerator sm = new EvenOddGenerator(evenSem, oddSem);
		Thread oddThread = new Thread(new Odd(sm));
		oddThread.start();
		
		Thread evenThread = new Thread(new Even(sm));
		evenThread.start();
	}

}
