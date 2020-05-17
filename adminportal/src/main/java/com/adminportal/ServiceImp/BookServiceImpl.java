package com.adminportal.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.Repository.BookRepository;
import com.adminportal.Service.BookService;
import com.adminportal.models.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		
		return bookRepository.save(book);
		
	}

	@Override
	public List<Book> findAll() {
		
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(long id) {
		
		return bookRepository.findById(id);
	}

}
