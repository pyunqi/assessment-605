package salon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import salon.item.Product;
import salon.item.Service;
import salon.util.SalonHelper;

public class Salon implements ConstantCBS {

	private static Scanner scanner; // using for user input commands and information
	private static String basePath = Salon.class.getClassLoader().getResource("").getPath(); // file base location
	private static String formatLine = "-----------------------------------------------------------";
	private static SalonHelper sh = new SalonHelper();
	private static BigDecimal taxRate = getRate(basePath + SETTING, TAXRATE);
	private static BigDecimal productDiscount = getRate(basePath + SETTING, PRODUCT_DISCOUNT);
	private static BigDecimal serviceDiscount = null;
	private static BigDecimal subTotal = new BigDecimal(0);
	private final static int width = 60;// define reception paper width

	public static void main(String[] args) throws WrongInputsException {
		scanner = new Scanner(System.in);
		String customerNumber = getCustomerNumber(basePath + SETTING); // get a customer number
		// initial city beauty salon shop with configuration files
		CityBeautySalon cbs = new CityBeautySalon(PRODUCTS, SERVICES);
		// a customer go to reception to check
		boolean finished = false;
		String memberShipLevel = "";
		DecimalFormat df = new DecimalFormat("#.00");
		DecimalFormat dfP = new DecimalFormat("00%");
		File receptionFile = new File(basePath + RECEPTION_PATH + UUID.randomUUID().toString());// a file for store
																								// reception
		// write the begin
		sh.writeALine(receptionFile,
				"CITY Beauty Salon      " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())
						+ "      Customer count: " + customerNumber);
		// enter the member ship
		while (!finished) {
			printMemberHelp();
			char memberShip = scanner.next().toUpperCase().charAt(0);
			switch (memberShip) {
			case 'P':
				serviceDiscount = getRate(basePath + SETTING, PREMIUM_MEMBER_DISCOUNT);
				sh.writeALine(receptionFile, "\rMember Ship : Premium \r");
				memberShipLevel = "Premium";
				finished = true;
				break;
			case 'G':
				serviceDiscount = getRate(basePath + SETTING, GOLD_MEMBER_DISCOUNT);
				sh.writeALine(receptionFile, "\rMember Ship : Gold \r");
				memberShipLevel = "Gold";
				finished = true;
				break;
			case 'S':
				serviceDiscount = getRate(basePath + SETTING, SILVER_MEMBERS_DISCOUNT);
				sh.writeALine(receptionFile, "\rMember Ship : Silver \r");
				memberShipLevel = "Silver";
				finished = true;
				break;
			case 'O':
				serviceDiscount = new BigDecimal(0);
				sh.writeALine(receptionFile, "\rMember Ship : N/A \r");
				finished = true;
				break;
			default:
				System.out.println("Wrong input, please re-enter member ship level!");
			}
		}

		// start to enter products
		finished = false;
		listProduct(cbs); // list products we have
		while (!finished) {
			System.out.println("Please enter what kind product that customer bought by \"Index Number\": ");
			int tempProductIndex = 0;
			int tempProductNumber = 0;
			String enteredProduct = scanner.next();// enter product index
			// verify inputs
			try {
				if (enteredProduct.matches("\\d+")) {
					tempProductIndex = Integer.valueOf(enteredProduct);
				} else if ("C".equals(enteredProduct.toUpperCase().substring(0))) {// will quit
					finished = true;
					break;
				} else {
					throw new WrongProductException();
				}
			} catch (WrongProductException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				continue;
			}
			// enter the number of products
			System.out.println("Please enter how many products that customer bought: ");
			try {
				tempProductNumber = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid inputs and please re try!");
				continue;
			}
			// output products related information into file
			outputProduct(cbs, memberShipLevel, df, dfP, receptionFile, tempProductIndex, tempProductNumber);
		}

		// start to enter services
		finished = false;
		listServices(cbs);	// list services we have
		while (!finished) {
			System.out.println("Please enter what kind service that customer bought by \"Index Number\": ");
			String inputString = scanner.next();// enter which service bought by customer
			int tempServiceIndex = 0;
			try {
				if (inputString.matches("\\d+")) {
					tempServiceIndex = Integer.valueOf(inputString);
				} else if ("C".equals(inputString.toUpperCase().substring(0))) {// will quit
					finished = true;
					break;
				} else {
					throw new WrongServiceException();
				}
			} catch (WrongServiceException e) {
				System.out.println(e.getMessage());
				continue;
			}
			// output service related information into file
			outputServices(cbs, memberShipLevel, df, dfP, receptionFile, tempServiceIndex);
		}

