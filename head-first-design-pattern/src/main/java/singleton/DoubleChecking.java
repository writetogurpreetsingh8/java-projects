package singleton;


class MySingleton{

	private static volatile MySingleton obj;
	
	private MySingleton(){}
	
	public static MySingleton getInstance(){
		
		if(obj == null){
			synchronized(MySingleton.class){
				if(obj == null){
					obj = new MySingleton();
				}
			}
		}
		return obj;
	}
}

public class DoubleChecking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(MySingleton.getInstance());
	}

}
