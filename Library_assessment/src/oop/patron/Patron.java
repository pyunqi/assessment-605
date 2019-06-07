package oop.patron;

import oop.util.IDCounter;

/**
 * basic class for patron
 * 
 * @author yunqi
 *
 */
public class Patron {

	private int id; // unique id for patron
	private String name; // patron's name
	private String address; // patron's address
	private String telNumber; // patron's Telephone number

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	/**
	 * default
	 */
	public Patron() {
		super();
	}

	/**
	 * patron init
	 * @param name
	 * @param address
	 * @param telNumber
	 */
	public Patron(String name, String address, String telNumber) {
		super();
		this.id = IDCounter.getNextUniqueIndexP();
		this.name = name;
		this.address = address;
		this.telNumber = telNumber;
	}

	@Override
	public String toString() {
		return "Patron [id=" + id + ", name=" + name + ", address=" + address + ", telNumber=" + telNumber + "]";
	}

}
