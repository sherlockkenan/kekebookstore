package dao;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Book;



public class Bookdao {
private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public void add(Book book){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.save(book);
			ts.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void delete(String book_id){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Book where id=?";
			Query query=session.createQuery(sql);
			query.setString(0,book_id);
			//delete the book
			Book book=(Book)query.uniqueResult();
			session.delete(book);
			ts.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.BookDao#find(java.lang.String)
	 */
	public Book find(String id){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Book where id=?";
			Query query=session.createQuery(sql);
			query.setString(0, id);
			Book book=(Book)query.uniqueResult();
			ts.commit();
			session.close();
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Book> getPageData(int startindex, int pagesize){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Book";
			Query query=session.createQuery(sql);
			query.setFirstResult(startindex);
			query.setMaxResults(pagesize);
			List<Book> book=(List<Book>)query.list();
			ts.commit();
			session.close();
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int getTotalRecord(){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Book";
			Query query=session.createQuery(sql);
			int totalrecord=query.list().size();
			ts.commit();
			session.close();
			return totalrecord;
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
			
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Book where category_id=?";
			Query query=session.createQuery(sql);
			query.setString(0,category_id);
			query.setFirstResult(startindex);
			query.setMaxResults(pagesize);
			List<Book> book=(List<Book>)query.list();
			ts.commit();
			session.close();
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int getTotalRecord(String category_id){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Book where category_id=?";
			Query query=session.createQuery(sql);
			query.setString(0,category_id);
			int totalrecord=query.list().size();
			ts.commit();
			session.close();
			return totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
