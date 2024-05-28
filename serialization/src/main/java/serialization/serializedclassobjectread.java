package serialization;

import java.io.*;


class serializedclassobjectread{
	
	public static void main(String args[]){
	
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.obj"));
			Object des = in.readObject();
			System.out.println("des is "+des);

			
			Class classdesemp=(Class)des;
			System.out.println("classdesemp is "+classdesemp);
			
			 
			System.out.println("De-serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}
		
	}	
}