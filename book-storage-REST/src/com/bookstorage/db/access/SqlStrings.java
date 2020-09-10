package com.bookstorage.db.access;

public class SqlStrings 
{
	public static final String BOOK_TABLE = "books";
	public static final String INSERT_BOOK = "INSERT INTO %s (title, author, location_id) VALUES ('%s', '%s', '%s');";
	public static final String DELETE = "DELETE FROM %s WHERE id = %d";
//	public static final String SELECT_RECORD = "SELECT books.id, books.title, books.author, location_id.place from books INNER JOIN location_id ON books.location_id = location_id.location_id WHERE id = %d";
	public static final String SELECT_RECORD = "SELECT * from books WHERE id = %d";
	public static final String UPDATE_RECORD = "UPDATE %s SET title = '%s', author = '%s', location_id = '%s' WHERE id = %d";
	public static final String SELECT_NUMBER_ALL_RECORDS = "SELECT COUNT(*) from books";	
	public static final String SELECT_ALL_IDS = "SELECT books.id FROM books";
	public static final String SELECT_RECORD_BY_AUTHOR = "SELECT * FROM %s WHERE author = '%s'";
	public static final String SELECT_RECORD_BY_TITLE = "SELECT * FROM %s WHERE title = '%s'";
	public static final String SELECT_RECORD_BY_TITLE_AND_AUTHOR = "SELECT * FROM %s WHERE title = '%s' AND author = '%s'";
	public static final String CONNECTION_STRING = "jdbc:mysql://localhost/books_2020?user=root&password=root";
}
