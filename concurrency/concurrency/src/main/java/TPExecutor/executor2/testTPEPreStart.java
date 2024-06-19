package TPExecutor.executor2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class testTPEPreStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedBlockingQueue l = new LinkedBlockingQueue<>();
		l.add(new Threadd());l.add(new Threadd());l.add(new Threadd());l.add(new Threadd());
		ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, l);
		System.out.println(tp.prestartCoreThread());

	}

}
