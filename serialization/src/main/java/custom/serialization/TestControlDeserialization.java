package custom.serialization;

import java.io.FileInputStream;
import java.io.IOException;

public class TestControlDeserialization {

	public static void main(String[] args) {
		MyClass myObject;

		try (CustomObjectInputStream in = new CustomObjectInputStream(new FileInputStream("myObject.ser"),
				CustomObjectInputStream.class.getClassLoader())) {
			myObject = (MyClass) in.readObject();
			System.out.println("Name: " + myObject.getName());
			System.out.println("Age: " + myObject.getAge());
			System.out.println("Address: " + myObject.getAddress());
			System.out.println("Nick Name: " + myObject.getNick());
            System.out.println("Deserialized object: " + myObject);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("exception ocurred!!....");
			e.printStackTrace();

		}

	}

}
