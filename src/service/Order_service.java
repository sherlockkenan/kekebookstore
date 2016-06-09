package service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dao.Orderdao;
import dao.Userdao;
import entity.Cart;
import entity.CartItem;
import entity.Order;
import entity.OrderItem;
import entity.User;

public class Order_service {
	  private Orderdao orderdao;
	  private Userdao userdao;
	  public Userdao getUserdao() {
		return userdao;
	}

	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}

	public Orderdao getOrderdao() {
	    return orderdao;
	  }

	  public void setOrderdao(Orderdao orderdao) {
		this.orderdao = orderdao;
	 }

		public void createOrder(Cart cart,User user){
			Order order=new Order();
			order.setUser(user);
	 		String id=UUID.randomUUID().toString();
	 		order.setId(id);
	 		order.setPrice(cart.getPrice());
	 		order.setState(false);
	 		Date date=new Date(new java.util.Date().getTime());
	 		order.setOrdertime(date);
	 		
	 		for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
			
				CartItem citem = me.getValue();
				OrderItem oitem = new OrderItem();
				oitem.setBook(citem.getBook());
				oitem.setPrice(citem.getPrice());
				oitem.setId(UUID.randomUUID().toString());
				oitem.setQuantity(citem.getQuantity());
				order.getOrderitems().add(oitem);
			}
	 		user.getOrders().add(order);
	 	    userdao.update(user);
			//orderdao.add(order);
	 	}

	 	// list the all order
	 	public List<Order> Listorder(String user_id) {
	    	return orderdao.getAllOrder(user_id);
			
		}
	 	
	 	public Order getOrder(String order_id) {

	       return orderdao.find(order_id);
		}
	 	
	 	public List<Order> Listorderbystate(String state) {

	    	return orderdao.getAll(Boolean.parseBoolean(state));
			
		}
	 	
	 	public void confirmOrder(String order_id){
	 		Order order=orderdao.find(order_id);
	 		order.setState(true);
	        orderdao.update(order);
	    	return;
	 	}
	 	
	 	public void deleteorder(String order_id){
	 		orderdao.delete(order_id);
	    	return;
	 	}
}
