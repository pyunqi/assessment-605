package salon.member;

public class NormalCustomer extends Member {

	private String serviceDiscount;
	private String productDiscount;
	private String memberLevel = "N/A";

	public String getMemberLevel() {
		return memberLevel;
	}

	public String getServiceDiscount() {
		return serviceDiscount;
	}

	public void setServiceDiscount(String serviceDiscount) {
		this.serviceDiscount = serviceDiscount;
	}

	public String getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}

	/**
	 * init Normal Customer
	 * 
	 * @param memberName
	 * @param dueDate
	 * @param serviceDiscount
	 * @param productDiscount
	 */
	public NormalCustomer(String memberName, String dueDate, String serviceDiscount, String productDiscount) {
		super(memberName);
		this.serviceDiscount = serviceDiscount;
		this.productDiscount = productDiscount;
	}

}
