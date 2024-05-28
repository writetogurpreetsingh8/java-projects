package custom.serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomObjectOutputStream extends ObjectOutputStream {

	public CustomObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		enableReplaceObject(true);
	}
	
	 @Override
	    protected Object replaceObject(Object obj) throws IOException {
	        if (obj instanceof MyClass) {
	        	MyClass largeObject = (MyClass) obj;
	        	MyClass o = new MyClass();
	        	o.setName(largeObject.getName());
	        	o.setAddress(largeObject.getAddress());
	            return o;
	        }
	        return super.replaceObject(obj);
	    }
}
