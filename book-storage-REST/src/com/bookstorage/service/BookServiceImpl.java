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

import com.bookstorage.db.access.DbAccess;
import com.bookstorage.model.Book;

@Path("/book")
public class BookServiceImpl implements IBookService {
	
	private static DbAccess dbAccess = new DbAccess();
	private static Map<Integer,Book> books = new HashMap<Integer,Book>();

	@Override
	@POST
	@Path("/add")
	//@Produces("application/json")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response addBook(Book book) {		
		dbAccess.connectToDb();
		return dbAccess.addBook(book);
	}

	@Override
	@DELETE
	@Path("/{id}/delete")
	@Produces("application/json")
	public Response deleteBook(@PathParam("id") int id) {
		dbAccess.connectToDb();
	return	dbAccess.deleteBook(id);
	}

	@Override
	@GET
	@Path("/{id}/get")
	@Produces("application/json")
	public Book getBook(@PathParam("id") int id) {
		dbAccess.connectToDb();
		return dbAccess.getBook(id);
	}

//	@Override
//	@GET
//	@Path("/getAll")
//	@Produces("application/json")
//	public Book[] getAllBook() {
//		dbAccess.connectToDb();
//		return dbAccess.getAllBooks();
//	}
}
