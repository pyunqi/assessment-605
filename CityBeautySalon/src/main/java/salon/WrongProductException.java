package salon;

public class WrongProductException extends Exception{

	private static final long serialVersionUID = 1L;

	public WrongProductException() {
        super("Please input a valid product's index number!!!");
    }
	
}
