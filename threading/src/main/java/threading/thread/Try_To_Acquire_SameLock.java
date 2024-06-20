package threading.thread;

/**
 * synchronized methods in Java do support reentrant locking. 
 * This means that if a thread holds a lock on a synchronized method or block, 
 * it can re-enter the same synchronized method or block (or another synchronized method/block on the same object) 
 * without causing a deadlock.
 */
class Resource3{
	
	 synchronized void dis() throws Exception{
		
		System.out.println("in side currentThread() from "+Thread.currentThread());
		//BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("waiting.. for user input......");
		//String name=bf.readLine();
		//System.out.println(name);
		play();
	}
	 
	 synchronized void play() {
		 System.out.println("invoking play...........");
	 }
}

class UserThread3 extends Thread{
	
	private Resource3 res;
	
	UserThread3(Resource3 res){
		this.res = res;
	}
	public void run(){
		try{
			res.dis();	
		}catch(Exception e){
			System.out.println("e is "+e);
		}
	}
	
}
public class Try_To_Acquire_SameLock {

	public static void main(String[] args) {
		Resource3 res = new Resource3();
		UserThread3 obj1=new UserThread3(res);
		obj1.start();
	}

}
