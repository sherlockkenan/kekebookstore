package service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dao.Orderdao;
import entity.Cart;
import entity.CartItem;
import entity.Order;
import entity.OrderItem;

public class Order_service {
	  private Orderdao orderdao;
	
	  public Orderdao getOrderdao() {
	    return orderdao;
	  }

	  public void setOrderdao(Orderdao orderdao) {
		this.orderdao = orderdao;
	 }

		public void createOrder(Cart cart,Order order){
	 		String id=UUID.randomUUID().toString();
	 		order.setId(id);
	 		order.setPrice(cart.getPrice());
	 		order.setState(false);
	 		order.setOrdertime(new Date());
	 		
	 		for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
			
				CartItem citem = me.getValue();
				OrderItem oitem = new OrderItem();
				oitem.setBook(citem.getBook());
				oitem.setPrice(citem.getPrice());
				oitem.setId(UUID.randomUUID().toString());
				oitem.setQuantity(citem.getQuantity());
				order.getOrderitems().add(oitem);
			}
	 		
			orderdao.add(order);
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
