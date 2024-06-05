package singleton.enum_approach;

public enum Printer {
	
	INSTANCE;
	
	Printer(){
		
	}
	
	public void display() {
		System.out.println("display.........");
	}
	
	public static void display1() {
		System.out.println("display.........");
	}
	
	public static void show() {
		System.out.println("show.........");
	}
}
