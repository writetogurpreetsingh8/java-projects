package generic;

class Emp<T extends Number>{
	
	T[] obj;
	
	Emp(T[] obj){
		this.obj = obj;
	}
	
	double avg() {
		double avger=0;
		for (int i=0; i<obj.length; i++) {
			avger+= obj[i].doubleValue();
		}
		return avger/obj.length;
	}
	
	/*
	 * boolean sameAvg(Emp<T> obj) { return (this.avg() == obj.avg()); } // CTE
	 */
	boolean sameAvg(Emp<?> obj) {  // ok
		return (this.avg() == obj.avg());
	}
	
}
public class UnderstandGeneric {

	public static void main(String[] args) {
		Integer nums[] = {1,2,3,4};
		Emp<Integer> l = new Emp<>(nums);
		System.out.println(l.avg());
		
		Double nums1[] = {1.0,2.0,3.0,4.0};
		Emp<Double> d = new Emp<>(nums1);
		
		System.out.println(d.sameAvg(l));
	}

}
