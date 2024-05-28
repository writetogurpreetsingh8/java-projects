package serialization;
import java.io.*;

class emp implements Serializable{
	
	private String name,job;
		
	String ref = new String("reference variable of string");

	//transient a obj=new a();

	public emp(String name,String job){
	
		this.name=name;
		this.job=job;
		System.out.println("parameterized construtor of emp class..........");
			
	}
	public void dis(){
		System.out.println("name ==>"+name+" job==>"+job);
		System.out.println("ref is " +ref);
		//System.out.println("a is " +obj);
	}

	static{
		System.out.println("emp class has loaded");
	}
	emp(){
		System.out.println("default construtor of emp class..........");
	}	
}

class a implements Serializable { 
	
	String gur;
	
	public a(){
		gur="money$honey";
		System.out.println("default construtor of a class..........");
	}
	public void dis(){
		
		System.out.println("gur ==>"+gur);
	}
	static{
		System.out.println("a class has loaded");
	}		
}

class serializedclassobjectwrite{
	
	public static void main(String args[]){
	
		try{
			emp e1 = new emp("money","java-developer");	
			
			System.out.println("serializing objects.......");
			e1.dis();
			System.out.println(e1);
			System.out.println(emp.class);
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.obj"));
			out.writeObject(emp.class);
			System.out.println("serialized done successfully\n");	
		
				
		}catch(Exception e){
			System.out.println(e);
		}
		
	}	
}