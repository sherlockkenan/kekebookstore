package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.jdbcutils;
import entity.User;

public class Userdao{

	public void add(User user){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "insert into user(id,username,password,phone,address,email,role) values(?,?,?,?,?,?,?)";
			Object params[] = {user.getId(), user.getUsername(), user.getPassword(), user.getPhone(), user.getAddress(), user.getEmail(), user.getRole()};
			runner.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	

	public User find(String id){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from user where id=?";
			return (User)runner.query(sql,new BeanHandler(User.class),id);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public User search(String username){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from user where username=?";
			return (User)runner.query(sql,new BeanHandler(User.class),username);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public User find(String username, String password){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object params[] = {username, password};
			return (User)runner.query(sql, new BeanHandler(User.class), params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public void delete(String id){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "delete from user where id=?";
			Object params[]={id};
			runner.update(sql,params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public void update(User user){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "update user set username=?,password=?,phone=?,address=?,email=?,role=?where id=?";
			Object params[] = { user.getUsername(), user.getPassword(), user.getPhone(), user.getAddress(), user.getEmail(), user.getRole(),user.getId()};
			runner.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	
    public List<User> getall() {
	   try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from user" ;
			return (List<User>)runner.query(sql, new BeanListHandler(User.class));
		} catch(Exception e){
			throw new RuntimeException(e);
		}   
	
    }
}
