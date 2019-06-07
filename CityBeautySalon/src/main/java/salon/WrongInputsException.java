package salon;

public class WrongInputsException extends Exception{

	private static final long serialVersionUID = 1L;

	public WrongInputsException(String message) {
	        super(message);
	    }
	public WrongInputsException() {
        super("Please input a number not String!!!");
    }
	
}
