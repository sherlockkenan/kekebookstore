package dao;

import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.*;
import util.jdbcutils;


public class Orderdao {
	public void add(Order order){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			//1. 把order的基本信息保存到order表
			String sql = "insert into orders(id,ordertime,price,state,user_id) values(?,?,?,?,?)";
			Object params[] = {order.getId(), order.getOrdertime(), order.getPrice(), order.getState(), order.getUser().getId()};
			runner.update(sql, params);
			//2. 把order中的订单项保存到orderitem表中
			Set<OrderItem> set = order.getOrderitems();
			for(OrderItem item : set){
				sql = "insert into orderitem(id,quantity,price,order_id,book_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(), order.getId(), item.getBook().getId()};
				runner.update(sql, params);
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#find(java.lang.String)
	 */
	public Order find(String id){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			//1.找出订单的基本信息
			String sql = "select * from orders where id=?";
			Order order = (Order) runner.query(sql, new BeanHandler(Order.class), id);
			//2.找出订单中所有的订单项
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list =  (List<OrderItem>) runner.query(sql, new BeanListHandler(OrderItem.class), id);
			for(OrderItem item : list){
				sql = "select book.* from orderitem,book where orderitem.id=? and orderitem.book_id=book.id";
				Book book = (Book) runner.query(sql, new BeanHandler(Book.class), item.getId());
				item.setBook(book);
			}
			//把找出的订单项放进order
			order.getOrderitems().addAll(list);
			//3.找出订单属于哪个用户
			sql = "select * from orders,user where orders.id=? and orders.user_id=user.id";
			User user = (User) runner.query(sql, new BeanHandler(User.class), order.getId());
			order.setUser(user);
			return order;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#getAll(boolean)
	 */
	//后台获取所有订单
	public List<Order> getAll(boolean state){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list = (List<Order>) runner.query(sql, new BeanListHandler(Order.class), state);
			for(Order order : list){				
				//找出当前订单属于哪个用户
				sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";
				User user = (User) runner.query(sql, new BeanHandler(User.class), order.getId());
				order.setUser(user);
			} 
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//前端页面中获取某个用户的所有订单
	public List<Order> getAll(boolean state, String userid){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from orders where state=? and orders.user_id=?";
			Object params[] = {state, userid};
			List<Order> list = (List<Order>) runner.query(sql, new BeanListHandler(Order.class), params);
			//将所有该user加到list中
			for(Order order : list){
				sql = "select * from user where user.id=?";
				User user = (User) runner.query(sql, new BeanHandler(User.class), userid);
				order.setUser(user);
			}
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> getAllOrder(String userid){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from orders where user_id=?";
			List<Order> list = (List<Order>) runner.query(sql, new BeanListHandler(Order.class), userid);
			//将所有该user加到List中去
			for(Order order : list){
				sql = "select * from user where id=?";
				User user = (User) runner.query(sql, new BeanHandler(User.class), userid);
				order.setUser(user);
			}
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.OrderDao#update(domain.Order)
	 */
	public void update(Order order){//这里只改变发货状态，实际中还可以改变购买数量等其他信息，可以再完善
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "update orders set state=? where id=?";
			Object params[] = {order.getState(), order.getId()};
			runner.update(sql, params);
		}   catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String id){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "delete from orders where id=?";
			Object params[] = {id};
			runner.update(sql, params);
		}   catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
