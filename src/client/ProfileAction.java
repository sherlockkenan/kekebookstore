package client;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.opensymphony.xwork2.ActionSupport;

import entity.Category;
import entity.Page;
import entity.User;
import net.sf.json.JSONObject;
import service.User_service;
import util.mongodbutil;

public class ProfileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File upload;
	private User_service user_service;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public User_service getUser_service() {
		return user_service;
	}

	public void setUser_service(User_service user_service) {
		this.user_service = user_service;
	}

	public void getimage()throws Exception{
	    DB database=mongodbutil.getdb();
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		HttpServletResponse response=ServletActionContext.getResponse();
	    DBCollection collection=database.getCollection("image");
		
	    DBObject db= collection.findOne(new BasicDBObject("user", user.getId()));
		byte[] byteImg=(byte[]) db.get("image");
		ServletOutputStream sout = response.getOutputStream();
		sout.write(byteImg);
		sout.close();

	}



	public String imageedit() throws Exception {
		DB database = mongodbutil.getdb();
		HttpServletRequest request = ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		
		FileInputStream in = new FileInputStream(upload);
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		
		byte[] temp = new byte[1024];

		int size = 0;

		while ((size = in.read(temp)) != -1) {
			out.write(temp, 0, size);
		}

		in.close();
		byte[]byteImg = out.toByteArray();
	
		DBCollection collection = database.getCollection("image");
		collection.update(new BasicDBObject("user",user.getId()), new BasicDBObject("$set", new BasicDBObject("image",byteImg)),true,true); 
		return SUCCESS;
	}
	public String useredit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();		
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

        User user =(User) request.getSession().getAttribute("user");
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
		try {
			user_service.updateuser(user);
			return SUCCESS;
		} catch (Exception e) {
			request.setAttribute("message", "update error");
			return ERROR;
		}
	}
}
