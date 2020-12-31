package com.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.IBookDao;
import com.bookstore.dao.entity.Book;
import com.bookstore.service.IBookService;

@Service
public class BookService implements IBookService {

	@Autowired
	IBookDao bookDao;

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public Book add(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public boolean update(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public boolean deleteById(Long id) {
		return bookDao.deleteBookById(id);
	}

	@Override
	public boolean deleteByBook(Book book) {
		return bookDao.deleteBook(book);
	}

	@Override
	public Book findById(Long id) {
		Optional<Book> bookOptional = bookDao.findById(id);
		return bookOptional.orElseThrow(() -> new EntityNotFoundException("Book not found with id : " + id));
	}
	
	

}
