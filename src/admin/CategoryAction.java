package admin;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.Category_service;
import entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//处理分类的CRUD请求
//@WebServlet("/admin/category")
public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	

	public void getall() throws Exception {
	
		HttpServletResponse response = ServletActionContext.getResponse();
		Category_service service = new Category_service();
		List<Category> CategoryList = service.getAllCategory();
		String jsonobjstr=JSONArray.fromObject(CategoryList).toString();
		response.getWriter().print(jsonobjstr);
	}

	public void find() throws Exception {
	

		// TODO Auto-generated method stub

	}

	public void update() throws Exception {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		String id = request.getParameter("id");
        Category category = new Category(id, name, description);
		
        Category_service service = new Category_service();
		try {
			service.updatecategory(category);
			JSONObject jsonobj=new JSONObject();
			jsonobj=JSONObject.fromObject(category);
			response.getWriter().print(jsonobj.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("message", "update error");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}

	public void delete() throws Exception {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
         String id=request.getParameter("id");
		
		//System.out.println(user_id);
         Category_service service=new Category_service();
		service.deletecategory(id);
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("success", true);
		response.getWriter().print(jsonobj.toString());

	}

	public void add() throws Exception {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");

			Category category = new Category();
			category.setName(name);
			category.setDescription(description);
			category.setId(UUID.randomUUID().toString());

			Category_service service = new Category_service();
			service.addCategory(category);
			JSONObject jsonobj=new JSONObject();
			jsonobj.put("success", true);
			response.getWriter().print(jsonobj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	

}
