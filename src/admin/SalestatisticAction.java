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
import java.sql.Date;
/**
 * Servlet implementation class SalestatisticAction
 */
//@WebServlet("/admin/salestatistic")
public class SalestatisticAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;
       
  
	public void byuser() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request= ServletActionContext.getRequest();
		String search=(String) request.getParameter("search");
		if(search!=null){
			usersearch();
			return;
		}
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
		HttpServletRequest request= ServletActionContext.getRequest();
		String search=(String) request.getParameter("search");
		if(search!=null){
			catesearch();
			return;
		}
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
		HttpServletRequest request= ServletActionContext.getRequest();
		String search=(String) request.getParameter("search");
		if(search!=null){
			daysearch();
			return;
		}
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
	public void bybook() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request= ServletActionContext.getRequest();
		String search=(String) request.getParameter("search");
		if(search!=null){
			booksearch();
			return;
		}
		Connection conn=  jdbcutils.getConnection();
		String sql ="select book.id as id, name,COUNT(*)as num from orderitem join book on book.id=orderitem.book_id GROUP BY name ORDER BY num desc";
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
	public void daysearch() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request= ServletActionContext.getRequest();
	    String start_year= (String) request.getParameter("start_year");
	    String start_month= (String) request.getParameter("start_month");
	    String start_day= (String) request.getParameter("start_day");
	    String end_year= (String) request.getParameter("end_year");
	    String end_month= (String) request.getParameter("end_month");
	    String end_day= (String) request.getParameter("end_day");

	    String startdate = start_year+"-"+start_month+"-"+start_day; 
	    String enddate=end_year+"-"+end_month+"-"+end_day;
	    
        Date start = Date.valueOf(startdate); 
        Date end =Date.valueOf(enddate);

		Connection conn=  jdbcutils.getConnection();
		String sql ="select date_format(ordertime, '%Y-%m-%d')as date,count(*) as num from orders where ordertime>='"+start+"' and "   
                    +"ordertime<='"+end+"' group by date_format(ordertime, '%Y-%m-%d') ORDER BY ordertime desc";
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
	
	public void usersearch() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request= ServletActionContext.getRequest();
		String username= (String) request.getParameter("username");
		String sql ="select byuser('"+username+"') as num";
		
		Connection conn=  jdbcutils.getConnection();
        ResultSet rs= conn.createStatement().executeQuery(sql);
	    JSONArray jsonarry=new JSONArray();
	    JSONObject jsonobj=new JSONObject();
        while (rs.next()){
    	    jsonobj.put("username", username);
    	    jsonobj.put("num", String.valueOf(rs.getInt("num")));
    	    jsonarry.add(jsonobj);
        }
    	response.getWriter().print(jsonarry);
	}
	public void booksearch() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request= ServletActionContext.getRequest();
		String bookname= (String) request.getParameter("bookname");
		String sql ="select bybook('"+bookname+"') as num";
		
		Connection conn=  jdbcutils.getConnection();
        ResultSet rs= conn.createStatement().executeQuery(sql);
	    JSONArray jsonarry=new JSONArray();
	    JSONObject jsonobj=new JSONObject();
        while (rs.next()){
    	    jsonobj.put("name", bookname);
    	    jsonobj.put("num", String.valueOf(rs.getInt("num")));
    	    jsonarry.add(jsonobj);
        }
    	response.getWriter().print(jsonarry);
	}
	public void catesearch() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request= ServletActionContext.getRequest();
		String catename= (String) request.getParameter("categoryname");
		String sql ="select bycate('"+catename+"') as num";
		
		Connection conn=  jdbcutils.getConnection();
        ResultSet rs= conn.createStatement().executeQuery(sql);
	    JSONArray jsonarry=new JSONArray();
	    JSONObject jsonobj=new JSONObject();
        while (rs.next()){
    	    jsonobj.put("name", catename);
    	    jsonobj.put("num", String.valueOf(rs.getInt("num")));
    	    jsonarry.add(jsonobj);
        }
    	response.getWriter().print(jsonarry);
	}
}
