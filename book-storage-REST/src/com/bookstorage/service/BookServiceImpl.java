package com.bookstorage.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		
		Set<Integer> ids = books.keySet();
		Book[] b = new Book[ids.size()];
		int i=0;
		for(Integer id : ids){
			b[i] = books.get(id);
			i++;
		}
		return b;
	}
}
