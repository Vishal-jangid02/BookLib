package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

	
}
