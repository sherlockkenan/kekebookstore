package client;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.User_service;

public class RegisterAction extends ActionSupport {


	private static final long serialVersionUID = 1L;

	private User_service user_service;


	public User_service getUser_service() {
		return user_service;
	}

	public void setUser_service(User_service user_service) {
		this.user_service = user_service;
	}
	
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();		
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String cpassword = request.getParameter("cpassword");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String id =UUID.randomUUID().toString();
			if(!cpassword.equals(password)){
				request.setAttribute("message", "password no equal to cpassword");
				return ERROR;
			}
			User user = new User(id, username, cpassword, phone, email, address,"user");
						
		
			user_service.Register(user);
			request.setAttribute("message", "Register Succeed");
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Register Fail");
			return ERROR;
		}
		
	}
}