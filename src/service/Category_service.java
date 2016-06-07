package service;

import java.util.List;

import dao.Categorydao;
import entity.Category;

public class Category_service {
	  //get all catergory
	private Categorydao categorydao;
	
    public Categorydao getCategorydao() {
		return categorydao;
	}
	public void setCategorydao(Categorydao categorydao) {
		this.categorydao = categorydao;
	}
	
	public List<Category> getAllCategory(){
   	 return categorydao.getAll();
    }
    public void addCategory(Category category){
   	 
   	 categorydao.add(category);
    }
    
    public void updatecategory(Category category){

   	 categorydao.update(category);
    }
    
    public void deletecategory(String id){

   	 categorydao.delete(id);
    }
}
