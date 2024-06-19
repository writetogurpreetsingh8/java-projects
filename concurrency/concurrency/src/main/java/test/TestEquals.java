package test;

class a{
	
	void show(a i){
		System.out.println("a class ");
	}
}

class b extends a{
	
	void show(b k){
		System.out.println("b class");
	}
}

public class TestEquals {

	
	
	
	public static void main(String[] args) {
		a obja = new a();
		b objb = new b();
		
		objb.show(obja);

	}

}
