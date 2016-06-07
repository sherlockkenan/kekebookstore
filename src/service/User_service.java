package service;

import java.util.List;

import dao.Userdao;
import entity.User;

public class User_service {
private Userdao userdao;
	
	public Userdao getUserdao() {
		return userdao;
	}


	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}


	//------------------------------------------------------user-----------------------------
     public User CheckUser(String username, String password) 
        throws Exception {
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
}