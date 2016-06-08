package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.Category;
import entity.Page;
import net.sf.json.JSONObject;
import service.Book_service;
import service.Category_service;

public class IndexAction extends ActionSupport {


	private static final long serialVersionUID = 1L;
	private Book_service book_service;
    private Category_service category_service;
	public Category_service getCategory_service() {
		return category_service;
	}


	public void setCategory_service(Category_service category_service) {
		this.category_service = category_service;
	}


	public Book_service getBook_service() {
		return book_service;
	}


	public void setBook_service(Book_service book_service) {
		this.book_service = book_service;
	}
	
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		  List<Category> categories = category_service.getAllCategory();
	      request.setAttribute("categories", categories);
	      String pagenum = request.getParameter("pagenum");
	      Page pages = book_service.getBookPageData(pagenum);
	      request.setAttribute("page", pages);
		return SUCCESS;
	}
	

	public String Category() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String category_id = request.getParameter("category_id");
		List<Category> categories = category_service.getAllCategory();
		request.setAttribute("categories", categories);
		String pagenum = request.getParameter("pagenum");
		Page page = book_service.getBookPageData(pagenum, category_id);
		request.setAttribute("page", page);
		return SUCCESS;

	}
	public void detail()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonobj=JSONObject.fromObject(request.getParameter("content"));
        System.out.println(jsonobj.toString());
        String bookid=jsonobj.get("id").toString();
        Book book=book_service.findBook(bookid);
        JSONObject result=JSONObject.fromObject(book);
        response.getWriter().print(result);
	}

}
