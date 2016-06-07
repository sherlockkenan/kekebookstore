package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.*;



public class Orderdao {
	
    private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public void add(Order order){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.save(order);
			ts.commit();
			session.close();
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public Order find(String id){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Order where id=?";
			Query query=session.createQuery(sql);
			query.setString(0, id);
			Order order=(Order)query.uniqueResult();
			ts.commit();
			session.close();
			
			return order;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	//��̨��ȡ���ж���
	public List<Order> getAll(boolean state){
		try{
			
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Order where state=?";
			Query query=session.createQuery(sql);
			query.setBoolean(0,state);
			List<Order> order=(List<Order>)query.list();
			ts.commit();
			session.close();
			return order;
				
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//ǰ��ҳ���л�ȡĳ���û������ж���
	public List<Order> getAll(boolean state, String userid){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Order where state=? and User.id=?";
			Query query=session.createQuery(sql);
			query.setBoolean(0,state);
			query.setString(0, userid);
			List<Order> order=(List<Order>)query.list();
			ts.commit();
			session.close();
			return order;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> getAllOrder(String userid){
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Order where user_id=?";
			Query query=session.createQuery(sql);
			Userdao userdao=new Userdao();
			//User user=userdao.find(userid);
			//query.setEntity("user", user);
			query.setString(0, userid);
			List<Order> order=(List<Order>)query.list();
			ts.commit();
			session.close();
			return order;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public void update(Order order){//����ֻ�ı䷢��״̬��ʵ���л����Ըı乺��������������Ϣ������������
		try{
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			session.update(order);
			ts.commit();
			session.close();
		}   catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String id){
		try{
			//find the order
			Session session=sessionFactory.openSession();
			Transaction ts= session.beginTransaction();
			String sql = "from Order where id=?";
			Query query=session.createQuery(sql);
			query.setString(0,id);
			//delete the order
			Order order=(Order)query.uniqueResult();
			session.delete(order);
			ts.commit();
			session.close();
		}   catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
