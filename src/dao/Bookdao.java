package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import entity.Book;
import util.jdbcutils;

public class Bookdao {
	public void add(Book book){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "insert into book(id,name,author,price,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object params[] = {book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getImage(), book.getDescription(), book.getCategory_id()};
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void delete(String book_id){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "delete from book where id=?";
			Object params[] = {book_id};
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.BookDao#find(java.lang.String)
	 */
	public Book find(String id){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from book where id=?";
			return (Book)runner.query(sql, new BeanHandler(Book.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Book> getPageData(int startindex, int pagesize){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from book limit ?,?";
			Object params[] = {startindex, pagesize};
			return (List<Book>)runner.query(sql, new BeanListHandler(Book.class), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int getTotalRecord(){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select count(*) from book";
			long totalrecord = (Long)runner.query(sql, new ScalarHandler());
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.BookDao#getAll()
	 */
	public List<Book> getPageData(int startindex, int pagesize, String category_id){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from book where category_id=? limit ?,?";
			Object params[] = {category_id, startindex, pagesize};
			return (List<Book>)runner.query(sql, new BeanListHandler(Book.class), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int getTotalRecord(String category_id){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select count(*) from book where category_id=?";
			long totalrecord = (Long)runner.query(sql, new ScalarHandler(), category_id);
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
