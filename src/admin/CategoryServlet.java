package admin;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
import entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//处理分类的CRUD请求
@WebServlet("/admin/category")
public class CategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("create")) {
			add(request, response);
		} else if (method.equals("delete")) {
			delete(request, response);
		} else if (method.equals("update")) {
			update(request, response);
		} else if (method.equals("find")) {
			find(request, response);
		} else if (method.equals("getall")) {
			getall(request, response);
		} else {
			request.setAttribute("message", "不支持此类操作");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	private void getall(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Service service = new Service();
		List<Category> CategoryList = service.getAllCategory();
		String jsonobjstr=JSONArray.fromObject(CategoryList).toString();
		response.getWriter().print(jsonobjstr);
	}

	private void find(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		String id = request.getParameter("id");
        Category category = new Category(id, name, description);
		
		Service service = new Service();
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

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
         String id=request.getParameter("id");
		
		//System.out.println(user_id);
		Service service=new Service();
		service.deletecategory(id);
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("success", true);
		response.getWriter().print(jsonobj.toString());

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");

			Category category = new Category();
			category.setName(name);
			category.setDescription(description);
			category.setId(UUID.randomUUID().toString());

			Service service = new Service();
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
