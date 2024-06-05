package singleton.static_inner_class_approach;


/**
 * Thread safe Singleton class
 */
class Socket implements Cloneable {
	
	public static Socket getSocket() {
		return SocketSingletonHelper.soc;
	}
	
	private static class SocketSingletonHelper {
		private final static Socket soc = new Socket();
	}
	
	private Socket() {}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton class doesn't support cloning...");
	}
}

public class Singleton_Against_Thread_Clone {

	public static void main(String[] args) throws CloneNotSupportedException {
		Socket s = Socket.getSocket();
		System.out.println(s);
		System.out.println(Socket.getSocket());
		s.clone();

	}

}
