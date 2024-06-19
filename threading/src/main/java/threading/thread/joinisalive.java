package threading.thread;

// if we use join() method into calling thread(which is being invoked) , it will suspend calling thread permanently, 
// will not recover at all , so use join() method in (caller thread) parent thread to make parent thread wait 
// until its child gets over... (main or parent thread to make wait for child threads, not used in child threads)


class a extends Thread   
{
	
	public void run()
	{
			System.out.println("starting thread a");
			
			try{	
				
				for (int i=0;i<10;i++){
				System.out.println("the value of i is(in a class)" + i);
				sleep(1000);
				if(i==5)
				join(); // see above description
				}
			}catch(InterruptedException e){
				System.out.println("a exception is "+e);	
			}
		
		System.out.println("exit from thread a");
		
		
	}	
}



class b extends Thread   
{
	
	public void run()
	{
			System.out.println("starting thread b");
			
			try{
				for (int i=0;i<10;i++){
				System.out.println("the value of i is(in b class)" + i);
				sleep(2000);
				//if(i==8)
				//join(); // see above description
				}
			}catch(InterruptedException e){
				System.out.println("a exception is "+e);	
			}
			
					
		
		System.out.println("exit from thread b");
	}	
}



class joinisalive
{
	
	public static void main(String []q)
	{
		System.out.println(" starting main thread");
		
		a obja=new a();
		System.out.println("thread a is  "+obja.isAlive());
		obja.start();
		System.out.println("thread a is  "+obja.isAlive());
		
		b objb=new b();
		objb.start();
		System.out.println("thread b is  "+objb.isAlive());	
		
		try
		{
			obja.join();
			objb.join();
		}
		catch(InterruptedException iep){}
		
		System.out.println("thread a is  "+obja.isAlive());
		System.out.println("thread b is  "+objb.isAlive());
		
		System.out.println("main thread is over");
	
		System.out.println("thread a is  "+obja.isAlive());
		System.out.println("thread b is  "+objb.isAlive());
		
	}	
}
