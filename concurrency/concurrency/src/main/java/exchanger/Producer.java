package exchanger;

import java.util.concurrent.Exchanger;

public class Producer implements Runnable{
	
	Exchanger<String> exchanger;
    String str;
    
    Producer(Exchanger<String> exchanger){
    	
           str = new String();
           this.exchanger = exchanger;
    }
    
    @Override
    public void run(){
    	
    	System.out.println("started producer thread "+Thread.currentThread().getName());
    	
           for(int i=1;i<=5;i++){
                  str+=i;
                  System.out.println("Produced : "+i);
                  try {
                	  System.out.println("going to put data into exchange "+Thread.currentThread().getName());
                	   str = exchanger.exchange(str);
                	   System.out.println("data has been put into exchange "+Thread.currentThread().getName());
                       System.out.println("data received from consumer ends "+str+" ....");
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
           }
    }

}
