package dao;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.GetSessionFactory;

import entity.Category;


public class Categorydao {
	
    private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public void add(Category category){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.save(category);
			ts.commit();
			session.close();
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	


	public Category find(String id){
		try {
		
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Category where id=?";
			Query query=session.createQuery(sql);
			query.setString(0, id);
			Category category=(Category)query.uniqueResult();
			ts.commit();
			session.close();
			return category;
		} catch (Exception e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	
	

	public List<Category> getAll(){
		try {
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Category";
			Query query=session.createQuery(sql);
			List<Category> category=(List<Category>)query.list();
			ts.commit();
			session.close();
			return category;
		} catch (Exception e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	
	
	public void delete(String id){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Category where id=?";
			Query query=session.createQuery(sql);
			query.setString(0,id);
			//delete the student
			Category category=(Category)query.uniqueResult();
			session.delete(category);
			ts.commit();
			session.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public void update(Category category){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.update(category);
			ts.commit();
			session.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
