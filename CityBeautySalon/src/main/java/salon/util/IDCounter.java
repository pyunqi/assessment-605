package salon.util;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tool class for generate unique ID
 * 
 * @author yunqi
 *
 */
public class IDCounter {

	private static AtomicInteger idCounter = new AtomicInteger();

	/**
	 * generate next id number for item
	 * 
	 * @return next unique id number for item
	 */
	public static int getNextUniqueIndexID() {
		return idCounter.getAndIncrement();
	}



}
