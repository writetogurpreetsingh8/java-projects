package copyonwritearraylist;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		
		final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList<>();
		
		copyOnWriteArrayList.add("1");
		//System.out.println(copyOnWriteArrayList.size());
		copyOnWriteArrayList.add("2");
		//System.out.println(copyOnWriteArrayList.size());
		
		Iterator itr = copyOnWriteArrayList.iterator();
		
		
			Thread t = new Thread(new Runnable() {
			
			public void run(){
				int i = 0;
				while(i<10){
					copyOnWriteArrayList.add(i);
					i++;
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
		Thread.sleep(1000);
		while(itr.hasNext()){
			System.out.println("out side "+itr.next());
		}
	}

}
