package singleton;


class Emp implements Cloneable{
	
	private String name;
	private int age;
	
	
	public Emp(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	protected Emp clone() throws CloneNotSupportedException{
		return (Emp) super.clone();
	}
	
}
public class MakeClassClone {

	public static void main(String[] args) throws CloneNotSupportedException {
		Emp e = new Emp("Gurupreet", 38);
		Emp e1 = e.clone();
		System.out.println(e1);
	}

}
