package threading.exceptionhandling;

class UserThread extends Thread{

	public void run(){
	
		System.out.println("thread a has been started.............");
		System.out.println(Thread.currentThread().getThreadGroup());
		UserThread1 l = new UserThread1();
		try {
			l.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.start();
		//try{
		throw new RuntimeException("error............");
		
			//int i=8/0;
		//}catch(Exception e){
		//	System.out.println("try block "+e);
		//}
		//System.out.println("divided by zero......");
		
	}
}

class UserThread1 extends Thread{

	public void run(){
	
		System.out.println("thread a has been started.............");
		System.out.println(Thread.currentThread().getThreadGroup());
		//try{
		throw new RuntimeException("error............");
		
			//int i=8/0;
		//}catch(Exception e){
		//	System.out.println("try block "+e);
		//}
		//System.out.println("divided by zero......");
		
	}
}


class Exception_Handling_With_Thread{
		
		
		public static void main(String... a){
		
		
			UserThread obj = new UserThread();
			System.out.println(Thread.currentThread().getThreadGroup());
			/*
			 * Thread.currentThread().setUncaughtExceptionHandler(new
			 * Thread.UncaughtExceptionHandler(){ public void uncaughtException(Thread t ,
			 * Throwable e){ System.out.println("Main Thread specify exception handler "+e);
			 * System.out.println("on the thread is "+t); } });
			 */
			
			Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
				public void uncaughtException(Thread t , Throwable e){
					System.out.println("default exception caughts for all the threads "+e);
					System.out.println("on the thread is "+t);
				}
			});
			
			obj.start();
			
			/*
			 * obj.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){ public
			 * void uncaughtException(Thread t , Throwable e){
			 * System.out.println("child thread "+t.getName()+" exception caught is "+e);
			 * System.out.println("on the thread is "+t); } } );
			 */
			
			int k = 10/0;
			System.out.println("main thread over........");
		
		}
		
}