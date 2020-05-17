package com.bookstore.Service;

import java.util.List;
import java.util.Optional;

import com.bookstore.models.Book;

public interface BookService {

	List<Book> findAll();

	Optional<Book> findById(Long id);

	
}
