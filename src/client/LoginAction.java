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
	private Service service;



	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
	
		String username=request.getParameter("username");
		String password=request.getParameter("password");
	
		
		
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
	
	public String logout() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		request.getSession().removeAttribute("user");
		return SUCCESS;
	}
}
