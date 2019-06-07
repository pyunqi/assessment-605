package salon.util;

import java.io.File;
import java.util.Properties;

import org.json.simple.JSONObject;


public interface HandleFile {

	/**
	 * append content to a file 
	 * @param file
	 * @param content
	 */
	public void writeALine(File file,String content);
	
	/**
	 * read json file as inputstream and return a json object
	 * @param filePath
	 * @return
	 */
	public JSONObject fetchJSON(String filePath);
	
	/**
	 * read json file by FileReader and return a json object
	 * @param filePath
	 * @return
	 */
	public JSONObject fetchJSONWithPath(String filePath);
	
	/**
	 * read properties file
	 * @param filePath
	 * @return
	 */
	public Properties fetchPropertiesFile(String filePath);
	

}
