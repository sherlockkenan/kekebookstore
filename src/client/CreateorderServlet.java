package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.Order;
import entity.User;
import service.Service;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/client/createorder")
public class CreateorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		   User user=(User) request.getSession().getAttribute("user");
		   Cart cart=(Cart) request.getSession().getAttribute("cart");
		   if(user==null){
			   request.setAttribute("message", "login first");
		       request.getRequestDispatcher("/message.jsp").forward(request, response);
			    return;
		   }
		   Order order=new Order();
		   order.setUser(user);
		   Service service=new Service();
		   service.createOrder(cart,order);
		   request.getSession().removeAttribute("cart");
		   request.getRequestDispatcher("/client/order").forward(request, response);
		}
		catch(Exception e){
			request.setAttribute("message", "creat order fail");
			  request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
