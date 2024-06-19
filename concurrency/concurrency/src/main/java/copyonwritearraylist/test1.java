package copyonwritearraylist;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		
		final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList<>();
		copyOnWriteArrayList.add("1");
		//System.out.println(copyOnWriteArrayList.size());
		copyOnWriteArrayList.add("2");
		//System.out.println(copyOnWriteArrayList.size());
		
Thread t = new Thread(new Runnable() {
			
			public void run(){
				int i = 2;
				while(i<10){
					System.out.println("setting ...........");
					copyOnWriteArrayList.set((i-1),(i+1));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("start child thread and removing elements......");
				Iterator itr1 = copyOnWriteArrayList.iterator();
				while(itr1.hasNext()){
					System.out.println("in side "+itr1.next());
				}
			}
		});
		t.start();
		Thread.sleep(100);
		while(true){
			System.out.println("adding..................");
			copyOnWriteArrayList.add("2");
		}
	}

}
