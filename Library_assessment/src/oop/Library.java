package oop;
import java.util.HashMap;
import java.util.List;

import oop.items.Book;
import oop.items.DVD;
import oop.items.Item;
import oop.items.Video;
import oop.patron.Graduate;
import oop.patron.Lecturer;
import oop.patron.Patron;
import oop.patron.UnGraduate;
import oop.patron.UnKnown;

/**
 * major Class for Library
 * 
 * @author yunqi
 *
 */
public class Library {

	private static List<Item> stockItems; // store all items
	private static HashMap<Item, Patron> loanedItems; // mapping of patron and loaned item relationships
	private static List<Patron> patrons; // store all patrons

	/**
	 * initial Library by patrons and items which include Books,DVDs,Videos,Patrons
	 * 
	 * @param items
	 * @param patrons
	 */
	public Library(List<Item> items, List<Patron> patrons) {
		super();
		// save items
		Library.stockItems = items;
		// now we have few customers here
		Library.patrons = patrons;
		// now we can loan item
		loanedItems = new HashMap<Item, Patron>();
	}

	/**
	 * get all patrons
	 * 
	 * @return all patron's objects
	 */
	public static List<Patron> getPatrons() {
		return patrons;
	}

	/**
	 * add item to stock
	 * @param item
	 */
	public void addToLibarary(Item item) {
		stockItems.add(item);
	}

	/**
	 * show all data
	 */
	public void listAll() {
		for (Item item : stockItems) {
			System.out.println(item.toString());
		}

	}

	/**
	 * list all patrons and print them
	 */
	public void listPatrons() {
		for (Patron patron : patrons) {
			if (patron instanceof Lecturer) {
				System.out.println((Lecturer) patron);
			} else if (patron instanceof Graduate) {
				System.out.println((Graduate) patron);
			} else if (patron instanceof UnGraduate) {
				System.out.println((UnGraduate) patron);
			} else {
				System.out.println((UnKnown) patron);
			}

		}

	}

	/**
	 * print all books' infomation
	 */
	public void listBooks() {

		for (Item item : stockItems) {
			if (item instanceof Book) {
				System.out.println(item.toString());
			}
		}
	}

	/**
	 * print all DVDs' information
	 */
	public void listDVDs() {

		for (Item item : stockItems) {
			if (item instanceof DVD) {
				System.out.println(item.toString());
			}
		}

	}

	/**
	 * list all videos' information
	 */
	public void listVideos() {

		for (Item item : stockItems) {
			if (item instanceof Video) {
				System.out.println(item.toString());
			}
		}

	}

	/**
	 * show all loans relationship
	 */
	public void listLoanDetails() {
		for (Item item : loanedItems.keySet()) {
			System.out.println(
					item.getClass().getName() + ",Title :" + item.getTitle() + " and the ID is : " + item.getItemId());
			Patron patron = loanedItems.get(item);
			System.out.println(" loan by " + patron.getName() + " and this patron Id is: " + patron.getId());
		}
		if (loanedItems.isEmpty()) {
			System.out.println("No loans information.");
		}
	}

	/**
	 * add a patron
	 * 
	 * @param patron
	 */
	public static void addPatron(Patron patron) {
		patrons.add(patron);
	}

	/**
	 * add an item to stock
	 * 
	 * @return all items in stock
	 */
	public static List<Item> getStockItems() {
		return stockItems;
	}

	/**
	 * get all loaned mapping which show item and patron relationship
	 * 
	 * @return relationship mapping
	 */
	public static HashMap<Item, Patron> getLoanedItems() {
		return loanedItems;
	}

}
