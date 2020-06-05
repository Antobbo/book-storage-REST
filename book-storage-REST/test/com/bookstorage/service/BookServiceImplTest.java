package com.bookstorage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bookstorage.model.Book;
import com.google.gson.Gson;

public class BookServiceImplTest 
{
	private BookServiceImpl bookServiceImpl;
	private String postRequest1;
	private Book book;
	private Response addBook;
	
	@Before
	public void setUp() throws Exception {		
		JSONObject postRequest = new JSONObject();
		postRequest.put("id","987");
		postRequest.put("title","The test");
		postRequest.put("author","Antonio Borrillo");
		postRequest.put("location_id","2");
		bookServiceImpl = new BookServiceImpl();
		postRequest1 = "{\"id\":987,\"title\":\"The test\",\"author\":\"Jane Deart\",\"location_id\":2}";
		book = new Gson().fromJson(postRequest1, Book.class);
		
	}
	
	@After
	public void tearDown() throws Exception {
		bookServiceImpl.deleteBook(987);
	}
	
	@Test
	public void testStatusIs200() {		
		addBook = bookServiceImpl.addBook(book);
		System.out.println("");
		assertEquals(addBook.getStatus(), 200);
	}
	
	@Test
	public void testObjectProperties() {		
		addBook = bookServiceImpl.addBook(book);
		System.out.println("");
		Book book = (Book)addBook.getEntity();		
		assertEquals(book.getAuthor(), "Jane Deart");
		
	}


}
