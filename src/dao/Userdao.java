package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import util.GetSessionFactory;
import entity.User;
import sun.security.timestamp.TSRequest;

public class Userdao{
	
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}


	public void add(User user){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.save(user);
			ts.commit();
			session.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	

	public User find(String id){
		try{
			//QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());			
			//return (User)runner.query(sql,new BeanHandler(User.class),id);
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from User where id=?";
			Query query=session.createQuery(sql);
			query.setString(0, id);
			User user=(User)query.uniqueResult();
			ts.commit();
			session.close();
			
			return user;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public User search(String username){
		try{
			//QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			//String sql = "select * from user where username=?";
			//return (User)runner.query(sql,new BeanHandler(User.class),username);
			
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from User as user where user.username=?";
			Query query=session.createQuery(sql);
			query.setString(0, username);
			User user=(User)query.uniqueResult();
			ts.commit();
			session.close();
			return user;
		
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public User find(String username, String password){
		try{

			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from User where username=? and password=?";
			Query query=session.createQuery(sql);
			query.setString(0, username);
			query.setString(1, password);
			User user=(User)query.uniqueResult();
			ts.commit();
			session.close();
			
			return user;
			
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public void delete(String id){
		try{
			//find the student
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from User where id=?";
			Query query=session.createQuery(sql);
			query.setString(0,id);
			//delete the student
			User user=(User)query.uniqueResult();
			session.delete(user);
			ts.commit();
			session.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public void update(User user){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.update(user);
			ts.commit();
			session.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	
    public List<User> getall() {
	   try{
			
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from User ";
			Query query=session.createQuery(sql);
			List<User> user=(List<User>)query.list();
			ts.commit();
			session.close();
			return user;
		} catch(Exception e){
			throw new RuntimeException(e);
		}   
	
    }
}
