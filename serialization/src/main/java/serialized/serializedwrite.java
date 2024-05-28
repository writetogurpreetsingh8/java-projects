package serialized;


import java.io.*;

class emp
{
	
	private String name="guru";
	static String job;
	transient static int i=90;
	transient int j=80;
	
	public emp(String name,String job){
			
		this.name=name;
		this.job=job;
		System.out.println("parameterized.... constructor");
		j=200;
	}
	
	emp(){
		System.out.println("default.... constructor");	
	}
	
	void dis(){
		System.out.println("name ==>"+name+" job==>"+job+" i is "+i);
		System.out.println("j is "+j);	
	}
	
	static{
		System.out.println("emp class has been loaded");
		i=100;
	}
		
		
}


class serializedwrite{
	
	public static void main(String args[]){
	
			
		try{
			emp e1 = new emp("money","java-developer");
			System.out.println("e1 is "+e1);	
			System.out.println("serializing objects.......");
			e1.dis();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.obj"));
			out.writeObject(e1);
			System.out.println("serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}
	}

}

