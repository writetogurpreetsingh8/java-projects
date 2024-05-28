package custom.serialization;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestControlSerialization {

	public static void main(String[] args) {
		MyClass myObject = new MyClass("John Doe",30,"US","Money");

        try (CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("myObject.ser"))) {
            out.writeObject(myObject);
            System.out.println("Serialization done successfully........");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
