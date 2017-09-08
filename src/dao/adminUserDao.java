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
 * �����û�����ѯ����Ա��Ϣ
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
 * ��ӹ���Ա
 * @param au
 * @throws SQLException 
 */
	public void add(adminUser au) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into adminUser (username,password,salt,create_date) values(?,?,?,?);";	//�ֺ���Ҫ����
	
		qr.update(sql,au.getUsername(),au.getPassword(),au.getSalt(),au.getCreate_date());
	}
	/**
	 * ��ҳ��ȡ����Ա
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
	 * �������Ա��������
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
	 * ����id��ȡ����Ա��Ϣ
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
	 * ���¹���Ա��Ϣ
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
	 * ɾ������Ա
	 * @param id
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from adminUser where id =?";
		qr.update(sql,id);
	}
	
}
