package client;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.Service;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	
		String username=request.getParameter("username");
		String password=request.getParameter("password");
	
		
		Service service= new Service();
		try{
			User user=service.CheckUser(username, password);
			if(user==null){
				throw new Exception("username or password error");
			}
			//登录成功，存储到session中
			request.getSession().setAttribute("user", user);
			
			if(user.getRole().equals("admin")){
				return "admin";
			}
			else{
			    return "client";
			}
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		
	}
}
