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

class Protocol2 implements Cloneable, Serializable {
	
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

	private Protocol2() {}
	
	private static class ProtocolSingletonHelper{
		private final static Protocol2 pro = new Protocol2();
	}
	
	public static Protocol2 getProtocol() {
		return ProtocolSingletonHelper.pro;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton class doesn't support cloning...");
	}
	
	// Preventing singleton from deserialization
    protected Object readResolve() {
        return getProtocol();
    }
    
}

public class Singleton_Against_Thread_Clone_Deserialization1 {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Protocol2 p = Protocol2.getProtocol();
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
