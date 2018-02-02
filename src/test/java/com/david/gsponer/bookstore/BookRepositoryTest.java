package com.david.gsponer.bookstore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.gsponer.bookstore.domain.Book;
import com.david.gsponer.bookstore.domain.BookRepository;
import com.david.gsponer.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnStudent() {
		List<Book> books = repository.findByTitle("Me in the desert");
		assertThat(books).hasSize(3);
		assertThat(books.get(0).getAuthor()).isEqualTo("David Gsponer");
	}
	@Test
	public void createNewBook() {
		Book b1 = new Book(new Category("Romantic"), "Me in the desert v50", "David Gsponer", "2099", "CH896987", 30.50f);
		repository.save(b1);
		assertThat(b1.getId()).isNotNull();
	}
	@Test
	public void insertAndDeleteBookTest() {
		Book b1 = new Book(new Category("Romantic"), "Me in the desert v50", "David Gsponer", "2099", "CH896987", 30.50f);
		repository.save(b1);
		long rowCount = repository.count();
		assertEquals(1, rowCount);
		repository.deleteAll();
		long rowCountafter = repository.count();
		assertEquals(0, rowCountafter);
	}
	
	
}
