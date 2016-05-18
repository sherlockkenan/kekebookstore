package client;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.sound.FFT;

import service.*;
import util.jdbcutils;
import entity.*;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
				response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
			}
			else{
			    response.sendRedirect(request.getContextPath()+"/client/index.jsp");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			response.getWriter().println("username or password error");
			//response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
