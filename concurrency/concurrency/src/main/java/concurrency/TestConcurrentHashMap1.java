package concurrency;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TestConcurrentHashMap1{
	
	public static void main(String...strings){
	
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(1,1);
		
		map.put("1", "1");
		map.put("2", "2");
		Thread t  = new Thread(){
			@Override
			public void run(){
				Set<Map.Entry<String,String>> s = map.entrySet();
				Iterator<Entry<String,String>> itr = s.iterator();
				map.put("3", "3");
				while(itr.hasNext()){
					Entry<String,String> entry = itr.next();
					System.out.println(entry.getKey()+" = "+entry.getValue());
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		System.out.println("main threading sleeping for 2 sec....");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println("main threading awaking..");
		map.remove("2");
		map.put("4", "4");
		System.out.println("map "+map);
		System.err.println("new map/............");
/*	map.forEach( (key,value) ->{
		System.out.println(key +" = "+value);
	});*/
}
}