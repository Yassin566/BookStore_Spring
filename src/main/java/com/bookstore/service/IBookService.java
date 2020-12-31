package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.entity.Book;

public interface IBookService {
	
	List<Book> getAllBooks();

	Book add(Book book);

    boolean update(Book book);

    boolean deleteById(Long id);

    boolean deleteByBook(Book book);

    Book findById(Long id);

}
