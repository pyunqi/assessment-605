package oop.patron;


/**
 * UnKnown patron class
 * 
 * @author yunqi
 *
 */

public class UnKnown extends Patron {

	private String profession = "UnKnown";

	public String getProfession() {
		return profession;
	}

	/**
	 * create a unknowns
	 * @param name
	 * @param address
	 * @param telNumber
	 */
	public UnKnown(String name, String address, String telNumber) {
		super(name, address, telNumber);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "UnKnown [profession=" + profession + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getTelNumber()=" + getTelNumber() + "]";
	}
	
	
}
