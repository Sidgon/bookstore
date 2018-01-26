package com.david.gsponer.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.david.gsponer.bookstore.domain.Book;
import com.david.gsponer.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			System.out.println("Loading data:");
			repository.save(new Book("Me in the desert", "David Gsponer", "2017", "CH64686312", 12.50f));
			repository.save(new Book("Me in the desert Version 2", "David Gsponer", "2019", "CH896544", 20.50f));
			repository.save(new Book("Me in the desert Version 3", "David Gsponer", "2050", "CH9874321", 30.50f));
			System.out.println(repository.count()+" new Books have been added");
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
}