		// calculate and output sub-total
		sh.writeALine(receptionFile, setIndent("Subtotal") + df.format(subTotal));
		// calculate and output tax
		sh.writeALine(receptionFile,
				setIndent("Tax") + df.format(subTotal.multiply(new BigDecimal(1).subtract(taxRate))));
		// calculate and output total cost
		sh.writeALine(receptionFile, setIndent("Total Cost")
				+ df.format(subTotal.add(subTotal.multiply(new BigDecimal(1).subtract(taxRate)))));
		sh.printReceipt(receptionFile.getAbsolutePath());
		// print all

	}

	/**
	 * output services to receipt file
	 * 
	 * @param cbs
	 * @param memberShipLevel
	 * @param df
	 * @param dfP
	 * @param receptionFile
	 * @param tempServiceIndex
	 */
	private static void outputServices(CityBeautySalon cbs, String memberShipLevel, DecimalFormat df, DecimalFormat dfP,
			File receptionFile, int tempServiceIndex) {
		for (Service s : cbs.getStockServices()) {
			if (String.valueOf(tempServiceIndex).equals(s.getIndex())) {
				BigDecimal totalPrice = calServicesCost(s, memberShipLevel);
				subTotal = subTotal.add(totalPrice);
				sh.writeALine(receptionFile, setIndent(s.getProdutName()) + df.format(totalPrice));
				sh.writeALine(receptionFile, "\r" + dfP.format(new BigDecimal(1).subtract(serviceDiscount)) + " OFF\r");
			}
		}
	}

	/**
	 * enter service
	 * 
	 * @param cbs
	 * @return
	 */
	private static void listServices(CityBeautySalon cbs) {
		System.out.println("Following list are our services:");
		System.out.println(formatLine);
		for (Service s : cbs.getStockServices()) {
			System.out.println(s.toString());
		}
		System.out.println("If there is no more service was bought, Please input C to continue.");
		System.out.println(formatLine);
	}

	/**
	 * output products to receipt file
	 * 
	 * @param cbs
	 * @param memberShipLevel
	 * @param df
	 * @param dfP
	 * @param receptionFile
	 * @param tempProductIndex
	 * @param tempProductNumber
	 */
	private static void outputProduct(CityBeautySalon cbs, String memberShipLevel, DecimalFormat df, DecimalFormat dfP,
			File receptionFile, int tempProductIndex, int tempProductNumber) {
		for (Product p : cbs.getStockProducts()) {
			if (String.valueOf(tempProductIndex).equals(p.getIndex())) {
				BigDecimal totalPrice = calProductsCost(p, tempProductNumber, memberShipLevel);
				subTotal = subTotal.add(totalPrice);
				sh.writeALine(receptionFile, setIndent(p.getProdutName()) + df.format(totalPrice));
				sh.writeALine(receptionFile,
						"\r" + tempProductNumber + " @ " + p.getIndividualPrice() + "/" + p.getUnitString());
				sh.writeALine(receptionFile, "\r" + dfP.format(new BigDecimal(1).subtract(productDiscount)) + " OFF\r");
			}
		}
	}

	/**
	 * enter product
	 * 
	 * @param cbs
	 * @return
	 */
	private static void listProduct(CityBeautySalon cbs) {
		System.out.println("Following list are our products:");
		System.out.println(formatLine);
		// list products
		for (Product p : cbs.getStockProducts()) {
			System.out.println(p.toString());
		}
		System.out.println("If there is no more product was bought, Please input C to continue.");
		System.out.println(formatLine);
	}

	/**
	 * calculate the indents to fit format
	 * 
	 * @param content
	 * @return
	 */
	private static String setIndent(String content) {
		int spaceNumber = width - content.length() - 5;
		StringBuffer spaces = new StringBuffer();
		spaces.append("\r").append(content);
		for (int i = 0; i < spaceNumber; i++) {
			spaces.append(" ");
		}
		return spaces.toString();
	}

	/**
	 * calculate product cost
	 * 
	 * @param product
	 * @param tempProductNumber
	 * @param memberShipLevel
	 * @return
	 */
	private static BigDecimal calProductsCost(Product product, int tempProductNumber, String memberShipLevel) {
		if ("".equals(memberShipLevel)) {
			return product.getIndividualPrice().multiply(new BigDecimal(tempProductNumber));
		} else {
			return product.getIndividualPrice().multiply(new BigDecimal(tempProductNumber)).multiply(productDiscount);
		}

	}

	/**
	 * calculate services cost
	 * 
	 * @param service
	 * @param memberShipLevel
	 * @return
	 */
	private static BigDecimal calServicesCost(Service service, String memberShipLevel) {
		if ("".equals(memberShipLevel)) {
			return service.getIndividualPrice();
		} else {
			return service.getIndividualPrice().multiply(serviceDiscount);
		}

	}

	/**
	 * print member level help info
	 */
	private static void printMemberHelp() {
		System.out.println("please enter the customer's membership:");
		System.out.println("P is Premium Member");
		System.out.println("G is Gold Member");
		System.out.println("S is Sliver Member");
		System.out.println("O is Premium Member");
	}

	/**
	 * get tax rate from setting file
	 * 
	 * @param taxRateFilePath
	 * @return
	 */
	private static BigDecimal getRate(String taxRateFilePath, String type) {
		File taxRateFile = new File(taxRateFilePath);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(taxRateFile);
			bufferedReader = new BufferedReader(fileReader);
			String tempString = bufferedReader.readLine();
			while (tempString != null) {
				if (tempString.contains(type)) {
					return new BigDecimal(1).subtract(new BigDecimal(tempString.split("=")[1]));
				} else {
					tempString = bufferedReader.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null; // if not find taxrate in file
	}

	/**
	 * get a customer number from file
	 */
	public static String getCustomerNumber(String settingFilePath) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(settingFilePath);
			bufferedReader = new BufferedReader(fileReader);
			String tempString = bufferedReader.readLine();
			while (tempString != null) {
				if (tempString.contains("CustomerNumber")) {
					String[] tempCs = tempString.split("=")[1].split(",");
					int rnd = new Random().nextInt(tempCs.length);
					return tempCs[rnd];
				} else {
					tempString = bufferedReader.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null; // if not find taxrate in file
	}
}
