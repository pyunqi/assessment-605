package salon.item;

import java.math.BigDecimal;

public class Service {
	private String produtName;
	private String index;
	private BigDecimal individualPrice; // product individual price

	public String getProdutName() {
		return produtName;
	}

	public void setProdutName(String produtName) {
		this.produtName = produtName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public BigDecimal getIndividualPrice() {
		return individualPrice;
	}

	public void setIndividualPrice(BigDecimal individualPrice) {
		this.individualPrice = individualPrice;
	}

	/**
	 * init a service
	 * @param produtName
	 * @param index
	 * @param individualPrice
	 */
	public Service(String produtName, String index, BigDecimal individualPrice) {
		super();
		this.produtName = produtName;
		this.index = index;
		this.individualPrice = individualPrice;
	}

	@Override
	public String toString() {
		return "Index: " + index+" ---- "+ produtName +  " ---- "+ " Price: " + individualPrice ;
	}

}
