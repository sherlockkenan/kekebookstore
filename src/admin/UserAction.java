package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Book;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.User_service;


/**
 * Servlet implementation class UserAction
 */

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
    private User_service user_service;
    

	public User_service getUser_service() {
		return user_service;
	}


	public void setUser_service(User_service user_service) {
		this.user_service = user_service;
	}


	public void get() throws Exception {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username=request.getParameter("username");
		if(username!=null){
			search();
			return;
		}
	
		List<User> users=user_service.getalluser();
		
		String jsonobjstr=JSONArray.fromObject(users).toString();
		response.getWriter().print(jsonobjstr);

	}
	
	
	public  void update() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String id = request.getParameter("id");
        User user = new User(id, username, password, phone, email, address,role);

		try {
			user_service.updateuser(user);
			JSONObject jsonobj=new JSONObject();
			jsonobj=JSONObject.fromObject(user);
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
		String user_id=request.getParameter("id");
		
		//System.out.println(user_id);

		user_service.deleteuser(user_id);
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("success", true);
		response.getWriter().print(jsonobj.toString());

	}
	
	public  void create() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String id =UUID.randomUUID().toString();
		
		
		User user = new User(id, username, password, phone, email, address,role);
		
	
		try {
			user_service.Register(user);
			JSONObject jsonobj=new JSONObject();
			jsonobj.put("success", true);
			response.getWriter().print(jsonobj.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("message", "×¢²áÊ§°Ü");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}
	
	public  void search() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username=request.getParameter("username");
	
		User user=user_service.searchuser(username);
	    List<User>userlist=new ArrayList<User>();
		userlist.add(user);
		
        JSONArray jsonArray=new JSONArray();
		jsonArray=JSONArray.fromObject(userlist);
		JSONObject rs=new JSONObject();
		if(user==null)
		{
			rs.put("total", 0);
		}
		else{
			rs.put("total", 1);
		}
		rs.put("rows",jsonArray);
		response.getWriter().print(rs.toString());
	}
}
