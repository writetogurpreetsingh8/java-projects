package threading.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;


//this example states that thread is not interrupted by IO and synchronized 

class Resource{
	
	 synchronized void dis() throws Exception{
		
		System.out.println("in side currentThread() from "+Thread.currentThread());
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("waiting.. for user input......");
		String name=bf.readLine();
		
		//Scanner in=new Scanner(System.in);
		//System.out.println("enter ur name ");
		
		//String name=in.nextLine();
		System.out.println("entered name is "+name);
		System.out.println(Thread.currentThread().isInterrupted());
		/*
		 * long start = new Date().getTime(); long end = start; while(end <
		 * (start+10000)) { end = new Date().getTime(); }
		 * System.out.println("10 sec over");
		 */
	}	
}

class UserThread1 extends Thread{
	
	private Resource res;
	
	UserThread1(Resource res){
		this.res = res;
	}
	public void run(){
		try{
			//System.out.println(Thread.currentThread().isInterrupted());
			res.dis();	
		}catch(Exception e){
			System.out.println("e is "+e);
		}
	}
	
}

class UserThread2 extends Thread{
	
	private Resource res;
	
	UserThread2(Resource res){
		this.res = res;
	}
	public void run(){
		try{
			//System.out.println(Thread.currentThread().isInterrupted());
			res.dis();	
		}catch(Exception e){
			System.out.println("e is "+e);
		}
	}
	
}


public class Interrupt_With_IO {

	public static void main(String[] args) throws InterruptedException {
		
		Resource res = new Resource();
		UserThread1 obj1=new UserThread1(res);
		UserThread2 obj2=new UserThread2(res);
		obj1.start();
		obj2.start();
		
		System.out.println("sespending main thread for some time..........");
		System.out.println("for mainThread "+Thread.currentThread());
		Thread.sleep(5000);
		System.out.println("awaking main thread after some time.");
		System.out.println("interrupting a thread from suspened status.");
		//obj1.interrupt();
		//obj2.interrupt();
		System.out.println("main thread is over........");
	}

}
