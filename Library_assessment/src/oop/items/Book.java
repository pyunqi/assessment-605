package oop.items;

import java.util.List;

/**
 * Book class
 * 
 * @author yunqi
 *
 */

public class Book extends Item {

	private List<String> authors;
	private String publisher;
	private String ISBN;
	private String edition;

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * create a book
	 * @param authors
	 * @param publisher
	 * @param iSBN
	 * @param edition
	 */
	public Book(List<String> authors, String publisher, String iSBN, String edition) {
		super();
		this.authors = authors;
		this.publisher = publisher;
		ISBN = iSBN;
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "Book [authors=" + authors + ", publisher=" + publisher + ", ISBN=" + ISBN + ", edition=" + edition
				+ ", getStatus()=" + getStatus() + ", getItemId()=" + getItemId() + ", getTitle()=" + getTitle()
				+ ", getDueDate()=" + getDueDate() + "]";
	}

}
