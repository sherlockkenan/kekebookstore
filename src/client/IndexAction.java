package client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Category;
import entity.Page;
import service.Service;

public class IndexAction extends ActionSupport {


	private static final long serialVersionUID = 1L;

	public String Category() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	
		Service service = new Service();
		String category_id = request.getParameter("category_id");
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		String pagenum = request.getParameter("pagenum");
		Page page = service.getBookPageData(pagenum, category_id);
		request.setAttribute("page", page);
		return SUCCESS;

	}

}
