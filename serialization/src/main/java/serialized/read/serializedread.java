package serialized.read;
import java.io.*;
class serializedread{
	
	public static void main(String args[]){
	
		try{
			System.out.println("De-serializing........\n");	
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.obj"));
			Object des = in.readObject();
			System.out.println(des);
			emp desemp=(emp)des;
			System.out.println(desemp);
			desemp.dis();
			System.out.println("De-serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}
		
	}	
}