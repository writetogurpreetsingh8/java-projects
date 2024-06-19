package exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

	public static void main(String[] args) {
		
        Exchanger<String> exchanger=new Exchanger<String>();
        System.out.println("Exchanger has been created");
        
        Producer prod = new Producer(exchanger);
        Consumer cons = new Consumer(exchanger);
        
        Thread consThread = new Thread(cons,"consThread");
        Thread prodThread = new Thread(prod,"prodThread");

        consThread.start();     //start consumer thread.
        prodThread.start();     //start producer thread.

 }
	
}
