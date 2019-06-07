package salon;

public class WrongServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public WrongServiceException() {
        super("Please input a valid service index number!!!");
    }
	
}
