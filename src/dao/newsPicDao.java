package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.newsPic;
import utils.DataSourceUtils;

public class newsPicDao {
/**
 * 添加图片新闻信息
 * @param n
 * @throws SQLException 
 */
	public void add(newsPic n) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into newsPic (title,publisher,username,pic,info,create_date) values (?,?,?,?,?,?); ";
		qr.update(sql,n.getTitle(),n.getPublisher(),n.getUsername(),n.getPic(),n.getInfo(),n.getCreate_date());
	}
/**
 * 计算图片新闻的总条数
 * @return
 * @throws SQLException 
 */
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from newsPic";
		return (int)qr.query(sql, new ScalarHandler());
	}
	public List<newsPic> getByPage(int currPage, int pageSize) {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "";
		return null;
	}

}
