package singleton.static_inner_class_approach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * this mechanism only provide protection against thread + clone + deserialization
 */

class MotherBoard implements Cloneable, Serializable {
	
	private final Object config;
	private MotherBoard() {
		if(getMotherBoard() != null) {
			throw new IllegalStateException("Already instantiated");
		}
		config = new Object();
	}
	
	private static class MotherBoardSingletonHelper{
		private final static MotherBoard mo = new MotherBoard();
	}
	
	public static MotherBoard getMotherBoard() {
		return MotherBoardSingletonHelper.mo;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton class doesn't support cloning...");
	}
	
	// Preventing singleton from serialization and deserialization
    protected Object readResolve() {
        return getMotherBoard();
    }
    
 // Ensure integrity via reflection
    public static void preventReflectionModification() {
        try {
            Field configField = MotherBoard.class.getDeclaredField("config");
            configField.setAccessible(true);
            MotherBoard instance = MotherBoard.getMotherBoard();
            Object originalConfig = instance.config;
            if (configField.get(instance) != originalConfig) {
                throw new IllegalStateException("Illegal modification attempt detected");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
}

public class Singleton_Against_Thread_Clone_Deserialization_Reflection {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException {
		
		MotherBoard p = MotherBoard.getMotherBoard();
		System.out.println(p);
		
		
		  // try to serialized singleton class 
		FileOutputStream f = new
		  FileOutputStream("protocol.ser"); ObjectOutputStream o = new
		  ObjectOutputStream(f); o.writeObject(p); System.out.println("result...");
		  
		  ObjectInputStream i = new ObjectInputStream(new
		  FileInputStream("protocol.ser")); System.out.println(i.readObject());
		 
		
        Constructor c[] = p.getClass().getDeclaredConstructors();
		
		MotherBoard m = null;
		  for(Constructor r : c)
		  { 
			  r.setAccessible(true); 
			    m =   (MotherBoard)r.newInstance(p); 
		}
		 
		System.out.println(m);
	}

}

