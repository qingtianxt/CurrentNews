package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.newsPic;
import utils.DataSourceUtils;

public class newsPicDao {
	/**
	 * 添加图片新闻信息
	 * 
	 * @param n
	 * @throws SQLException
	 */
	public void add(newsPic n) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into newsPic (title,publisher,username,pic,info,create_date,newsType_id,publish_date) values (?,?,?,?,?,?,?,?); ";
		qr.update(sql, n.getTitle(), n.getPublisher(), n.getUsername(), n.getPic(), n.getInfo(), n.getCreate_date(),n.getNewsType_id(),n.getPublish_date());
	}

	/**
	 * 计算图片新闻的总条数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from newsPic";
		return (int) qr.query(sql, new ScalarHandler());
	}

	public List<newsPic> getByPage(int currPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select top " + pageSize + " * from newsPic where id not in(select top "
				+ (currPage - 1) * pageSize + " id from newsPic)";
		return qr.query(sql, new BeanListHandler<>(newsPic.class));
	}

	/**
	 * 通过id获取图片新闻信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public newsPic getById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from newsPic where id = ?";
		return qr.query(sql, new BeanHandler<>(newsPic.class), id);
	}

	/**
	 * 更新图片新闻内容
	 * 
	 * @param n
	 * @throws SQLException 
	 */
	public void update(newsPic n) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update newsPic set title='" + n.getTitle()+ "',publisher='" + n.getPublisher() + "',username='"
				+ n.getUsername() + "',pic='" + n.getPic() + "',info='" + n.getInfo() + "',newsType_id='"+n.getNewsType_id()+"',publish_date='"+n.getPublish_date()+"' where id ="+n.getId();
		qr.update(sql);
	}
/**
 * 删除图片新闻内容
 * @param id
 * @throws SQLException 
 */
	public void delete(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from newsPic where id = ?";
		qr.update(sql,id);
	}

}
