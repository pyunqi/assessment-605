package oop.items;

import java.util.Date;

import oop.util.IDCounter;

/**
 * Item Class
 * 
 * @author yunqi
 *
 */
public class Item implements Cloneable {

	private int itemId; // item unique ID
	private String title; // item tilte , could be book's title, DVD's name or Video's name
	private Date dueDate; // due date
	private String status; // status could be rented,available,expired,damaged,sold;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * default
	 */
	public Item() {

	}

	/**
	 * create a item
	 * 
	 * @param title
	 * @param dueDate
	 * @param status
	 */
	public Item(String title, Date dueDate, String status) {
		this.itemId = IDCounter.getNextUniqueIndexID();
		this.title = title;
		this.dueDate = dueDate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", title=" + title + ", dueDate=" + dueDate + ", status=" + status
				+ ", getStatus()=" + getStatus() + ", getItemId()=" + getItemId() + ", getTitle()=" + getTitle()
				+ ", getDueDate()=" + getDueDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public Object clone() {
		Item item = null;
		try {
			item = (Item) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return item;
	}
}
