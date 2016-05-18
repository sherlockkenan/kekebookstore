package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.deploy.LoginConfig;

import com.sun.net.httpserver.HttpServer;

import entity.User;

/**
 * Servlet Filter implementation class adminFilter
 */
@WebFilter("/admin/*")
public class adminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public adminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest  req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		User user=(User)req.getSession().getAttribute("user");
		if(user!=null&&"admin".equals(user.getRole())){
			chain.doFilter(req, resp);
		}
		else{
			resp.sendRedirect(req.getContextPath()+"/client/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
