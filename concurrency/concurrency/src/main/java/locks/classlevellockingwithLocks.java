package locks;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


class Resource1{
	
	private static ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock lock1 = new ReentrantLock();
	
	static void show(int i) throws InterruptedException{
		lock.lock();
		System.out.println(Thread.currentThread().getName());
		for (int j = 0; j <i; j++) {
			System.out.println("show() value of j "+j);
			TimeUnit.MILLISECONDS.sleep(1000);
		}	
		lock.unlock();
	}
	
	static void show1(int i) throws InterruptedException{
		lock1.lock();
		System.out.println(Thread.currentThread().getName());
		for (int j = 0; j <i; j++) {
			System.out.println("show1() value of j "+j);
			TimeUnit.MILLISECONDS.sleep(1000);
		}	
		lock1.unlock();
	}
	
	 void display(int j) throws InterruptedException{
		 lock1.lock();
		 System.out.println(Thread.currentThread().getName());
		 for (int k = 0; k <j; k++) {
			 System.out.println("display() value of k "+k);
			 TimeUnit.MILLISECONDS.sleep(1000);
		 }
		 lock1.unlock();
	}
	 
	 void display1(int j) throws InterruptedException{
		 lock1.lock();
		 System.out.println(Thread.currentThread().getName());
		 for (int k = 0; k <j; k++) {
			 System.out.println("display1() value of k "+k);
			 TimeUnit.MILLISECONDS.sleep(1000);
		 }
		 lock1.unlock();
	}
	 
	 void starting(int j) throws InterruptedException{
		 synchronized (Resource.class) {
					
		 System.out.println(Thread.currentThread().getName());
		 for (int k = 0; k <j; k++) {
			 System.out.println("starting() value of k "+k);
			 TimeUnit.MILLISECONDS.sleep(1000);
		 }
	 }
	 }
	 void starting1(int j) throws InterruptedException{
		 synchronized (Resource.class) {
					
		 System.out.println(Thread.currentThread().getName());
		 for (int k = 0; k <j; k++) {
			 System.out.println("starting1() value of k "+k);
			 TimeUnit.MILLISECONDS.sleep(1000);
		 }
	 }
	 }
}

class t1 extends Thread{
	
	private Resource1 r;
	t1(Resource1 r){
		this.r = r;
	}
	public void run(){
		
			try {
				System.out.println("thread t1 has started.......");
				r.display1(5);
				System.out.println("thread t1 has ended.......");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

class t2 extends Thread{
	
	private Resource1 r;
	t2(Resource1 r){
		this.r = r;
	}
	public void run(){
			try {
				r.display(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


class t3 extends Thread{
	
	private Resource1 r;
	t3(Resource1 r){
		this.r = r;
	}
	public void run(){
			try {
				System.out.println("thread t3 has started.......");
				r.show1(5);
				System.out.println("thread t3 has ended.......");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

public class classlevellockingwithLocks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Resource1 r = new Resource1();
		t1 o1 = new t1(r);
		System.out.println("thread t1 is about to start");
		o1.start();
		
		Resource1 r1 = new Resource1();
		t2 o2 = new t2(r);
		System.out.println("thread t2 is about to start");
		o2.start();
		/*
		Resource r2 = new Resource();
		t3 o3 = new t3(r);
		System.out.println("thread t3 is about to start");
		o3.start();*/
	}

}
