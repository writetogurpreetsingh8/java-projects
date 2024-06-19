package generic;

class Department{

	public <T extends Comparable<T>> T min(T[] arr) {
		
		T smallest=arr[0];
		
		for(int i=1;i<arr.length;i++) {
			if(!(arr[i].compareTo(smallest) > 0)) {
				smallest = arr[i];
			}
		}
		
		return smallest;
	}
}

public class GenericMethod_In_Non_Generic_Class {

	public static void main(String[] args) {
		Integer nums[] = {1,2,3,4,0,-3,-2};
		Department l = new Department();
		System.out.println(l.min(nums));	
	}

}
