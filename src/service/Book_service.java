package service;

import java.util.ArrayList;
import java.util.List;



import dao.Bookdao;

import entity.Book;
import entity.Cart;

import entity.Page;

public class Book_service {

	   private Bookdao bookdao;
	   
	 	public Bookdao getBookdao() {
		   return bookdao;
	  }
   
	    public void setBookdao(Bookdao bookdao) {
		  this.bookdao = bookdao;
	   }

		public Page getBookPageData(String pagenum){
	 		int totalrecord = bookdao.getTotalRecord();
	 		Page page = null;
	 		if(pagenum == null){
	 			page = new Page(1,totalrecord);
	 		}else{
	 			page = new Page(Integer.parseInt(pagenum), totalrecord);
	 		}
	 		List<Book> list = bookdao.getPageData(page.getStartindex(), page.getPagesize());
	 		page.setList(list);
	 		return page;
	 	}
	 	
	 	public Page getBookPageData(String pagenum, String category_id){
	 	
	 		int totalrecord = bookdao.getTotalRecord(category_id);
	 		Page page = null;
	 		if(pagenum == null){
	 			page = new Page(1,totalrecord);
	 		}else{
	 			page = new Page(Integer.parseInt(pagenum), totalrecord);
	 		}
	 		List<Book> list = bookdao.getPageData(page.getStartindex(), page.getPagesize(), category_id);
	 		page.setList(list);
	 		return page;
	 	}
	 	
	 	
	 	
	 	public Book findBook(String bookid){
	 	
	 		return bookdao.find(bookid);
	 	}
	 	
	 	public Page findBookbyname_page(String name){
		 	
	 		Book book= bookdao.findbyname(name);
	 		List<Book> list=new ArrayList<Book>();
	 		list.add(book);
	 		Page page=new Page(1,1);
	 		page.setList(list);
	 		return page;
	 	}
	 	
	 	public void buyBook(Cart cart,Book book){
	 		cart.add(book);
	 	}
	 	
	 	public void addBook(Book book){

	 		bookdao.add(book);
	 	}
	 	
	 	public void deletebookbyone(Cart cart,Book book){
	 		cart.deletebyone(book);
	 	}
	 	public void deletebook(String book_id){
	 		bookdao.delete(book_id);
	 	}
	 	public List<Book> getallbook() {
			return bookdao.getall();
		}
	 	public void updatebook(Book book) {
			bookdao.update(book);
		}
	 	
}
