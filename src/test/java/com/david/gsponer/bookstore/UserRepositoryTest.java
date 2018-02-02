package com.david.gsponer.bookstore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.gsponer.bookstore.domain.User;
import com.david.gsponer.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByUsernameShouldReturnStudent() {
		User u1 = repository.findByUsername("user1");
		assertThat(u1.getUsername()).isEqualTo("user1");
	}
	@Test
	public void createNewUser() {
		User u1 = new User("user3", "$2a$04$Uwt0jkQ634YjeH/nJoUJjea/akAsA.ZuviWMoaRVBhRRTm7G1xSxK", "test3@email.com", "USER");
		repository.save(u1);
		assertThat(u1.getId()).isNotNull();
	}
	@Test
	public void insertAndDeleteUserTest() {
		User u1 = new User("user3", "$2a$04$Uwt0jkQ634YjeH/nJoUJjea/akAsA.ZuviWMoaRVBhRRTm7G1xSxK", "test3@email.com", "USER");
		repository.save(u1);
		long rowCount = repository.count();
		assertEquals(1, rowCount);
		repository.deleteAll();
		long rowCountafter = repository.count();
		assertEquals(0, rowCountafter);
	}
	
	
}