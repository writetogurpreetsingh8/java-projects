package serialized;


import java.io.*;

class emp implements Serializable
{
	
	private String name;
	static String job;
	static int i=90;
	
	public emp(String name,String job){
			
		this.name=name;
		this.job=job;
		
	}
	public void dis(){
		System.out.println("name ==>"+name+" job==>"+job+" i is "+i);
			
	}
	public emp(){
		System.out.println("default constructor");
	}
	static{
		System.out.println("emp class has been loaded");
	}
		
}


class serializedwriteread{
	
	public static void main(String args[]){
	
			
		try{
			emp e1 = new emp("money","java-developer");	
			System.out.println("serializing objects.......");
			e1.dis();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.obj"));
			out.writeObject(e1);
			System.out.println("serialized done successfully\n");	
			
			System.out.println("De-serializing........\n");	
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.obj"));

			Object des = in.readObject();
			
			System.out.println(des);
			emp desemp=(emp)des;
			System.out.println(desemp);
			desemp.dis();
				
			in.close();
			System.out.println("De-serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
}