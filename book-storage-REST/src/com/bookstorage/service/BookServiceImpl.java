package com.bookstorage.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bookstorage.model.Book;

@Path("/book")
public class BookServiceImpl implements IBookService {
	
	private static Map<Integer,Book> books = new HashMap<Integer,Book>();

	@Override
	@POST
	@Path("/add")
	//@Produces("application/json")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response addBook(Book book) {		
		
		if(books.get(book.getId()) != null){
			
			return Response.notModified().build();
		}
		books.put(book.getId(), book);		
		return Response.status(200).entity(book).build();
	}

	@Override
	@DELETE
	@Path("/{id}/delete")
	@Produces("application/json")
	public Response deleteBook(@PathParam("id") int id) {
		
		return null;
	}

	@Override
	@GET
	@Path("/{id}/get")
	@Produces("application/json")
	public Book getBook(@PathParam("id") int id) {
		
		return books.get(id);
	}

	@Override
	@GET
	@Path("/getAll")
	@Produces("application/json")
	public Book[] getAllBook() {
		
		//(new Book(213, "TestTitle", "TestAuthor", 3))
		Book[] books = new Book[2];
		for(int i = 0; i <= books.length; i++)
		{
			books[i].setId(i);
			books[i].setAuthor("xx_" + i);
			books[i].setTitle("yy_" + i);
			books[i].setLocation_id(i);
			
		}
		return books;
	}
}
