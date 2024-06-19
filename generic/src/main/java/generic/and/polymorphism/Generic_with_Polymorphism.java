package generic.and.polymorphism;

import java.util.ArrayList;
import java.util.List;


class Animal{
	
}

class Cat extends Animal{
	
}

class Dog extends Animal{
	
}

class petdog extends Dog{
	
}

class parent{
	
}

class child extends parent{
	
}

public class Generic_with_Polymorphism {

	public static void main(String[] args) {
		parent[] p = new child[2];
		p[0] = new child();
		//p[1] = new parent();
		
		Animal[] ani = new Dog[10];
		ani[0] = new Animal();
		ani[1] = new Cat();
		ani[2] = new Dog();
		addAnimals1(ani);
		
		List<Animal> list = new ArrayList<Animal>();
		list.add(new Dog());
		list.add(new Dog());
		
		addAnimals(list);
		
		/**
		 * can accept/assign any list with generic type of Dog or supertype
		 * of Dog, nothing lower than dog in the inheritance tree can accept/assign here
		 * but anything higher than dog (including dog) is ok
		 */
		List<? super Dog> list1 = new ArrayList<Animal>();
		list1.add(new Dog());
		list1.add(new petdog());
		//list1.add(new Animal());
		//list1.add(new Object());

	//	List<? super Animal> list2 = new ArrayList<Dog>();
		
		//List<? extends Dog> list3 = new ArrayList<Animal>();

		List<? extends Animal> list4 = new ArrayList<Dog>();


	}
	
	
	private static void addAnimals1(Animal[] an) {
		an[0] = new Cat(); 
	}
	
	private static void addAnimals(List<? super Dog> an) {
		an.add(new Dog());
		an.add(new petdog());
		//an.add(new Animal());
	}

}
