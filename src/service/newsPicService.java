package service;

import java.sql.SQLException;
import java.util.List;

import constant.constant;
import dao.newsPicDao;
import dao.newsTypeDao;
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
 * @throws SQLException 
 */
	public pageBean<newsPic> listByPage(int currPage) throws SQLException {
		List<newsPic> list =null;
		newsPicDao nd = new newsPicDao();
		newsTypeDao nt = new newsTypeDao();
		int totalCount =nd.count();
		int pageSize = constant.NEWSPIC_PAGESIZE;
		list =nd.getByPage(currPage,pageSize);
		for (newsPic n : list) {
			n.setNewsType(nt.getById(n.getNewsType_id()));
		}
		pageBean<newsPic> page =new pageBean<>(list, totalCount, currPage, pageSize);
		return page;
	}
	/**
	 * 获取新闻的详细信息,包括新闻的分类
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public newsPic getById(int id) throws SQLException {
		newsPicDao nd = new newsPicDao();
		newsTypeDao ns =new newsTypeDao();
		newsPic n = nd.getById(id);
		n.setNewsType(ns.getById(n.getNewsType_id()));
		return n;
	}
	/**
	 * 更新图片新闻内容
	 * @param n
	 * @throws SQLException 
	 */
	public void update(newsPic n) throws SQLException {
		newsPicDao nd = new newsPicDao();
		nd.update(n);
	}
	/**
	 * 根据id删除图片新闻信息
	 * @param id
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException {
		newsPicDao nd = new newsPicDao();
		nd.delete(id);
	}
	/**
	 * 获取所有的新闻信息
	 * @return
	 * @throws SQLException 
	 */
	public pageBean<newsPic> getAllByPage(int currPage) throws SQLException {
		newsPicDao nd = new newsPicDao();
		int count = nd.count();
		int pageSize = constant.NEWSPIC_PAGESIZE;
		List<newsPic> list = nd.getByPage(currPage, pageSize);
		pageBean<newsPic> page =new pageBean<>(list, count, currPage, pageSize);
		return page;
	}
	
	
}
