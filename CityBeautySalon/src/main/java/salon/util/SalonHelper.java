package salon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SalonHelper implements HandleFile {

	/**
	 * write content to a file with append mode
	 * 
	 * @file
	 * @content
	 */
	public void writeALine(File file, String content) {
//		System.out.println(content);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
			fw.append(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.flush();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Read JSON file
	 * 
	 * @filePath
	 */
	public JSONObject fetchJSON(String filePath) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			InputStream targetStream = new FileInputStream(filePath);
			InputStreamReader streamReader = new InputStreamReader(targetStream);
			Object obj = parser.parse(streamReader);
			jsonObject = (JSONObject) obj;
		} catch (JSONFileWrongPathException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * read json file and return a json object
	 * 
	 * @param filePath
	 * @return JSONObject
	 */
	public JSONObject fetchJSONWithPath(String filePath) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Object obj = parser.parse(new FileReader(filePath));
			jsonObject = (JSONObject) obj;
		} catch (JSONFileWrongPathException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * read properties file
	 * 
	 * @filePath
	 */
	public Properties fetchPropertiesFile(String filePath) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(filePath);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * print receipt from file
	 * 
	 * @param filePath
	 */
	public void printReceipt(String filePath) {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(filePath));
			Stream<String> lines = br.lines().map(str -> str.toUpperCase());
			System.out.println("<!-----Blow is the Receipt-----!> \r");
			lines.forEach(System.out::println);
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	
}
