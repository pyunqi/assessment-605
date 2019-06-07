package oop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import oop.items.Book;
import oop.items.DVD;
import oop.items.Item;
import oop.items.Video;
import oop.patron.Graduate;
import oop.patron.Lecturer;
import oop.patron.Patron;
import oop.patron.UnGraduate;
import oop.patron.UnKnown;
import oop.util.IDCounter;
import oop.util.ItemHelper;

/**
 * Entry class for initializing and running Library
 * 
 * @author yunqi
 *
 */

public class TestLibrary extends BaseLibrary {

	private static Scanner scanner; // using for user input commands and information

	/**
	 * The entry
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// init Lib
		Library library = initLibrary();
		Patron patron = new Patron();
		Item item = new Item();
		// init stock list and loaned map
		stockItems = Library.getStockItems();
		loanedItems = Library.getLoanedItems();

		System.out.println("Welcome to use our Library !");
		scanner = new Scanner(System.in);

		boolean run = true;
		while (run) {
			String command = scanner.next().toUpperCase();
			switch (command) {
			case "LISTALL":
				System.out.println("Show stock!");
				for (Item sItem : stockItems) {
					System.out.println(sItem.toString());
				}
				break;
			case "PATRONS":
				System.out.println("List all Patrons!");
				library.listPatrons();
				break;
			case "BOOKS":
				System.out.println("List all Books we had!");
				library.listBooks();
				break;
			case "DVDS":
				System.out.println("List all DVDs we had!");
				library.listDVDs();
				break;
			case "VIDEOS":
				System.out.println("List all Videos we had!");
				library.listVideos();
				break;
			case "LISTLOAN":
				System.out.println("List all loaned infomation!");
				library.listLoanDetails();
				break;
			case "ADDP":
				System.out.println("Add a new Patron!");
				System.out.println("please enter Patron's name: ");
				String name = scanner.next();
				System.out.println("please enter Patron's address: ");
				String address = scanner.next();// should do multiple lines input, but now only support input line
												// without spaces
				System.out.println("please enter Patron's Tel number: ");
				String telNumber = scanner.next();
				System.out.println("please enter Patron's profession: ");
				String input = scanner.next();
				switch (input.toUpperCase().charAt(0)) {
				case 'L':
					patron = new Lecturer(name, address, telNumber);
					break;
				case 'G':
					patron = new Graduate(name, address, telNumber);
					break;
				case 'U':
					patron = new UnGraduate(name, address, telNumber);
					break;
				default:
					patron = new UnKnown(name, address, telNumber);
					break;
				}
				Library.addPatron(patron);
				System.out.println("Thanks! Patron:" + patron.toString() + "has been added!!");
				break;
			case "ADDB":
				// same book have many copies in library
				System.out.println("Thanks! Book:" + addToLibrary(library, ItemHelper.createBook(scanner)).toString()
						+ "has been added!!");
				break;
			case "ADDD":
				System.out.println("Thanks! DVD:" +addToLibrary(library,ItemHelper.createDVD(scanner)).toString() + "has been added!!");
				break;
			case "ADDV":
				System.out.println("Thanks! Video:" + addToLibrary(library,ItemHelper.createVideo(scanner)).toString() + "has been added!!");
				break;
			case "LOANBOOK":
				// who load which book
				System.out.println("Loan Book!");
				System.out.println("please enter book's ID number or enter book's Title: ");
				String bookInfo = scanner.next();
				item = findItem(bookInfo, stockItems, AVAILABLE);
				if (item != null && item instanceof Book) {
					loan2patron(item);
				} else {
					System.out.println("There is no such book or not available. please try again!");
				}
				break;
			case "LOANVIDEO":
				// who loan which Video
				System.out.println("Loan Video!");
				System.out.println("please enter video's ID number or enter video's Title: ");
				String videoInfo = scanner.next();

				item = findItem(videoInfo, stockItems, AVAILABLE);
				if (item != null && item instanceof Video) {
					// lend to patron and need to know who is he/she
					loan2patron(item);
				} else {
					System.out.println("There is no such video. please try again!");
				}
				break;
			case "LOANDVD":
				// who load which DVD
				System.out.println("Loan DVD!");
				System.out.println("please enter DVD's ID number or enter DVD's Title: ");
				String dvdInfo = scanner.next();
				item = findItem(dvdInfo, stockItems, AVAILABLE);
				if (item != null && item instanceof DVD) {
					// lend to patron and need to know who is he/she
					loan2patron(item);
				} else {
					System.out.println("There is no such DVD. please try again!");
				}
				break;
			case "RETURN":
				System.out.println("Return a Item !");
				System.out.println("Please enter item id which you want to return");
				String iteminfo = scanner.next();
				List<Item> tempList = new ArrayList<Item>(loanedItems.keySet());
				item = findItem(iteminfo, tempList, RENTED);
				if (item != null) {
					System.out.println("Please enter item Status which you want to return: ");
					System.out.println("A = Available, D = Damaged, Others = Damaged");
					returnItem(item, scanner.next().toUpperCase().charAt(0));
				} else {
					System.out.println("There is no such item can be returned");
				}
				break;
			case "QUIT":
				System.out.println("Good Bye!");
				run = false;
				break;
			default:
				// commands
				System.out.println("Currently, we have following commands : \r" + "listAll, listloan, patrons, books,"
						+ " videos, dvds, addP, addB, addD, loanBook, loanVideo, loanDvd,return quit ");
				break;

			}
		}

	}

	/**
	 * loan an item to patron
	 * 
	 * @param item
	 */
	private static void loan2patron(Item item) {
		System.out.println("Plase enter patron's ID or Name(in this scenarios it will not repeat): ");
		String patronInfo = scanner.next();
		item.setStatus(RENTED);
		Library.getLoanedItems().put(item, findPatron(patronInfo));
		Library.getStockItems().remove(item);
		System.out.println("Loan " + item.getClass().getName() + " to " + findPatron(patronInfo).getName());
	}

