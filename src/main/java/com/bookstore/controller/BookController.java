package com.bookstore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.dao.entity.Book;
import com.bookstore.dao.entity.ImageModel;
import com.bookstore.dao.repository.ImageModelRepository;
import com.bookstore.service.IBookService;

@RestController
@RequestMapping(value = "/books")
public class BookController {
	@Autowired
	IBookService bookService;
	@Autowired
	ImageModelRepository imageDao;

	@GetMapping("")
	public List<Book> getBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/find")
	public Book getBook(@RequestParam Long id) {
		return bookService.findById(id);
	}

	@PostMapping("/add")
	public Book addBook(@RequestBody Book book) {
		return bookService.add(book);
	}

	@DeleteMapping("/remove")
	public boolean deleteBook(@RequestParam Long id) {
		return bookService.deleteById(id);
	}

	@PutMapping("/update")
	public boolean updateBook(@RequestBody Book book) {
		return bookService.update(book);
	}
	
	 @PostMapping("/upload")
	    public ImageModel uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

	        ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes() );


	        final ImageModel savedImage = imageDao.save(img);


	        System.out.println("Image saved");


	        return savedImage;


	    }

}
