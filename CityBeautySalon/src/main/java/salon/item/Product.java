package salon.item;

import java.math.BigDecimal;

public class Product {
	private String produtName;
	private String index;
	private BigDecimal individualPrice; // product individual price
	private String unitString;
	
	public String getUnitString() {
		return unitString;
	}

	public void setUnitString(String unitString) {
		this.unitString = unitString;
	}

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
	 * init a product
	 * @param produtName
	 * @param index
	 * @param individualPrice
	 * @param unitString
	 */
	public Product(String produtName, String index, BigDecimal individualPrice, String unitString) {
		super();
		this.produtName = produtName;
		this.index = index;
		this.individualPrice = individualPrice;
		this.unitString = unitString;
	}

	@Override
	public String toString() {
		return "Index: " + index+" ---- "+ produtName +  " ---- "+ " Price: " + individualPrice + " Unit: "+ unitString ;
	}


}
