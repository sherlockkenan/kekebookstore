package service;

import java.util.List;

import dao.CategoryDao;
import entity.Category;

public class Category_service {
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
}
