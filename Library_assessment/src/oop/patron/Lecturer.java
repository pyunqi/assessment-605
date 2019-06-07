package oop.patron;

/**
 * Lecturer patron class
 * 
 * @author yunqi
 *
 */
public class Lecturer extends Patron {

	private String profession = "Lecturer";

	public String getProfession() {
		return profession;
	}

	/**
	 * create a Lecturer
	 * @param name
	 * @param address
	 * @param telNumber
	 */
	public Lecturer(String name, String address, String telNumber) {
		super(name, address, telNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Lecturer [profession=" + profession + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getTelNumber()=" + getTelNumber() + "]";
	}

}
