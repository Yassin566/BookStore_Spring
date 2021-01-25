package com.bookstore.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.dao.IBookDao;
import com.bookstore.dao.entity.Book;
import com.bookstore.dao.repository.BookRepository;

@Repository
public class BookDaoImpl implements IBookDao {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public boolean updateBook(Book book) {
		if (bookRepository.existsById(book.getId())) {
			bookRepository.save(book);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteBookById(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		Long id = book.getId();
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}
	
	

}
