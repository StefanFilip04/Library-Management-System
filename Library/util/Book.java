package util;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private String genre;
	private int year;
	private boolean available;

	public Book() {
		this.isbn = "";
		this.title = "";
		this.author = "";
		this.genre = "";
		this.year = 0;
		this.available = true;
	}

	public Book(String isbn, String title, String author, String genre, int year) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
		this.available = true;
	}

	// Getters and Setters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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

	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Book [isbn: " + isbn + ", title: " + title + ", author: " + author
				+ ", genre: " + genre + ", year: " + year + ", available: " + available + "]";
	}
}
