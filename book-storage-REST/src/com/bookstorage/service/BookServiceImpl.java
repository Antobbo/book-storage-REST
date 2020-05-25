package com.bookstorage.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;
//import javax.xml.ws.Response;
import javax.ws.rs.core.Response;

import com.bookstorage.model.Book;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/bookService")
public class BookServiceImpl implements IBookService {
	
	private static Map<Integer,Book> books = new HashMap<Integer,Book>();

	@Override
	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addBook(Book book) {		
		//Response<?> response = new Response();
	//	String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

		if(books.get(book.getId()) != null){
			
			return Response.notModified().build();
		}
		books.put(book.getId(), book);		
		return Response.status(403).entity(book).build();
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
		
		return null;
	}
}
