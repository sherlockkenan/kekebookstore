package client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.Cart;
import entity.Category;
import entity.Page;
import service.Book_service;

public class CartAction extends ActionSupport {


	private static final long serialVersionUID = 1L;


	public String add() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		 try{
			 

			    String bookid = request.getParameter("bookid");
			    Book_service service = new Book_service();
			    Book book = service.findBook(bookid);
			    Cart cart = (Cart) request.getSession().getAttribute("cart");
			    if(cart == null){
				cart = new Cart();
			    }
			    service.buyBook(cart, book);
			    request.getSession().setAttribute("cart", cart);
			    return SUCCESS;
		    }catch(Exception e){
			  e.printStackTrace();
			  request.setAttribute("message", "¹ºÂòÊ§°Ü");
	          return ERROR;
		   }
		}
	
	
	public String delete() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	    try{
		    String bookid = request.getParameter("bookid");
		    Book_service service = new Book_service();
		    Book book=service.findBook(bookid);
		    Cart cart=(Cart)request.getSession().getAttribute("cart");		    
		    service.deletebookbyone(cart, book);
		    return SUCCESS;
	    }catch(Exception e){
		  e.printStackTrace();
		  request.setAttribute("message", "delete error");
		  return ERROR;
	   }
	}
}


