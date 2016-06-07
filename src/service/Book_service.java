package service;

import java.util.List;

import dao.Bookdao;
import dao.CategoryDao;
import entity.Book;
import entity.Cart;
import entity.Category;
import entity.Page;

public class Book_service {
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
	 	
}
