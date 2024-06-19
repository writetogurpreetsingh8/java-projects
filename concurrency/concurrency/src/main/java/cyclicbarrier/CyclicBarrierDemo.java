package cyclicbarrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

public class CyclicBarrierDemo {

	
	private static CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults  = Collections.synchronizedList(new ArrayList<List<Integer>>());
    private Random random = new Random();
    private int NUM_PARTIAL_RESULTS;
    private int NUM_WORKERS;
    private static Thread t;
    public class NumberCruncherThread implements Runnable{

    	@Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();
            List<Integer> partialResult = new ArrayList();

            // Crunch some numbers and store the partial result
            for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {    
                Integer num = random.nextInt(10);
                System.out.println(thisThreadName
                  + ": Crunching some numbers! Final result - " + num);
                partialResult.add(num);
            }

            partialResults.add(partialResult);
            try {
            	 System.out.println("cyclicBarrier getNumberWaiting are "+cyclicBarrier.getNumberWaiting());
                 System.out.println("cyclicBarrier getParties are "+cyclicBarrier.getParties());
                System.out.println(thisThreadName 
                  + " waiting for others to reach barrier.");
                Thread.sleep(500);
                cyclicBarrier.await();
                System.out.println("cyclicBarrier.await(); "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
            	System.out.println("interrupted exception "+Thread.currentThread().getName());
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
            	System.out.println("BrokenBarrierException exception "+Thread.currentThread().getName());
                e.printStackTrace();
            }
        }
    	
    }
    
    
    class AggregatorThread implements Runnable {
    	 
        @Override
        public void run() {
 
            String thisThreadName = Thread.currentThread().getName();
 
            System.out.println(
              thisThreadName + ": Computing sum of " + NUM_WORKERS 
              + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
            int sum = 0;
 
            for (List<Integer> threadResult : partialResults) {
                System.out.print("Adding ");
                for (Integer partialResult : threadResult) {
                    System.out.print(partialResult+" ");
                    sum += partialResult;
                }
                System.out.println();
            }
            System.out.println(thisThreadName + ": Final result = " + sum);
        }
    }
    
    
    public void runSimulation(int numWorkers, int numberOfPartialResults) {
        NUM_PARTIAL_RESULTS = numberOfPartialResults;
        NUM_WORKERS = numWorkers;
 
        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread());
 
        System.out.println("Spawning " + NUM_WORKERS
          + " worker threads to compute "
          + NUM_PARTIAL_RESULTS + " partial results each");
  
        for (int i = 0; i < NUM_WORKERS; i++) {
            Thread worker = new Thread(new NumberCruncherThread());
            worker.setName("Thread " + i);
            worker.start();
            t = worker;
        }
    }
    
    
    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.runSimulation(5, 3);
        try {
			Thread.sleep(500);
			System.out.println("awaking main thread......");
			t.interrupt();
		} catch (Exception e) {
			System.out.println("interrupted exception main "+Thread.currentThread().getName());
			e.printStackTrace();
		}
        System.out.println("cyclicBarrier count are "+cyclicBarrier.getNumberWaiting());
        System.out.println("cyclicBarrier count are "+cyclicBarrier.getParties());
        
    }
    
}
