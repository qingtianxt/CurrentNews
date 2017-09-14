package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.user;
import utils.DataSourceUtils;

public class userDao {

	public void add(user u) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into [user] (username,password,salt,nickname,sex,email,status,code,create_date) values(?,?,?,?,?,?,?,?,?);";
		qr.update(sql,u.getUsername(),u.getPassword(),u.getSalt(),
				u.getNickname(),u.getSex(),u.getEmail(),
				u.getStatus(),u.getCode(),u.getCreate_date());
	}
	/**
	 * 
	 * @param u
	 * @throws SQLException 
	 */
	public void update(user u) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update [user] set username='"+u.getUsername()+"',password='"+u.getPassword()+"',salt='"+u.getSalt()+"',create_date='"+u.getCreate_date()+"',status="+u.getStatus()+",nickname='"+u.getNickname()+"',email='"+u.getEmail()+"',sex="+u.getSex()+",code="+u.getCode()+" where id="+u.getId();
		qr.update(sql);
	}
	/**
	 * 根据激活码获取用户信息
	 * @param code
	 * @return
	 * @throws SQLException 
	 */
	public user getByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from [user] where code = ?";
		return qr.query(sql, new BeanHandler<>(user.class),code);
	}
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public user getByUserName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "select * from [user] where username=?";
		return qr.query(sql, new BeanHandler<>(user.class),username);
	}
	/**
	 * 分页获取用户
	 * @param currPage
	 * @param pageSize
	 * @return
	 * @throws SQLException 
	 */
	public List<user> getByPage(int currPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select top "+pageSize+" * from [user] where id not in (select top "+(currPage-1)*pageSize+" id from [user])";
		return qr.query(sql, new BeanListHandler<>(user.class));
	}
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "select count(*) from [user]";
		return (int) qr.query(sql,new ScalarHandler());
	}
	public user getById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from [user] where id =?";
		return qr.query(sql, new BeanHandler<>(user.class),id);
	}
	
}
