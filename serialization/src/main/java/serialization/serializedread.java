package serialization;
import java.io.*;


class serializedread{
	
	public static void main(String args[]){
	
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.obj"));
			Object des = in.readObject();
			System.out.println(des);

			emp desemp=(emp)des;
			System.out.println(desemp);
			desemp.dis();
			System.out.println("desemp.obj is "+desemp.obj);
			desemp.obj.dis(); 
			System.out.println("De-serialized done successfully\n");
			des = in.readObject();	
		}catch(Exception e){
			System.out.println(e);
		}
		
	}	
}