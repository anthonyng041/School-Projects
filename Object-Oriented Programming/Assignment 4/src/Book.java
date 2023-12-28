// -----------------------------------------------------
// Assignment 4
// Part: Book Class
// Written by: Anthony Nguyen - 40210667
// -----------------------------------------------------

/**
 * The Book class represents a book and its attributes including the title, author, price, ISBN, genre and year of publication.
 * @author Anthony Nguyen
 */

public class Book {
	private String title;
	private String author;
	private double price;
	private long isbn;
	private String genre;
	private int year;
	
	public Book(String title, String author, double price, long isbn, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return title + ", " + author + ", " + price + ", " + isbn + ", " + genre + ", " + year;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return title.equals(other.title) && author.equals(other.author) && price == other.price && isbn == other.isbn && genre.equals(other.genre) &&  year == other.year;
	}
}
