package com.david.gsponer.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	List<Book> findByName(@Param("name") String name);
	
}
