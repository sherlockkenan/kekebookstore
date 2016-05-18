package admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;



import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import util.jdbcutils;

import java.sql.Connection;
/**
 * Servlet implementation class SalestatisticServlet
 */
@WebServlet("/admin/salestatistic")
public class SalestatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalestatisticServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String method=request.getParameter("method");
			if(method.equals("byuser")){
				Orderbyuser(request,response);
			}
			if(method.equals("bycategory")){
				Orderbycategory(request,response);
			}
			if(method.equals("byday")){
				Orderbyday(request,response);
			}
			if(method.equals("bymonth")){
				Orderbymonth(request,response);
			}
			if(method.equals("byyear")){
				Orderbyyear(request,response);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void Orderbyuser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException {
		Connection conn=  jdbcutils.getConnection();
		String sql = "select user.username,user.id, temp.num from user natural join "
				+ "(select user_id as id,count(*)as num from orders group by user_id) as temp order by temp.num desc";
	    ResultSet rs= conn.createStatement().executeQuery(sql);
		JSONArray jsonarry=new JSONArray();
		JSONObject jsonobj=new JSONObject();
	    while (rs.next()){
	    	 jsonobj.put("user_id", rs.getString("id"));
	    	 jsonobj.put("username", rs.getString("username"));
	    	 jsonobj.put("num", String.valueOf(rs.getInt("num")));
	    	 jsonarry.add(jsonobj);
	    }
        
	
		response.getWriter().print(jsonarry);
	}
	
	protected void Orderbycategory(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException {
		Connection conn=  jdbcutils.getConnection();
		String sql ="select category.id,category.name,num "+
				    "from category natural join "+
	                "(select book.category_id as id,num from book natural join "+
				     "(select book_id as id,sum(quantity) as num from orderitem group by book_id) as temp)as temp2 "+
				    "order by num desc";
	    ResultSet rs= conn.createStatement().executeQuery(sql);
		JSONArray jsonarry=new JSONArray();
		JSONObject jsonobj=new JSONObject();
	    while (rs.next()){
	    	 jsonobj.put("id", rs.getString("id"));
	    	 jsonobj.put("name", rs.getString("name"));
	    	 jsonobj.put("num", String.valueOf(rs.getInt("num")));
	    	 jsonarry.add(jsonobj);
	    }
        
	
		response.getWriter().print(jsonarry);
	}
	

	protected void Orderbyday(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException {
		Connection conn=  jdbcutils.getConnection();
		String sql ="select count(*) as num,date_format(ordertime, '%Y-%m-%d')as date from orders group by date_format(ordertime, '%Y-%m-%d') "
				+ "order by num desc";
	    ResultSet rs= conn.createStatement().executeQuery(sql);
		JSONArray jsonarry=new JSONArray();
		JSONObject jsonobj=new JSONObject();
	    while (rs.next()){
	    	 
	    	 jsonobj.put("date", rs.getString("date"));
	    	 jsonobj.put("num", String.valueOf(rs.getInt("num")));
	    	 jsonarry.add(jsonobj);
	    }
        
	
		response.getWriter().print(jsonarry);
	}
	protected void Orderbymonth(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException {
		Connection conn=  jdbcutils.getConnection();
		String sql ="select count(*) as num,date_format(ordertime, '%Y-%m')as date from orders group by date_format(ordertime, '%Y-%m')"
			    +" order by num desc";
	    ResultSet rs= conn.createStatement().executeQuery(sql);
		JSONArray jsonarry=new JSONArray();
		JSONObject jsonobj=new JSONObject();
	    while (rs.next()){
	    	 jsonobj.put("date", rs.getString("date"));
	    	 jsonobj.put("num", String.valueOf(rs.getInt("num")));
	    	 jsonarry.add(jsonobj);
	    }
        
	
		response.getWriter().print(jsonarry);
	}
	
	protected void Orderbyyear(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException {
		Connection conn=  jdbcutils.getConnection();
		String sql ="select count(*) as num,date_format(ordertime, '%Y')as date from orders group by date_format(ordertime, '%Y')"
			    +" order by num desc";
	    ResultSet rs= conn.createStatement().executeQuery(sql);
		JSONArray jsonarry=new JSONArray();
		JSONObject jsonobj=new JSONObject();
	    while (rs.next()){
	    	 jsonobj.put("date", rs.getString("date"));
	    	 jsonobj.put("num", String.valueOf(rs.getInt("num")));
	    	 jsonarry.add(jsonobj);
	    }
        
	
		response.getWriter().print(jsonarry);
	}
	
}
