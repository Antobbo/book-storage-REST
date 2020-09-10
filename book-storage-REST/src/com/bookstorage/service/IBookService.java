package com.bookstorage.service;

import javax.ws.rs.core.Response;

import com.bookstorage.model.Book;

public interface IBookService {
	
	public Response addBook(Book p);
	
	public Response deleteBook(int id);
	
	public Book getBook(int id);
	
	public Response updateBook(int id, String author, String title, int location_id);
	
	//public Book[] getAllBook();
}
