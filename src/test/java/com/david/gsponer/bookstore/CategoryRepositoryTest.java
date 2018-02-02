package com.david.gsponer.bookstore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.gsponer.bookstore.domain.Category;
import com.david.gsponer.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void findByNameShouldReturnStudent() {
		List<Category> categorys = repository.findByName("Romantic");
		assertThat(categorys).hasSize(3);
		assertThat(categorys.get(0).getName()).isEqualTo("Romantic");
	}
	@Test
	public void createNewCategory() {
		Category c1 = new Category("NewCategory");
		repository.save(c1);
		assertThat(c1.getId()).isNotNull();
	}
	@Test
	public void insertAndDeleteCategoryTest() {
		Category c1 = new Category("NewCategory");
		repository.save(c1);
		long rowCount = repository.count();
		assertEquals(1, rowCount);
		repository.deleteAll();
		long rowCountafter = repository.count();
		assertEquals(0, rowCountafter);
	}
	
	
}