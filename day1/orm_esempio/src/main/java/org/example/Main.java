package org.example;

import org.example.entity.Book;
import org.example.entity.Publisher;

public class Main {

	public static void main(String[] args) {
		Dao dao = new Dao();

		Publisher publisher = new Publisher("1", "Hibernate");
		Book book = new Book("9781449334379", "My first book", "Paolo Bianchi", 2020, publisher);
		Book book2 = new Book("115544332277", "My second book", "Paolo Bianchi", 2021, publisher);
		
		dao.addPublisher(publisher);
		dao.addBook(book);
		dao.addBook(book2);
		
		System.out.println(dao.findBook(book.getIsbn()));
		
		dao.deletePublisher(publisher.getCode());
	}
}
