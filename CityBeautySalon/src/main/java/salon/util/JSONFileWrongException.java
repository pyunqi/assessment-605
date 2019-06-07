package salon.util;


import java.io.FileNotFoundException;

public class JSONFileWrongException extends FileNotFoundException{

	private static final long serialVersionUID = 1L;

	 public JSONFileWrongException() {
		System.err.println("File's path is not right");
		// TODO Auto-generated constructor stub
	}



}
