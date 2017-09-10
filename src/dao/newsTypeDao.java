package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.newsType;
import utils.DataSourceUtils;

public class newsTypeDao {
/**
 * ������ŷ���
 * @param n
 * @throws SQLException 
 */
	public void add(newsType n) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "insert into newsType (name,parentId,intro,create_date) values(?,?,?,?); ";
		qr.update(sql,n.getName(),n.getParentId(),n.getIntro(),n.getCreate_date());
	}
/**
 * ��ȡ������Ϊi�������ӷ���
 * @param i
 * @return
 * @throws SQLException 
 */
	public List<newsType> getByParentId(int i) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from newsType where parentId = ?";
		return qr.query(sql, new BeanListHandler<>(newsType.class),i);
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public newsType getById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from newsType where id = ?";
		return qr.query(sql, new BeanHandler<>(newsType.class),id);
	}
	/**
	 * ���·���
	 * @param n
	 * @return
	 * @throws SQLException 
	 */
	public Object update(newsType n) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update newsType set name='"+n.getName()+"',parentId='"+n.getParentId()+"',intro='"+n.getIntro()+"' where id='"+n.getId()+"'";
		return qr.update(sql);
	}
	/**
	 * ɾ��һ������
	 * @param id
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from newsType where id = ?;";
		qr.update(sql,id);
	}

}
