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

import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.Service;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method=request.getParameter("method");
		if(method.equals("getall")){
			try{
				String username=request.getParameter("username");
				if(username.equals("")){
					
					get_user(request,response);
				}
				else{
					search_user(request, response);
				}
			}catch(Exception e){
			    get_user(request,response);
			}
		}
		if(method.equals("update")){
			update_user(request,response);
		}
        if(method.equals("create")){
        	create_user(request,response);
		}
        if(method.equals("delete")){
        	delete_user(request,response);
        }
        if(method.equals("search")){
        	search_user(request,response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void get_user(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		Service service=new Service();
		List<User> users=service.getalluser();
		
		String jsonobjstr=JSONArray.fromObject(users).toString();
		response.getWriter().print(jsonobjstr);
	}
	
	
	protected void update_user(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String id = request.getParameter("id");
        User user = new User(id, username, password, phone, email, address,role);
		
		Service service = new Service();
		try {
			service.updateuser(user);
			JSONObject jsonobj=new JSONObject();
			jsonobj=JSONObject.fromObject(user);
			response.getWriter().print(jsonobj.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("message", "update error");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
	
	
	
	protected void delete_user(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String user_id=request.getParameter("id");
		
		//System.out.println(user_id);
		Service service=new Service();
		service.deleteuser(user_id);
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("success", true);
		response.getWriter().print(jsonobj.toString());
	}
	
	protected void create_user(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String id =UUID.randomUUID().toString();
		
		
		User user = new User(id, username, password, phone, email, address,role);
		
		Service service = new Service();
		try {
			service.Register(user);
			JSONObject jsonobj=new JSONObject();
			jsonobj.put("success", true);
			response.getWriter().print(jsonobj.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("message", "×¢²áÊ§°Ü");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}
	
	protected void search_user(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		String username=request.getParameter("username");
		Service service=new Service();
		User user=service.searchuser(username);
	    List<User>userlist=new ArrayList<User>();
		userlist.add(user);
		
        JSONArray jsonArray=new JSONArray();
		jsonArray=JSONArray.fromObject(userlist);
		JSONObject rs=new JSONObject();
		rs.put("total", 1);
		rs.put("rows",jsonArray);
		response.getWriter().print(rs.toString());
	}

}
