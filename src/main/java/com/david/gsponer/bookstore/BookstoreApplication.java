package com.david.gsponer.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.david.gsponer.bookstore.domain.Book;
import com.david.gsponer.bookstore.domain.BookRepository;
import com.david.gsponer.bookstore.domain.Category;
import com.david.gsponer.bookstore.domain.CategoryRepository;
import com.david.gsponer.bookstore.domain.User;
import com.david.gsponer.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			System.out.println("Loading data:");
			Category c1=new Category("Animals");
			Category c2=new Category("History");
			Category c3=new Category("Romance");
			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);
			repository.save(new Book(c1, "Me in the desert", "David Gsponer", "2017", "CH64686312", 12.50f));
			repository.save(new Book(c2, "Me in the desert Version 2", "David Gsponer", "2019", "CH896544", 20.50f));
			repository.save(new Book(c3, "Me in the desert Version 3", "David Gsponer", "2050", "CH9874321", 30.50f));
			System.out.println(repository.count()+" new Books have been added");
			
			//creating users
			User u1 = new User("user1", "$2a$04$aid3MnXoX7OyKYmJ.63PUe5UPmOH1uFm/XVCb0xOh3rtu5ycfgw0S", "test@user.com", "ADMIN");
			User u2 = new User("user2", "$2a$04$Uwt0jkQ634YjeH/nJoUJjea/akAsA.ZuviWMoaRVBhRRTm7G1xSxK", "test2@email.com", "USER");
			urepository.save(u1);
			urepository.save(u2);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
}
