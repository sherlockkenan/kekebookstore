package admin;


import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import util.jdbcutils;

import java.sql.Connection;
/**
 * Servlet implementation class SalestatisticAction
 */
//@WebServlet("/admin/salestatistic")
public class SalestatisticAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;
       
  
	public void byuser() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
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
	
	public void bycategory() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
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
	

	public void byday() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
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
	public void bymonth() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
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
	
	public void byyear() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
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
