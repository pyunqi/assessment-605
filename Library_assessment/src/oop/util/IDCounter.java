package oop.util;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tool class for generate unique ID
 * 
 * @author yunqi
 *
 */
public class IDCounter {

	private static AtomicInteger idCounter = new AtomicInteger();
	private static AtomicInteger pCounter = new AtomicInteger();

	/**
	 * generate next id number for item
	 * 
	 * @return next unique id number for item
	 */
	public static int getNextUniqueIndexID() {
		return idCounter.getAndIncrement();
	}

	/**
	 * generate next id number for patron
	 * 
	 * @return next unique id number for patron
	 */
	public static int getNextUniqueIndexP() {
		return pCounter.getAndIncrement();
	}

}
