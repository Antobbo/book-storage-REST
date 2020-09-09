package com.bookstorage.db.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.bookstorage.model.Book;

public class DbAccess
{
	private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    public void connectToDb()
    {
    	 try
    	 {
			//Class.forName("com.mysql.jdbc.Driver");
    		 Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(SqlStrings.CONNECTION_STRING);			
			statement = connect.createStatement();
    	 } 
    	 catch (ClassNotFoundException | SQLException e) 
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	 }
    }
    public Response addBook(Book book)
    {
    	ResponseBuilder builder = null;
    	try 
    	{
			statement.executeUpdate(String.format(SqlStrings.INSERT_BOOK, SqlStrings.BOOK_TABLE, book.getTitle(), book.getAuthor(), book.getLocation_id()));
			builder = Response.ok(book);	
			builder.header("success", "Successfully added");
			statement.close();
			
    	} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//use the below in FE to get the proper message.
//    	Response build = builder.build();
//    	String title = ((Book)build.getEntity()).getTitle();
    	return builder.build();
    }
    
    
    public Book getBook(int id)
    {
//    	ResultSet bookResultSet;
//    	Statement getBookStatement;
//    	
//    	
//    	Book bookQueried = null;
//    	try
//    	{
//    		getBookStatement = connect.createStatement();
//    		bookResultSet = getBookStatement.executeQuery(String.format(SqlStrings.SELECT_RECORD, id));
//			//TODO: CHECK ID, IF NO ID PRINT MSG
//    		bookQueried = constructObject(bookResultSet);
//			bookResultSet.close();
//			getBookStatement.close();
//			statement.close();
//			resultSet.close();
//		} 
//    	catch (SQLException e)
//    	{
//			e.printStackTrace();
//		}
//    	return bookQueried;
    	Book bookQueried = null;
    	try
    	{
			resultSet = statement.executeQuery(String.format(SqlStrings.SELECT_RECORD, id));

    		bookQueried = constructObject(resultSet);

			statement.close();
			resultSet.close();
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    	return bookQueried;
    }
    private Book constructObject(ResultSet bookResultSet) {
    	Book bookToReturn = new Book();
    	try
    	{
			if (bookResultSet.next()) 
			{
			    do 
			    {
			    	bookToReturn.setId(Integer.valueOf(bookResultSet.getString("id")));
	    			bookToReturn.setTitle(bookResultSet.getString("title"));
		    		bookToReturn.setAuthor(bookResultSet.getString("author"));
		    		bookToReturn.setLocation_id(Integer.valueOf(bookResultSet.getString("location_id")));
			    } 
			    while(bookResultSet.next());
			} 
			else 
			{
			    System.out.println("No record found ");
			    return null;
			}
    	} 
    	catch (SQLException e)
    	{
			e.getMessage();
			e.printStackTrace();
		}
    	
    	return bookToReturn;
		
	}
//	public Book[] getAllBooks()
//    {
//    	Book[] books = null;
//		try
//    	{
//    		resultSet = statement.executeQuery(SqlStrings.SELECT_NUMBER_ALL_RECORDS);			
//    		resultSet.next();
//    		int dbNumberRecords = resultSet.getInt(1);//get number of record
//    		resultSet = statement.executeQuery(SqlStrings.SELECT_ALL_IDS);
//    		books = new Book[dbNumberRecords];
//    		int i = 0;
//    		while((resultSet.next()) && (i < dbNumberRecords))
//    		{
//    			int bookId = Integer.parseInt(resultSet.getString("id"));
//    			books[i] = getBook(bookId);    		
//    			System.out.println();
//    			i++;
//    		}
//    		resultSet.close();
//    		statement.close();
//    		return books;    		
//		} 
//    	catch (SQLException e)
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//    }
	public Response deleteBook(int id) {
		try
		{
			statement.executeUpdate(String.format(SqlStrings.DELETE, SqlStrings.BOOK_TABLE, id));
			statement.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String deleteMsg = String.format("The record with id %d has been deleted", id);
		return Response
	      .status(Response.Status.OK)
	      .entity(deleteMsg)
	      .build();
	}
    
    
}
