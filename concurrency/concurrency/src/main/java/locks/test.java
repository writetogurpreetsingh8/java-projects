package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;




class Resource{
	
	Lock l = new ReentrantLock();
	void show(){
		l.lock();
		System.out.println("show() method....");
		l.unlock();
	}
	
	void display() throws InterruptedException{
		l.lock();
		System.out.println("display() method....");
		Thread.sleep(5000);
		l.unlock();
	}
}

class T11 extends Thread{
	
	Resource r;
	
	T11(Resource r){
		this.r = r;
	}
	
	public void run(){
		
		for(int i=0;i<10;i++){
			try {
				r.display();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class T12 extends Thread{
	
	Resource r;
	
	T12(Resource r){
		this.r = r;
	}

	public void run(){
		
		for(int i=0;i<10;i++){
			r.show();
		}

	}
}


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Resource r = new Resource();
		
		T11 obj1 = new T11(r);
		T12 obj2 = new T12(r);
		
		obj1.start();obj2.start();

	}

}
