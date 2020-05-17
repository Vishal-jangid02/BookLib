package com.bookstore.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.models.Book;


public interface BookRepository extends CrudRepository<Book, Long>{
}
