package client;

import java.beans.DefaultPersistenceDelegate;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.*;
import service.Service;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/client/addcart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stubtry{
		try {
			
			String method = request.getParameter("method");

			if (method.equals("add")) {
				AddtoCart(request, response);
			}
			if (method.equals("delete")) {
				DeletefromCart(request, response);
			}
		} catch (NullPointerException e) {
			request.setAttribute("message", "something error");
			request.getRequestDispatcher("/message").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void DeletefromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try{
		    String bookid = request.getParameter("bookid");
		    Service service = new Service();
		    Book book=service.findBook(bookid);
		    Cart cart=(Cart)request.getSession().getAttribute("cart");
		    
		    service.deletebookbyone(cart, book);
		   request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
	    }catch(Exception e){
		  e.printStackTrace();
		  request.setAttribute("message", "delete error");
		  request.getRequestDispatcher("/message.jsp").forward(request, response);
	   }
	}
	
	
	protected void AddtoCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try{
		    String bookid = request.getParameter("bookid");
		    Service service = new Service();
		    Book book = service.findBook(bookid);
		    Cart cart = (Cart) request.getSession().getAttribute("cart");
		    if(cart == null){
			cart = new Cart();
		    }
		    service.buyBook(cart, book);
		    request.getSession().setAttribute("cart", cart);
		   request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
	    }catch(Exception e){
		  e.printStackTrace();
		  request.setAttribute("message", "¹ºÂòÊ§°Ü");
		  request.getRequestDispatcher("/message.jsp").forward(request, response);
	   }
	}
}