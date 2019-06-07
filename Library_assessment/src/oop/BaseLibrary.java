package oop;

import java.util.HashMap;
import java.util.List;

import oop.items.Item;
import oop.patron.Patron;
import oop.util.ConstantLibrary;

/**
 * Basic method and variables for Library
 * 
 * @author yunqi
 *
 */
public class BaseLibrary implements ConstantLibrary {

	protected static List<Item> stockItems; // item storage
	protected static HashMap<Item, Patron> loanedItems; // loaned item and patron relationship mapping

	/**
	 * according to inputed item info to find is there a item in library
	 * 
	 * @param itemInfo  is the key word for searching
	 * @param itemsList
	 * @param status
	 * @return item which matched
	 */
	protected static Item findItem(String itemInfo, List<Item> itemsList, String status) {
		Item tempItem = new Item();
		// it's number
		if (itemInfo.chars().allMatch(Character::isDigit)) {
			for (Item item : itemsList) {
				if (item.getItemId() == Integer.parseInt(itemInfo)) {
					tempItem = item;
					break;
				}
			}
		} else { // it's title
			for (Item item : itemsList) {
				if (itemInfo.equals(item.getTitle())) {
					tempItem = item;
					break;
				}
			}
		}
		// must be available or return null
		if (status.equals(tempItem.getStatus())) {
			return tempItem;
		} else {
			return null;
		}

	}

	/**
	 * find the patron exsit or not
	 * 
	 * @param patronInfo
	 * @return patron or not find
	 */
	protected static Patron findPatron(String patronInfo) {
		// TODO Auto-generated method stub
		List<Patron> patrons = Library.getPatrons();
		for (Patron patron : patrons) {
			if (patronInfo.chars().allMatch(Character::isDigit)) {
				if (patron.getId() == Integer.valueOf(patronInfo)) {
					return patron;
				}
			} else {
				if (patronInfo.equals(patron.getName())) {
					return patron;
				}
			}
		}
		return null;
	}

	/**
	 * set return item's status and put it back to stock
	 * 
	 * @param item
	 * @param newStatus
	 */
	protected static void returnItem(Item item, char newStatus) {
		Patron patron = loanedItems.get(item);
		loanedItems.remove(item);
		System.out.println("The item: " + item.getTitle() + " returned by:" + patron.getName());
		switch (newStatus) {
		case 'A':
			item.setStatus(AVAILABLE);
			break;
		case 'D':
			item.setStatus(DAMAGED);
			break;
		default:
			item.setStatus(DAMAGED);
			break;
		}
		stockItems.add(item); // put it back
	}



}
