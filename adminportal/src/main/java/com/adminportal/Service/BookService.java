package com.adminportal.Service;

import java.util.List;
import java.util.Optional;

import com.adminportal.models.Book;

public interface BookService {

	Book save(Book book);

	List<Book> findAll();

	Optional<Book> findById(long id);

}
