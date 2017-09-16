package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.Collect;
import utils.DataSourceUtils;

public class CollectDao {

	public List<Collect> getCollectByUser(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from collect where user_id =?";
		return qr.query(sql, new BeanListHandler<>(Collect.class),id);
	}
	/**
	 * 添加收藏
	 * @param c
	 * @throws SQLException 
	 */
	public void add(Collect c) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="insert into collect (news_id,user_id,collect_date) values(?,?,?)";
		qr.update(sql,c.getNews_id(),c.getUser_id(),c.getCollect_date());
	}
	public void delete(Collect c) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="delete from collect where news_id= ? and user_id=?";
		qr.update(sql,c.getNews_id(),c.getUser_id());
	}
	/**
	 * 获取用户id为id的用户的总收藏数
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public int count(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select count(*) from collect where user_id  =?";
		return (int) qr.query(sql, new ScalarHandler(),id);
	}
	
	public List<Collect> getByPage(int currPage, int pageSize, int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select top "+pageSize+" * from collect where user_id ="+id+" and  id not in (select top "+(currPage-1)*pageSize+" id from collect where user_id ="+id+" order by collect_date desc) order by collect_date desc";
		return qr.query(sql, new BeanListHandler<>(Collect.class));
	}
	/**
	 * 根据id 删除收藏
	 * @param id
	 * @throws SQLException 
	 */
	public void deleteById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="delete from collect where id =?";
		qr.update(sql,id);
	}

}
