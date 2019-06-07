package salon.util;


import org.json.simple.parser.ParseException;

public class JSONFileWrongPathException extends ParseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public JSONFileWrongPathException(int errorType) {
		super(errorType);
		System.err.println("File's format is not right");
		// TODO Auto-generated constructor stub
	}



}
