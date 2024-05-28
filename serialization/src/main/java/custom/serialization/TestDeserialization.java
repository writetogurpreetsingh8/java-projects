package custom.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestDeserialization {

	public static void main(String[] args) {
		MyClass myObject;

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("myObject.ser"))) {
			myObject = (MyClass) in.readObject();
			System.out.println("Name: " + myObject.getName());
			System.out.println("Age: " + myObject.getAge());
			System.out.println("Address: " + myObject.getAddress());
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("exception ocurred!!....");
			e.printStackTrace();

		}

	}

}
