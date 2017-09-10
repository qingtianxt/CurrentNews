package service;

import java.sql.SQLException;
import java.util.List;

import constant.constant;
import dao.newsPicDao;
import domain.newsPic;
import domain.pageBean;

public class newsPicService {
	/**
	 * 添加图片新闻信息
	 * @param n
	 * @throws SQLException 
	 */
	public void add(newsPic n) throws SQLException {
		newsPicDao nd = new newsPicDao();
		nd.add(n);
	}
/**
 * 分页获取 图片新闻信息
 * @param currPage
 * @return
 */
	public pageBean<newsPic> listByPage(int currPage) {
		List<newsPic> list =null;
		newsPicDao nd = new newsPicDao();
		int totalCount =nd.count();
		int pageSize = constant.NEWSPIC_PAGESIZE;
		list =nd.getByPage(currPage,pageSize);
		pageBean<newsPic> page =new pageBean<>(list, totalCount, currPage, pageSize);
		return page;
	}
	
}
