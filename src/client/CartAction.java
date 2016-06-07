package client;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.Cart;

import service.Book_service;

public class CartAction extends ActionSupport {


	private static final long serialVersionUID = 1L;
    
	private Book_service book_service;

	public Book_service getBook_service() {
		return book_service;
	}


	public void setBook_service(Book_service book_service) {
		this.book_service = book_service;
	}


	public String add() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		 try{
			 

			    String bookid = request.getParameter("bookid");
			    Book book = book_service.findBook(bookid);
			    Cart cart = (Cart) request.getSession().getAttribute("cart");
			    if(cart == null){
				cart = new Cart();
			    }
			    book_service.buyBook(cart, book);
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
		    Book book=book_service.findBook(bookid);
		    Cart cart=(Cart)request.getSession().getAttribute("cart");		    
		    book_service.deletebookbyone(cart, book);
		    return SUCCESS;
	    }catch(Exception e){
		  e.printStackTrace();
		  request.setAttribute("message", "delete error");
		  return ERROR;
	   }
	}
}


