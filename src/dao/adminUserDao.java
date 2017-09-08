package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.adminUser;
import domain.pageBean;
import utils.DataSourceUtils;

public class adminUserDao {
/**
 * 根据用户名查询管理员信息
 * @param username
 * @return
 * @throws SQLException 
 */
	public adminUser getByUserName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from adminUser where username=?";
		return qr.query(sql, new BeanHandler<>(adminUser.class),username);
	}
/**
 * 添加管理员
 * @param au
 * @throws SQLException 
 */
	public void add(adminUser au) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into adminUser (username,password,salt,create_date) values(?,?,?,?);";	//分号需要加上
	
		qr.update(sql,au.getUsername(),au.getPassword(),au.getSalt(),au.getCreate_date());
	}
	/**
	 * 分页获取管理员
	 * @param currPage
	 * @param pageSize
	 * @return
	 * @throws SQLException 
	 */
	public List<adminUser> getByPage(int currPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql  ="select * from adminUser limit ?,?";
		String sql = "select top " + pageSize + " * from adminUser where id not in(select top " + (currPage-1)*pageSize + " id from adminUser)";
		return qr.query(sql, new BeanListHandler<>(adminUser.class));
	}
	/**
	 * 计算管理员的总数量
	 * @return
	 * @throws SQLException 
	 */
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from adminUser";
		int count = (int) qr.query(sql, new ScalarHandler());
//		int c = count.intValue();
		return count;
	}
	/**
	 * 根据id获取管理员信息
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public adminUser getById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from adminUser where id = ?";
		return qr.query(sql, new BeanHandler<>(adminUser.class),id);
	}
	/**
	 * 更新管理员信息
	 * @param au
	 * 
	 * @throws SQLException 
	 */
	public void update(adminUser au) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update adminUser set username='"+au.getUsername()+"',password='"+au.getPassword()+"',salt='"+au.getSalt()+"' where id ='"+au.getId()+"'";
		qr.update(sql);
	}
	/**
	 * 删除管理员
	 * @param id
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from adminUser where id =?";
		qr.update(sql,id);
	}
	
}
