package serialization;
import java.io.*;


class serializedwithFileRead{
	
	public static void main(String args[]){
	
		try{
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.obj"));
			Object des = in.readObject();
			System.out.println(des);
			emp desemp=(emp)des;
			System.out.println(desemp);
			desemp.dis();
			System.out.println("De-serialized done successfully\n");	
			System.out.println("getting data from file......... ");
			System.out.println("file name is "+desemp.file);
			
			FileOutputStream fos = new FileOutputStream(desemp.file);
			//fos.write("data from file out put stram /n and it will be store into file object /n and try to find out serailzed object into file ".getBytes());
			fos.flush();
			fos.close();
			
			/*FileInputStream fis =new FileInputStream(desemp.file);
			
			while(true){
				int ch=fis.read();
				if(ch==-1)
				break;
				System.out.print((char)ch);
			}*/
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}	
}