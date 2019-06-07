package oop.patron;

/**
 * UnGraduate patron class
 * 
 * @author yunqi
 *
 */

public class UnGraduate extends Patron {


	private String profession = "UnderGraduate Student";

	public String getProfession() {
		return profession;
	}

	/**
	 * create a UnGraduate
	 * @param name
	 * @param address
	 * @param telNumber
	 */
	public UnGraduate(String name, String address, String telNumber) {
		super(name, address, telNumber);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "UnderGraduate Student [profession=" + profession + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getTelNumber()=" + getTelNumber() + "]";
	}
	
	
}
