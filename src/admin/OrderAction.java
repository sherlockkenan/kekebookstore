package admin;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.*;
import service.Order_service;




public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = 1L;


	
	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String order_id=request.getParameter("order_id");
		
		//System.out.println(user_id);
		Order_service service=new Order_service();
		service.deleteorder(order_id);
		
		request.setAttribute("message", "������ɾ��");
		return "delete";
	}
	
	public String get() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		 String state = request.getParameter("state");
		 Order_service service = new Order_service();
		 List<Order> orders = service.Listorderbystate(state);//������Ҫ��ø��û����ж�����Ϣ������ֻ��δ������(state==false)���ں�̨������δ�������ѷ�������ǰ̨Ҫ������һ��
		 request.setAttribute("orders", orders);

		 return "get";
	}
	
	public String confirm() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			
			String order_id = request.getParameter("order_id");
			Order_service service = new Order_service();
			service.confirmOrder(order_id);
			request.setAttribute("message", "������ȷ�ϣ��뼰ʱ����");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "ȷ��ʧ��");
	
		}
		return "confirm";
	}
	
	public String detail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String order_id = request.getParameter("order_id");
		Order_service service = new Order_service();
		Order order = service.getOrder(order_id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/admin/orderdetail.jsp").forward(request, response);
		return "detail";
	}

}
