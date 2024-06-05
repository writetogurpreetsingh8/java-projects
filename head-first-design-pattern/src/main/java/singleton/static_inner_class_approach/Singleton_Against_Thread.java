package singleton.static_inner_class_approach;


/**
 * Thread safe Singleton class
 */
class Document {

	
	public static Document getDocument() {
		return DocumentSingletonHelper.doc;
	}
	
	private static class DocumentSingletonHelper {
		private final static Document doc = new Document();
	}
	
	private Document() {}
}

public class Singleton_Against_Thread {

	public static void main(String[] args) {
		System.out.println(Document.getDocument());

	}

}
