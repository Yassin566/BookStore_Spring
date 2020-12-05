package com.bookstore.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String title;
	private String author;
	private float price;
	private Date releaseDate;
	
	
	public Book() {
		super();
	}


	public Book(String title, String author, float price, Date releaseDate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
	}


	public Book(Integer id, String title, String author, float price, Date releaseDate) {
		super();
		Id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
	}


	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	
	
	
}
