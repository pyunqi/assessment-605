package oop.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import oop.items.Book;
import oop.items.DVD;
import oop.items.Video;

public class ItemHelper implements ConstantLibrary {

	/**
	 * create a book from inputs
	 * @param scanner
	 * @return
	 */
	public static Book createBook(Scanner scanner) {
		System.out.println("Add new books!");
		// add a book to library
		System.out.println("please enter title : ");
		String title = scanner.next();
		System.out.println("please enter ISBN : ");
		String isbn = scanner.next();
		System.out.println("please enter publisher : ");
		String publisher = scanner.next();
		System.out.println("please enter edition: ");
		String edition = scanner.next();
		System.out.println("please enter authors and each author name should split with , : ");
		List<String> authors = tolist(scanner.next());
		Book book = new Book(authors, publisher, isbn, edition);
		book.setDueDate(new Date());
		book.setStatus(AVAILABLE);
		book.setTitle(title);
		return book;
	}
	

	/**
	 * create a dvd from inputs
	 * @param scanner
	 * @return
	 */
	public static DVD createDVD(Scanner scanner) {
		System.out.println("Add new DVDs!");
		System.out.println("please enter title: ");
		String title = scanner.next();
		System.out.println("please enter release year (yyyy): ");
		String releasDate = scanner.next();
		System.out.println("please enter actors and each actor name should split with , : ");
		List<String> auctors = tolist(scanner.next());
		System.out.println("please enter life time : ");
		int lifeTime=scanner.nextInt();
		DVD dvd = new DVD(releasDate,lifeTime, auctors);
		dvd.setStatus(AVAILABLE);
		dvd.setTitle(title);
		// set now as date
		dvd.setDueDate(new Date());
		return dvd;
	}
	
	/**
	 * create a video from inputs
	 * @param scanner
	 * @return
	 */
	public static Video	createVideo(Scanner scanner) {
		// add a video to library
		System.out.println("Add new Videos!");
		System.out.println("please enter title: ");
		String title=scanner.next();
		System.out.println("please enter producers and each producer name should split with , : ");
		String producer = scanner.next();
		System.out.println("please enter video length (how many mintues): ");
		int length = scanner.nextInt();
		System.out.println("please enter life time : ");
		int lifeTime =scanner.nextInt();
		Video video = new Video(producer, lifeTime, length);
		video.setStatus(AVAILABLE);
		video.setTitle(title);
		// set now as date
		video.setDueDate(new Date());
		return video;
	}

	/**
	 * get string from scanner and change to List type.
	 * 
	 * @param slist
	 * @return cast string as a list
	 */
	public static List<String> tolist(String slist) {
		List<String> tolist = new ArrayList<String>();
		for (String author : slist.split(",")) {
			tolist.add(author);
		}
		return tolist;
	}

}
