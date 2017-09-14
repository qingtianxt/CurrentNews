package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.newsPic;
import utils.DataSourceUtils;
import utils.DateUtils;

public class FrontnewsDao {
/**
 * 统计某个分类的所有新闻
 * @param typeId
 * @return
 * @throws SQLException
 */
	public int countType(int typeId) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from newsPic where newsType_id=?";
		return (int)qr.query(sql,new ScalarHandler(), typeId);
	}
/**
 * 根据分类分页获取数据
 * @param typeId
 * @param currPage
 * @param pageSize
 * @return
 * @throws SQLException 
 */
	public List<newsPic> getTypeNewsByPage(int typeId, int currPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select top "+pageSize+" * from newsPic where newsType_id="+typeId+" and id not in (select top "+(currPage-1)*pageSize+" id from newsPic where newsType_id="+typeId+" )";
		return qr.query(sql, new BeanListHandler<>(newsPic.class));
	}
	/**
	 * 获取国际新闻前四条
	 * @return
	 * @throws SQLException 
	 */
	public List<newsPic> getWorldNewstf() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select top "+4+" * from newsPic where newsType_id="+14+" and id not in (select top "+0+" id from newsPic where newsType_id="+14+" )";
		
		return qr.query(sql, new BeanListHandler<>(newsPic.class));
	}
	/**
	 * 获取今日新闻前四条
	 * @return
	 * @throws SQLException 
	 */
	public List<newsPic> getTodayNewstf() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		String date1=date+" 00:00:00.000";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select top "+4+" * from newsPic where publish_date>='"+date1+"' and id not in (select top "+0+" id from newsPic where publish_date>='"+date1+"' )";
		return qr.query(sql, new BeanListHandler<>(newsPic.class));
	}
	/**
	 * 
	 * @param title
	 * @param currPage
	 * @param pageSize 
	 * @return
	 * @throws SQLException 
	 */
	public List<newsPic> search(String title, int currPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		System.out.println("title " + title);
		String sql = "select top "+pageSize+" * from newsPic where title like '%"+title+"%' and id not in (select top "+(currPage-1)*pageSize+" id from newsPic where title like '%"+title+"%' )";
		return qr.query(sql, new BeanListHandler<>(newsPic.class));
	}

}
