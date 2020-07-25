package com.bookstorage.db.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.bookstorage.model.Book;

public class DbAccess
{
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
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
    public Book getBook(int id)
    {
    	ResultSet bookResultSet;
    	Statement getBookStatement;
    	
    	
    	Book bookQueried = null;
    	try
    	{
    		getBookStatement = connect.createStatement();
    		bookResultSet = getBookStatement.executeQuery(String.format(SqlStrings.SELECT_RECORD, id));
			//TODO: CHECK ID, IF NO ID PRINT MSG
    		bookQueried = constructObject(bookResultSet);
			bookResultSet.close();
			getBookStatement.close();
		} 
    	catch (SQLException e)
    	{
			e.printStackTrace();
		}
    	return bookQueried;
    }
    private Book constructObject(ResultSet bookResultSet) {
    	Book bookToReturn = new Book();
    	try
    	{
    		while (bookResultSet.next()) 
    		{	    		
    			bookToReturn.setId(Integer.valueOf(bookResultSet.getString("id")));
    			bookToReturn.setTitle(bookResultSet.getString("title"));
	    		bookToReturn.setAuthor(bookResultSet.getString("author"));
	    		bookToReturn.setLocation_id(Integer.valueOf(bookResultSet.getString("location_id")));
	    		
    		 }
    	} 
    	catch (SQLException e)
    	{
			e.getMessage();
			e.printStackTrace();
		}
    	
    	return bookToReturn;
		
	}
	public Book[] getAllBooks()
    {
    	Book[] books = null;
		try
    	{
    		resultSet = statement.executeQuery(SqlStrings.SELECT_NUMBER_ALL_RECORDS);			
    		resultSet.next();
    		int dbNumberRecords = resultSet.getInt(1);//get number of record
    		resultSet = statement.executeQuery(SqlStrings.SELECT_ALL_IDS);
    		books = new Book[dbNumberRecords];
    		int i = 0;
    		while((resultSet.next()) && (i < dbNumberRecords))
    		{
    			int bookId = Integer.parseInt(resultSet.getString("id"));
    			books[i] = getBook(bookId);    		
    			System.out.println();
    			i++;
    		}
    		resultSet.close();
    		statement.close();
    		return books;    		
		} 
    	catch (SQLException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
}
