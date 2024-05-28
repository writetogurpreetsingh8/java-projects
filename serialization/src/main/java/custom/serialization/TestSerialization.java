package custom.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestSerialization {

	public static void main(String[] args) {
		MyClass myObject = new MyClass();
		myObject.setAddress("New delhi");

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myObject.ser"))) {
            out.writeObject(myObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
