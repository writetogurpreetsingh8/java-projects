package locks;

import java.util.concurrent.TimeUnit;

class Resource_1 {

	private Object obj = new Object();
	
	 void show2(int i) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " has got the lock via static block on obj...");
		synchronized(obj) {
		for (int n = 0; n < i; n++) {
			System.out.println("show() value of n " + n + " from " + Thread.currentThread().getName());
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		}
	}
	
	static synchronized void show(int i) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " has got the lock via Sync static method()...");
		for (int j = 0; j < i; j++) {
			System.out.println("show() value of j " + j + " from " + Thread.currentThread().getName());
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}

	static synchronized void show1(int i) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " has got the lock via Sync static method()...");
		for (int k = 0; k < i; k++) {
			System.out.println("show1() value of k " + k + " from " + Thread.currentThread().getName());
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}

	synchronized void display(int j) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " has got the lock via Sync instance method()...");
		for (int i = 0; i < j; i++) {
			System.out.println("display() value of i " + i + " from " + Thread.currentThread().getName());
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}

	void starting(int j) throws InterruptedException {

		System.out.println(
				Thread.currentThread().getName() + " waiting to acquire lock via Sync block on Object.class...");
		synchronized (Object.class) {
			System.out
					.println(Thread.currentThread().getName() + " has got the lock via Synch block on Object.class...");
			for (int l = 0; l < j; l++) {
				System.out.println("starting() value of l " + l + " from " + Thread.currentThread().getName());
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		}
	}

	void starting1(int j) throws InterruptedException {
		System.out.println(
				Thread.currentThread().getName() + " waiting to acquire lock via Sync block on Resource_1.class...");
		synchronized (Resource_1.class) {
			System.out.println(
					Thread.currentThread().getName() + " has got the lock via Sync block on Resource_1.class...");
			for (int m = 0; m < j; m++) {
				System.out.println("starting1() value of m " + m + " from " + Thread.currentThread().getName());
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		}
	}

	void starting2(int j) throws InterruptedException {
		System.out.println(
				Thread.currentThread().getName() + " waiting to acquire lock via Sync block on Resource_1.class...");
		synchronized (Resource_1.class) {
			System.out.println(
					Thread.currentThread().getName() + " has got the lock via Sync block on Resource_1.class...");
			for (int o = 0; o < j; o++) {
				System.out.println("starting2() value of o " + o + " from " + Thread.currentThread().getName());
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		}
	}
}

class one1 extends Thread {

	private Resource_1 r;

	one1(Resource_1 r) {
		this.r = r;
	}

	public void run() {

		try {
			System.out.println("thread t1 has started.......");
			r.show2(5);
			System.out.println("thread t1 has ended.......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class two2 extends Thread {

	private Resource_1 r;

	two2(Resource_1 r) {
		this.r = r;
	}

	public void run() {
		try {
			r.starting(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class three3 extends Thread {

	private Resource_1 r;

	three3(Resource_1 r) {
		this.r = r;
	}

	public void run() {
		try {
			System.out.println("thread t3 has started.......");
			r.show2(5);
			System.out.println("thread t3 has ended.......");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class classlevellocking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Resource_1 r = new Resource_1();
		one1 o1 = new one1(r);
		System.out.println("thread t1 is about to start");
		o1.start();

		Resource_1 r1 = new Resource_1();
		two2 o2 = new two2(r);
		System.out.println("thread t2 is about to start");
		o2.start();

		
		  //Resource_1 r2 = new Resource_1(); 
		 // three3 o3 = new three3(r2);
		 // System.out.println("thread t3 is about to start"); 
		 // o3.start();
		 
	}

}
