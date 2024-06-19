package cowl;

import java.util.Iterator;

public class CopyOnWriteArrayList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		java.util.concurrent.CopyOnWriteArrayList list = new java.util.concurrent.CopyOnWriteArrayList<>();
		
		list.add("1");list.add("2");list.add("3");
		list.add("4");list.add("5");list.add("6");
		
		System.out.println(list);
		
		System.out.println(list.size());
		Iterator itr = list.iterator();
		list.add("after itr");
		System.out.println("if absent "+list.addIfAbsent("1"));
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		System.out.println(list);
	}

}
