package custom.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class CustomObjectInputStream extends ObjectInputStream{
	
	 private final ClassLoader classLoader;
	public CustomObjectInputStream(InputStream in, ClassLoader classLoade) throws IOException {
		super(in);
		this.classLoader = classLoade;
		enableResolveObject(true);
	}
	
	@Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String className = desc.getName();
        //className = className.substring(className.lastIndexOf(".")+1);
        if(className.equalsIgnoreCase("MyClass")) {
        	//throw new IllegalAccessError("class is not in whitelisting box...");
        }
        return Class.forName(className, false, classLoader);
    }
	
	 @Override
	   protected Object resolveObject(Object obj) throws IOException {
	        if (obj instanceof MyClass) {
	        	MyClass oldPerson = (MyClass) obj;
	            // If the deserialized object is from the old version, convert it to the new version
	           // if (oldPerson.getClass().getDeclaredFields().length == 2) {
	        	MyClass o = new MyClass();
	        	o.setName(oldPerson.getName());
	        	o.setAddress(oldPerson.getAddress());
	           // }
	        	return o;
	        }
	        return obj;
	    }

}
