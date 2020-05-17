package com.bookstore.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Repository.BookRepository;
import com.bookstore.Service.BookService;
import com.bookstore.models.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository; 
	
	@Override
	public List<Book> findAll() {
		
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(Long id) {
		
		return bookRepository.findById(id);
	}

}
