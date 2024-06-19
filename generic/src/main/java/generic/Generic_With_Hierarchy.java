package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class gp {

}

class parent extends gp {

}

class child extends parent {

}

class Animal {

}

class Dog extends Animal {

}

class MySuperclass<T> {

	T obj;

	MySuperclass(T obj) {
		this.obj = obj;
	}

	T display() {
		System.out.println("");
		return obj;
	}

	<T extends gp> void add(List<T> list) {

	}
}

class MyClass<L extends Dog> extends MySuperclass<Animal> {

	L rr;

	MyClass(L rr) {
		super(rr);
		this.rr = rr;
	}

	@Override
	L display() {
		return (L) super.display();
	}
}

public class Generic_With_Hierarchy {

	static <X> void add(List<X extends gp> list) {
		
	}

	<X extends gp> void add1(List<X> list) {

	}

	void add4(List<X extends gp> list) {
		
	}

	void add2(List<? super gp> list) {

	}

	void add0(List<? extends gp> list) {

	}

	// While parent and child inherit from gp, the compiler doesn't know that at
	// compile time due to type erasure in generics.
	
	// In the add3 method, you have List<L> list, where L is a generic type
	// extending gp. This means the list can only hold objects of type L (which must
	// be a subclass of gp).
	
	// The line list.add(new child()) attempts to add a child object, but the
	// compiler doesn't know for sure if child is a subclass of the unknown generic
	// type L at compile time.
	static <L extends gp> void add3(List<L> list) {
		list.add(new child());
	}// convet this method into below type
	
	static void add30(List<? super gp> list) {
		list.add(new child());
	}

	public static void main(String[] args) {
		MyClass<Dog> k = new MyClass<>(new Dog());
		System.out.println(k.display());

		// MyClass<Integer>[] kk = new MyClass<Integer>[2]; //error
		MyClass<?>[] kk1 = new MyClass<?>[2];

		MySuperclass<?>[] k1 = new MySuperclass<?>[2];
		// MySuperclass<Integer>[] k2 = new MySuperclass<Integer>[1];

		k1[0] = new MySuperclass<Integer>(12);
		k1[1] = new MySuperclass<String>("df");

		List<gp> list = new ArrayList<gp>();
		list.add(new parent());
		list.add(new child());
		add3(list);
	}

}
