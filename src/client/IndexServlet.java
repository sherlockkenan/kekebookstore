package client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Category;
import entity.Page;
import service.Service;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/client/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		    String method=request.getParameter("method");
				
		
		if(method.equals("Category")){
			listBookWithCategory(request,response);
		}
		else{
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);
		}
	   }catch(NullPointerException e){
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);
		}
   }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	
	public void listBookWithCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Service service = new Service();
		String category_id = request.getParameter("category_id");
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		String pagenum = request.getParameter("pagenum");
		Page page = service.getBookPageData(pagenum, category_id);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

}
