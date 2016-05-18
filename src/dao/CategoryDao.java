package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.jdbcutils;
import entity.Category;
import entity.User;

public class CategoryDao {
	
	/* (non-Javadoc)
	 * @see dao.impl.CategoryDao#add(domain.Category)
	 */
	public void add(Category category){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object params[] = {category.getId(), category.getName(), category.getDescription()};
			runner.update(sql, params);
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.CategoryDao#find(java.lang.String)
	 */

	public Category find(String id){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from category where id=?";
			return (Category)runner.query(sql, new BeanHandler(Category.class), id);
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.CategoryDao#getAll()
	 */

	public List<Category> getAll(){
		try {
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "select * from category";
			return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	
	
	public void delete(String id){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "delete from category where id=?";
			Object params[]={id};
			runner.update(sql,params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public void update(Category category){
		try{
			QueryRunner runner = new QueryRunner(jdbcutils.getDataSource());
			String sql = "update category set name=?,description=? where id=?";
			Object params[] = {category.getName(),category.getDescription(),category.getId()};
			runner.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
