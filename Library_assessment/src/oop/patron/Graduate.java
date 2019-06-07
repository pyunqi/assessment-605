package oop.patron;

/**
 * graduate patron class
 * 
 * @author yunqi
 *
 */
public class Graduate extends Patron {

	private String profession = "Graduate Student";

	/**
	 * get profession
	 * 
	 * @return profession's name
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * create a Graduate
	 * @param name
	 * @param address
	 * @param telNumber
	 */
	public Graduate(String name, String address, String telNumber) {
		super(name, address, telNumber);
	}

	@Override
	public String toString() {
		return "Graduate Student [profession=" + profession + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getTelNumber()=" + getTelNumber() + "]";
	}

}
