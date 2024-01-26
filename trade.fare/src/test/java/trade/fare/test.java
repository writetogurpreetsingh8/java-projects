package trade.fare;

import java.util.HashMap;
import java.util.Map;

import com.trade.fare.bean.TradeBean;

class testmap extends HashMap<String, String>{

	
	  static int hash(Object key) {
		  System.out.println("hash code...........");
	        int h;
	        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	    }
}

class emptest{
	
	String a;
	
	@Override
	 public int hashCode() {
		int h=0;
	        if (a.length() > 0) {
	            char val[] = a.toCharArray();

	            for (int i = 0; i < val.length; i++) {
	                h = 31 * h + val[i];
	            }
	        }
	        return h;
	    }
}
public class test {

	
	public static void main(String[] args) {

		
		  Map<TradeBean, String> map = new HashMap<TradeBean, String>();
		  
		  TradeBean tb = new TradeBean(); tb.setFirstParty("fp-a");
		  tb.setSecondParty("sp-b");
		  
		  map.put(tb, "fp-a , sp-b"); System.out.println(map);
		  
		  TradeBean tb1 = new TradeBean(); tb1.setFirstParty("fp-a");
		  tb1.setSecondParty("sp-b");
		  
		  map.put(tb1, "fp-a , sp-b"); System.out.println(map);
		  
		  TradeBean tb2 = new TradeBean(); tb2.setFirstParty("sp-b");
		  tb2.setSecondParty("fp-a");
		  
		  map.put(tb2, "sp-b , fp-a"); System.out.println(map);
		 
		  int c = "abc".hashCode();
		  System.out.println("c "+c);
		  c = "cba".hashCode();
		  System.out.println("c "+c);
		  
		  testmap m = new testmap();
		  m.put("df","df");
		  m.put("gd", "gh");
		  System.out.println(m);

	}

}
