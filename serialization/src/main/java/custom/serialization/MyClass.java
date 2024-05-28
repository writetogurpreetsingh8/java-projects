package custom.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MyClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private  int age;
	private String address;
	private String nickName;
	private String loginid; // here can be initialized
	private int password; // here can be initialized
private String location;
private String nickname;	
private String partyName;
private String agentName;
private List list;
private List list1 = Arrays.asList("dfdfdf","dfdfdfdf","dfdfdf");;

	public MyClass(String name, int age, String adderss,String nickName) {
		System.out.println("parameterized constructor myclass...");
		this.name = name;
		this.age = age;
		this.address = adderss;
		this.nickName = nickName;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Custom serialization method

	private void writeObject(ObjectOutputStream out) throws IOException {
	  out.defaultWriteObject(); // Serialize the default fields  
	  // Serialize the new field 
		//out.writeBytes(address);
	 }
	 

	// Custom deserialization method
//
	private void readObject(ObjectInputStream in) throws IOException,
	  ClassNotFoundException { 
		in.defaultReadObject(); // Deserialize the default fields
	}
	  
	MyClass() {
		System.out.println("default constructor myclass...");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNick() {
		return nickName;
	}

	public void setNick(String nick) {
		this.nickName = nick;
	}

	static {
		System.out.println("static block myclass...");
	}
}
