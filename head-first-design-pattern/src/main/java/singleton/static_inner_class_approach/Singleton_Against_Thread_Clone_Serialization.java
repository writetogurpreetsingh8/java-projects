package singleton.static_inner_class_approach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * this mechanism only provide protection against thread + clone + serialization
 * but not against deserialization
 */

class Protocol implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private  int age;
	private String address;
	private String nickName;
	private String loginid; 
	private int password; 
	private String location;
	private String nickname;	
	private String partyName;
	private String agentName;
	private List list;
	private List list1 = Arrays.asList("dfdfdf","dfdfdfdf","dfdfdf");;

	private Protocol() {}
	
	private static class ProtocolSingletonHelper{
		private final static Protocol pro = new Protocol();
	}
	
	public static Protocol getProtocol() {
		return ProtocolSingletonHelper.pro;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton class doesn't support cloning...");
	}
	
	/**
	 * this method will be called by JRE at the time of object serialization and at the time only
	 * we just raise the exception to protect our singleton class to be Serialized 
	 * even though ObjectOutputStream still create the output file and store all the data members of class
	 * into it but without their values because while serializing their value we raised the exception due to
	 * this files didn't get successfully writes and at the time of Deserialization some one will get EOF Exception
	 */
	private void writeObject(ObjectOutputStream oos)throws IOException {
		// raise any good and relevant exception
		throw new IllegalStateException("Singleton class doesn't support Serializability...");
	}
}
public class Singleton_Against_Thread_Clone_Serialization {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Protocol p = Protocol.getProtocol();
		System.out.println(p);
		// try to serialized singleton class
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream("protocol.ser");
			o = new ObjectOutputStream(f);
			o.writeObject(p);
			System.out.println("result...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			f.flush();
			f.close();
			o.flush();
			o.close();
		} 
		
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("protocol.ser"));
		System.out.println(i.readObject());
	}

}
