package singleton.static_inner_class_approach;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * test singleton class against multithreading....
 */
class Printer{
	
	private Printer() {
		System.out.println("creating printer instance............");
	}
	
	private static class PrinterSingleton{
		private static Printer printer = new Printer();
	}
	
	public static Printer getPrinter() {
		return PrinterSingleton.printer;
	}
	
}

class Worker implements Runnable{

	private static int workers=0;
	final Set<Printer> set;
	
	Worker(final Set<Printer> set){
		this.set = set;
	}
	
	@Override
	public void run() {
		
		workers++;
		System.out.println(workers+" worker is created by "+Thread.currentThread().getName());
		Printer printer = Printer.getPrinter();
		System.out.println("hascode of printer:- "+printer);
		set.add(printer);
	}
	
}

public class Test_Singleton {
	
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		Set<Printer> set = new HashSet<Printer>();
		OutputStream file = new FileOutputStream("abc.txt");
		PrintStream st = System.out;
		PrintStream m = new PrintStream(file);
		System.setOut(m);
		
		System.out.println("main treading waiting............");
		
		ExecutorService e = Executors.newFixedThreadPool(9000);
		for(int i=0;i<9000;i++) {
			e.submit(new Worker(set));
		}
		System.out.println("main treading waiting............");
		e.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println("set size is "+set.size());
		System.out.println("set is "+set);
		System.out.println("main thread end......");
		e.shutdown();
		System.setOut(st);
	}
	
	

}
