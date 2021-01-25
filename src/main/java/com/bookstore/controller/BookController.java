package com.bookstore.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@CrossOrigin("*")
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
	public ResponseEntity<ImageModel> uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());

		final ImageModel savedImage = imageDao.save(img);

		System.out.println("Image saved");

		return ResponseEntity.status(HttpStatus.OK).body(savedImage);

	}
	
//	public static byte[] compressBytes(byte[] data) {
//		Deflater deflater = new Deflater();
//		deflater.setInput(data);
//		deflater.finish();
//
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[1024];
//		while (!deflater.finished()) {
//			int count = deflater.deflate(buffer);
//			outputStream.write(buffer, 0, count);
//		}
//		try {
//			outputStream.close();
//		} catch (IOException e) {
//		}
//		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//
//		return outputStream.toByteArray();
//	}
//	
//	public static byte[] decompressBytes(byte[] data) {
//		Inflater inflater = new Inflater();
//		inflater.setInput(data);
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[1024];
//		try {
//			while (!inflater.finished()) {
//				int count = inflater.inflate(buffer);
//				outputStream.write(buffer, 0, count);
//			}
//			outputStream.close();
//		} catch (IOException ioe) {
//		} catch (DataFormatException e) {
//		}
//		return outputStream.toByteArray();
//	}

	@GetMapping("/getImage/{id}")
	 public ImageModel getImage(@PathVariable Long id) throws IOException{
		System.out.println(id);
		 final Optional<ImageModel> retrievedImage = imageDao.findById(id);
		 ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),retrievedImage.get().getPic());
		 return img;
	 }

}
