package org.example;

import org.example.model.Book;
import org.example.model.Publisher;
import org.example.util.JdbcUtil;

import java.sql.*;

public class Dao {
	
	public void insertPublisher(Publisher publisher) throws SQLException {
		String query = "INSERT INTO PUBLISHER (CODE, NAME) VALUES  (?, ?)";
    	Connection conn = null;
    	PreparedStatement pstm = null;
        try {
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setString(1, publisher.getCode());
            pstm.setString(2, publisher.getName());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
    }

    public void insertBook(Book book) throws SQLException {
    	String query = "INSERT INTO BOOK (ISBN, TITLE, AUTHOR, YEAR, PUBLISHER_CODE) VALUES (?,?,?,?,?)";
    	Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();

            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, book.getTitle());
            pstm.setString(3, book.getAuthor());
            pstm.setInt(4, book.getYear());
            pstm.setString(5, book.getPublisher().getCode());
            pstm.executeUpdate();
            pstm.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
    }

    public Book selectByIsbn(String isbn) throws SQLException {
    	String query = "SELECT BOOK.ISBN, BOOK.TITLE, BOOK.AUTHOR, BOOK.YEAR, BOOK.PUBLISHER_CODE, PUBLISHER.NAME AS PUB_NAME "
        		+ "FROM BOOK, PUBLISHER WHERE BOOK.PUBLISHER_CODE = PUBLISHER.CODE AND BOOK.ISBN = ?";
    	Connection conn = null;
    	PreparedStatement stmt = null;
        Book book = null;
        try {
        	conn = JdbcUtil.getConnection();

            stmt = conn.prepareStatement(query);
            stmt.setString(1, isbn);

            ResultSet rs = stmt.executeQuery(); // Il ResultSet rs riceve la risposta del database

            book = new Book();
            if (rs.next()) {
                book.setIsbn(rs.getString("ISBN"));
                book.setTitle(rs.getString("TITLE"));
                book.setAuthor(rs.getString("AUTHOR"));
                book.setYear(rs.getInt("YEAR"));

                Publisher publisher = new Publisher();
                publisher.setCode(rs.getString("PUBLISHER_CODE"));
                publisher.setName(rs.getString("PUB_NAME"));
                book.setPublisher(publisher);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(stmt);
        	JdbcUtil.closeConnection(conn);
        }
        return book;
    }

    public Book update(Book book) throws SQLException {
    	String query = "UPDATE BOOK SET TITLE = ? WHERE BOOK.ISBN = ?";
    	Connection conn = null;
    	PreparedStatement stmt = null;
        try {
            conn = JdbcUtil.getConnection();

            stmt = conn.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(stmt);
        	JdbcUtil.closeConnection(conn);
        }
        return book;
    }

    public void delete(Book book) throws SQLException {
    	String query = "DELETE FROM BOOK WHERE BOOK.ISBN = ?";
    	Connection conn = null;
    	PreparedStatement stmt = null;
        try {
        	conn = JdbcUtil.getConnection();

            stmt = conn.prepareStatement(query);
            stmt.setString(1, book.getIsbn());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(stmt);
        	JdbcUtil.closeConnection(conn);
        }
    }
    
    public void delete(Publisher publisher) throws SQLException {
    	String query = "DELETE FROM PUBLISHER WHERE CODE = ?";
    	Connection conn = null;
    	PreparedStatement stmt = null;
        try {
        	conn = JdbcUtil.getConnection();

            stmt = conn.prepareStatement(query);
            stmt.setString(1, publisher.getCode());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(stmt);
        	JdbcUtil.closeConnection(conn);
        }
    }
    
}