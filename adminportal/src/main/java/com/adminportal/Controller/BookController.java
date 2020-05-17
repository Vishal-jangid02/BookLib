package com.adminportal.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.Service.BookService;
import com.adminportal.models.Book;


@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {

		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}

	@RequestMapping(value = "/add")
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {

		bookService.save(book);

		MultipartFile bookImage = book.getBookImage();

		try {

			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			
			e.getMessage();
		}
		
		return "redirect:bookList";
	}
	
	@RequestMapping(value = "/bookInfo")
	public String bookInfo(@PathParam("id") long id,Model model) {
		
		Optional<Book> book = bookService.findById(id);
		model.addAttribute("book", book.get());
		return "bookInfo";
	}
	
	@RequestMapping("/updateBook")
	public String updateBook(@PathParam("id") long id,Model model) {
		
		Optional<Book> book = bookService.findById(id);
		model.addAttribute("book",book.get());
		return "updateBook";
				
	}
	
	@RequestMapping(value="/updateBook",method = RequestMethod.POST)
	public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		
		bookService.save(book);
		MultipartFile bookImage = book.getBookImage();
		
		if(!bookImage.isEmpty()) {
			try {

				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/book/" + name));
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				
				e.getMessage();
			}
		}
		
		return "redirect:/book/bookInfo?id="+book.getId();
	}
	
	@RequestMapping("/bookList")
	public String bookList(Model model) {
		
	List<Book> bookList = bookService.findAll();
	
	model.addAttribute("bookList", bookList);
		return "bookList";
	}

}
