package generic;

class Trainer{

	private double salary;
	
	public <T extends Double> Trainer(T salary) {
		this.salary = salary;
	}
	
	public double getSalary() {
		return this.salary;
	}
}

public class GenericConstructor_In_Non_Generic_Class {

	public static void main(String[] args) {
		Trainer tr = new Trainer(12.0);
		System.out.println(tr.getSalary());
	}

}
