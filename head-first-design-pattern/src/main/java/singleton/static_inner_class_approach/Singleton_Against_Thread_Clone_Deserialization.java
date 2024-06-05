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
 * this mechanism only provide protection against thread + clone + deserialization
 */

class Protocol1 implements Cloneable, Serializable {
	
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

	private Protocol1() {}
	
	private static class ProtocolSingletonHelper{
		private final static Protocol1 pro = new Protocol1();
	}
	
	public static Protocol1 getProtocol() {
		return ProtocolSingletonHelper.pro;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton class doesn't support cloning...");
	}
	
	/**
	 * this method will be called by JRE at the time of object deserialization and at the time only
	 * we just raise the exception to protect our singleton class to be deserialized 
	 * even though ObjectInputStream try to deserialized all the data members of class
	 * but while deserializing their value we raised the exception due deserialization got failed
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		throw new IllegalStateException("Singleton class doesn't support Deserializability...");
	}
	
}

public class Singleton_Against_Thread_Clone_Deserialization {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Protocol1 p = Protocol1.getProtocol();
		System.out.println(p);
		
		// try to serialized singleton class
		FileOutputStream f = new FileOutputStream("protocol.ser");
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(p);
		System.out.println("result...");
	
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("protocol.ser"));
		System.out.println(i.readObject());
	}

}
