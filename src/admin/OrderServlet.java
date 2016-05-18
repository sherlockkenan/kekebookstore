package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.*;
import net.sf.json.JSONObject;
import service.Service;



@WebServlet("/admin/order")
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
		    String method=request.getParameter("method");
		    if(method.equals("get"))
		    {
		    	get_order(request,response);
		    }
		    if(method.equals("confirm"))
		    {
		    	confirm_order(request, response);
		    }
		    if(method.equals("delete"))
		    {
		    	delete_order(request,response);
		    }
		    if(method.equals("detail"))
		    {
		    	detail_order(request,response);
		    }
		    
		   
		}catch(Exception e){
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	protected void delete_order(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String order_id=request.getParameter("order_id");
		
		//System.out.println(user_id);
		Service service=new Service();
		service.deleteorder(order_id);
		
		request.setAttribute("message", "订单已删除");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		//JSONObject jsonobj=new JSONObject();
		//jsonobj.put("success", true);
		//response.getWriter().print(jsonobj.toString());
	}
	
	protected void get_order(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		 String state = request.getParameter("state");
		 Service service = new Service();
		 List<Order> orders = service.Listorderbystate(state);//这里需要获得该用户所有订单消息，不用只看未发货的(state==false)，在后台会区分未发货和已发货，在前台要罗列在一起
		 request.setAttribute("orders", orders);
		 request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}
	
	protected void confirm_order(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String order_id = request.getParameter("order_id");
			Service service = new Service();
			service.confirmOrder(order_id);
			request.setAttribute("message", "订单已确认，请及时发货");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "确认失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
	
	protected void detail_order(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		Service service = new Service();
		Order order = service.getOrder(order_id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/admin/orderdetail.jsp").forward(request, response);
	}

}
