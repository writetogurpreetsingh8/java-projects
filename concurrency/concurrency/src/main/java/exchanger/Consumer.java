package exchanger;

import java.util.concurrent.Exchanger;

public class Consumer implements Runnable{

    Exchanger<String> exchanger;
    String str;
    
    Consumer(Exchanger<String> exchanger){
        this.exchanger=exchanger;
    }
    
    public void run(){
    	
    	System.out.println("started consumer thread "+Thread.currentThread().getName());
    	
           for(int i=0; i<5;i++){
                  try {
                	  System.out.println("going to get data from exchange "+Thread.currentThread().getName());
                        str= exchanger.exchange(new String());
                        System.out.println("got data from exchange "+Thread.currentThread().getName());
                        System.out.println("CONSUMED data from producer : " + str  );
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
           }
    } 
}
