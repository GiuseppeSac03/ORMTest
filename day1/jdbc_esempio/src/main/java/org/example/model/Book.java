package org.example.model;

public class Book {

    private String isbn;

    private String title;
    
    private String author;
    
    private int year;

    private Publisher publisher;
    
	public Book() {}

	public Book(String isbn, String title, String author, int year, Publisher publisher) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.publisher = publisher;
	}

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public String toString() {
    	return String.format("%s - %s - %d - %s - %s ", title, author, year, isbn, publisher.getName());
    }
}
