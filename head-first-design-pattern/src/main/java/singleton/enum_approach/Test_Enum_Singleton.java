package singleton.enum_approach;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test_Enum_Singleton {

	public static void main(String[] args) {
		
		Printer p = Printer.INSTANCE;
			System.out.println(Printer.INSTANCE.hashCode());
			Printer.display1();
			Printer.INSTANCE.display();
			System.out.println(Printer.INSTANCE.ordinal());
			 // Serialize SingletonEnum
	        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("enum.ser"))) {
	            out.writeObject(Printer.INSTANCE);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Deserialize SingletonEnum
	        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("enum.ser"))) {
	        	Printer deserialized = (Printer) in.readObject();
	            deserialized.display(); // Successfully calls doSomething() method
	            System.out.println(deserialized.hashCode());
				System.out.println(deserialized.ordinal());
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        try {
	            Constructor<Printer> constructor = Printer.class.getDeclaredConstructor(String.class);
	            constructor.setAccessible(true);
	            Printer instance = constructor.newInstance(); // IllegalAccessException: Cannot reflectively create enum objects
	        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
	            e.printStackTrace();
	        }
	}

}