	/**
	 * add item to library
	 * @param library
	 * @param item
	 * @return
	 */
	private static Item addToLibrary(Library library, Item item) {
		System.out.println("please enter how many copies this time: ");
		int copies = scanner.nextInt();
		for (int i = 0; i < copies; i++) {
			Item tempItem = (Item) item.clone();
			tempItem.setItemId(IDCounter.getNextUniqueIndexID());
			library.addToLibarary(tempItem);
		}
		return item;
	}

	/**
	 * Initialize Library by creating some original data manually
	 * 
	 * @return created Library object
	 */
	private static Library initLibrary() {
		// create items
		List<Item> items = new ArrayList<Item>();
		// define a book
		Book book = new Book(ItemHelper.tolist("Sam,Tom,Mike"), "Famouse Publisher", "12-32-3445-232-22", "3th");
		book.setDueDate(new Date());
		book.setItemId(IDCounter.getNextUniqueIndexID());
		book.setStatus(AVAILABLE);
		book.setTitle("A fan book");
		items.add(book);

		// define a video
		Video video = new Video("Golden Producer", 50, 120);
		video.setDueDate(new Date());
		video.setItemId(IDCounter.getNextUniqueIndexID());
		video.setStatus(AVAILABLE);
		video.setTitle("A great movie");
		items.add(video);

		// define a DVD
		DVD dvd = new DVD("1997", 50, ItemHelper.tolist("Jack,Patrick,Jane"));
		dvd.setDueDate(new Date());
		dvd.setItemId(IDCounter.getNextUniqueIndexID());
		dvd.setTitle("Good DVD");
		dvd.setStatus(AVAILABLE);
		items.add(dvd);

		// define three patrons
		List<Patron> patrons = new ArrayList<Patron>();
		Lecturer lpatron = new Lecturer("Patrick", "Grey lynn, Ponsonby", "20210287623");
		patrons.add(lpatron);
		Graduate gpatron = new Graduate("Batman", "City centre, NY", "2314614769");
		patrons.add(gpatron);
		UnGraduate upatron = new UnGraduate("Superman", "mass cite , Landon", "911911911");
		patrons.add(upatron);
		return new Library(items, patrons);
	}

}
