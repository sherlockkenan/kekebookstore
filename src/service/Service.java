package service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import dao.*;

import entity.*;
import sun.reflect.generics.tree.VoidDescriptor;


public class Service{  
	
	//------------------------------------------------------user-----------------------------
     public User CheckUser(String username, String password) 
        throws Exception {
         Userdao userdao=new Userdao();
         User user = userdao.find(username, password);
    
    	 return user;
       }
     
     
     public void Register(User user)throws Exception{
    	 Userdao userdao=new Userdao();
    	 userdao.add(user);
     }
     
     public List<User> getalluser(){
    	 Userdao userdao=new Userdao();
    	 return userdao.getall();
     }
     
     public void deleteuser(String user_id){
    	 Userdao userdao=new Userdao();
    	 userdao.delete(user_id);
    	 return;
    	 
     }
     public void updateuser(User user){
    	 Userdao userdao=new Userdao();
    	 userdao.update(user);
    	 return;
     }
     
     public User searchuser(String username){
    	 Userdao userdao=new Userdao();
    	 
    	 return userdao.search(username);
     }
     
     //------------------------------------category------------------------------------------
     //get all catergory
     public List<Category> getAllCategory(){
    	 CategoryDao categorydao=new CategoryDao();
    	 return categorydao.getAll();
     }
     public void addCategory(Category category){
    	 CategoryDao categorydao=new CategoryDao();
    	 categorydao.add(category);
     }
     
     public void updatecategory(Category category){
    	 CategoryDao categorydao=new CategoryDao();
    	 categorydao.update(category);
     }
     
     public void deletecategory(String id){
    	 CategoryDao categorydao=new CategoryDao();
    	 categorydao.delete(id);
     }
    
     
     
     
    //------------------------------------------Page-----------------------------------------
   //获得分页数据
 	public Page getBookPageData(String pagenum){
 		Bookdao bookDao=new Bookdao();
 		int totalrecord = bookDao.getTotalRecord();
 		Page page = null;
 		if(pagenum == null){
 			page = new Page(1,totalrecord);
 		}else{
 			page = new Page(Integer.parseInt(pagenum), totalrecord);
 		}
 		List<Book> list = bookDao.getPageData(page.getStartindex(), page.getPagesize());
 		page.setList(list);
 		return page;
 	}
 	
 	public Page getBookPageData(String pagenum, String category_id){
 		Bookdao bookDao=new Bookdao();
 		int totalrecord = bookDao.getTotalRecord(category_id);
 		Page page = null;
 		if(pagenum == null){
 			page = new Page(1,totalrecord);
 		}else{
 			page = new Page(Integer.parseInt(pagenum), totalrecord);
 		}
 		List<Book> list = bookDao.getPageData(page.getStartindex(), page.getPagesize(), category_id);
 		page.setList(list);
 		return page;
 	}
 	
 	
 	//----------------------------------book-----------------------------------------
 	
 	public Book findBook(String bookid){
 		Bookdao bookdao=new Bookdao();
 		return bookdao.find(bookid);
 	}
 	
 	public void buyBook(Cart cart,Book book){
 		cart.add(book);
 	}
 	
 	public void addBook(Book book){
 		Bookdao bookdao=new Bookdao();
 		bookdao.add(book);
 	}
 	
 	public void deletebookbyone(Cart cart,Book book){
 		cart.deletebyone(book);
 	}
 	public void deletebook(String book_id){
 		Bookdao bookdao=new Bookdao();
 		bookdao.delete(book_id);
 	}
 
 	
 	
 	//----------------------------------------order---------------------------------------------------
 	public void createOrder(Cart cart,Order order){
 		String id=UUID.randomUUID().toString();
 		order.setId(id);
 		order.setPrice(cart.getPrice());
 		order.setState(false);
 		order.setOrdertime(new Date());
 		
 		for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
		
			CartItem citem = me.getValue();
			OrderItem oitem = new OrderItem();
			oitem.setBook(citem.getBook());
			oitem.setPrice(citem.getPrice());
			oitem.setId(UUID.randomUUID().toString());
			oitem.setQuantity(citem.getQuantity());
			order.getOrderitems().add(oitem);
		}
 		Orderdao orderdao=new Orderdao();
		orderdao.add(order);
 	}

 	// list the all order
 	public List<Order> Listorder(String user_id) {
    	Orderdao orderdao=new Orderdao();
    	return orderdao.getAllOrder(user_id);
		
	}
 	
 	public Order getOrder(String order_id) {
       Orderdao orderdao=new Orderdao();
       return orderdao.find(order_id);
	}
 	
 	public List<Order> Listorderbystate(String state) {
    	Orderdao orderdao=new Orderdao();
    	return orderdao.getAll(Boolean.parseBoolean(state));
		
	}
 	
 	public void confirmOrder(String order_id){
 		Orderdao orderdao=new Orderdao();
 		Order order=orderdao.find(order_id);
 		order.setState(true);
        orderdao.update(order);
    	return;
 	}
 	
 	public void deleteorder(String order_id){
 		Orderdao orderdao=new Orderdao();
 		orderdao.delete(order_id);
    	return;
 	}
 	
}
