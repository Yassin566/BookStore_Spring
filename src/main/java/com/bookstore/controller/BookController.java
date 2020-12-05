package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Book;
import com.bookstore.repository.BookRepository;

@RestController
@RequestMapping(value = "/books")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	
	@GetMapping("/")
	public List<Book> getAll(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Book getBook(@PathVariable Integer id) {
		return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Il n'existe pas "));
		
	}
	
	@PostMapping(value= {"/add", "/update"})
	public Book addproduit(@RequestBody Book book) {
		
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduit(@PathVariable Integer id) {
		bookRepository.deleteById(id);
	}
	
	@PostMapping(value= {"/calculate"})
	public float calculate(@RequestBody List<Book> books) {
		float totale = 0;
		for (Book book:books) {
			totale+=book.getPrice();
		}
		return totale;
		
	}

}
