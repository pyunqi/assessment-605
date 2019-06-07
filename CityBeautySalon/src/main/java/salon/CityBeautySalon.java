package salon;
/**
 * this class is to init a salon
 * @author yunqi
 *
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import salon.item.Product;
import salon.item.Service;
import salon.util.SalonHelper;

public class CityBeautySalon implements ConstantCBS {

	private String basePath = CityBeautySalon.class.getClassLoader().getResource("").getPath(); // file base location
	private static SalonHelper sh = new SalonHelper();

	private List<Product> stockProducts;
	private List<Service> stockServices;

	public List<Product> getStockProducts() {
		return stockProducts;
	}

	public void setStockProducts(List<Product> stockProducts) {
		this.stockProducts = stockProducts;
	}

	public List<Service> getStockServices() {
		return stockServices;
	}

	public void setStockServices(List<Service> stockServices) {
		this.stockServices = stockServices;
	}

	/**
	 * initial stocks of products and services
	 * 
	 * @param stockProducts
	 * @param stockServices
	 */
	@SuppressWarnings("unchecked")
	public CityBeautySalon(String stockProductsJson, String stockServicesJson) {
		this.stockProducts = new ArrayList<Product>();
		this.stockServices = new ArrayList<Service>();
		JSONObject productsJson = sh.fetchJSON(basePath + stockProductsJson);
		JSONObject servicesJson = sh.fetchJSON(basePath + stockServicesJson);
		// generate list about what kind of product we have
		for (JSONObject product : (List<JSONObject>) productsJson.get(PRODUCTSLIST)) {
			Product temProduct = new Product(product.get("name").toString(), product.get("index").toString(),
					new BigDecimal(product.get("price").toString()),product.get("unit").toString());
			stockProducts.add(temProduct);
		}

		// generate list about what kind of service we have
		for (JSONObject service : (List<JSONObject>) servicesJson.get(SERVICESLIST)) {
			Service temService = new Service(service.get("name").toString(), service.get("index").toString(),
					new BigDecimal(service.get("price").toString()));
			stockServices.add(temService);
		}
	}

}
