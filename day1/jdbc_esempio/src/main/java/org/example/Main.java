package org.example;

import org.example.model.Book;
import org.example.model.Publisher;

import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		Dao service = new Dao();

		Publisher publisher = new Publisher("1", "My Publisher");
		Book book = new Book("12345aa54321", "Book 1", "Mario Rossi", 2000, publisher);

		service.insertPublisher(publisher);
		service.insertBook(book);

		Book book2 = service.selectByIsbn("12345aa54321");
		System.out.println(book2);

		book2.setTitle("New Title");
		service.update(book2);
		System.out.println(book2);

		service.delete(book2);
		service.delete(publisher);
    }
}
