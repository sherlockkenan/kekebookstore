package client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Cart;
import entity.Order;
import entity.User;
import service.Order_service;

public class OrderAction  extends ActionSupport {


	private static final long serialVersionUID = 1L;

	public String list() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	
		try{
			User user = (User)request.getSession().getAttribute("user");
			String user_id=user.getId();
			Order_service service = new Order_service();
			List<Order> orders = service.Listorder(user_id);
			request.setAttribute("orders", orders);
		    return "list";
			}catch(Exception e){
				return ERROR;
			}
	}
	
	public String detail() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	
		String order_id=request.getParameter("order_id");
		Order_service service=new Order_service();
		Order order=service.getOrder(order_id);
		request.setAttribute("order", order);
		return "detail";
	}
	
	public String create() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	
		try{
			   User user=(User) request.getSession().getAttribute("user");
			   Cart cart=(Cart) request.getSession().getAttribute("cart");
			   if(user==null){
				   request.setAttribute("message", "login first");
			   
				    return ERROR;
			   }
			   Order order=new Order();
			   order.setUser(user);
			   Order_service service=new Order_service();
			   service.createOrder(cart,order);
			   request.getSession().removeAttribute("cart");
			   return "create";
			}
			catch(Exception e){
				request.setAttribute("message", "creat order fail");
				return ERROR;
			}
	}
}
