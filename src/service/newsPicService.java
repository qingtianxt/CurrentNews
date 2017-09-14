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
	 * ���ͼƬ������Ϣ
	 * @param n
	 * @throws SQLException 
	 */
	public void add(newsPic n) throws SQLException {
		newsPicDao nd = new newsPicDao();
		nd.add(n);
	}
/**
 * ��ҳ��ȡ ͼƬ������Ϣ
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
	 * ��ȡ���ŵ���ϸ��Ϣ,�������ŵķ���
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
	 * ����ͼƬ��������
	 * @param n
	 * @throws SQLException 
	 */
	public void update(newsPic n) throws SQLException {
		newsPicDao nd = new newsPicDao();
		nd.update(n);
	}
	/**
	 * ����idɾ��ͼƬ������Ϣ
	 * @param id
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException {
		newsPicDao nd = new newsPicDao();
		nd.delete(id);
	}
	/**
	 * ��ȡ���е�������Ϣ
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
