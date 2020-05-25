package com.bookstorage.service;

import javax.ws.rs.core.Response;

import com.bookstorage.model.Book;

public interface IBookService {
	public Response addBook(Book p);
	
	public Response deleteBook(int id);
	
	public Book getBook(int id);
	
	public Book[] getAllBook();
}
