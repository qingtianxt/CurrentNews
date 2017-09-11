package service;

import java.sql.SQLException;
import java.util.List;

import constant.constant;
import dao.newsPicDao;
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
		int totalCount =nd.count();
		int pageSize = constant.NEWSPIC_PAGESIZE;
		list =nd.getByPage(currPage,pageSize);
		pageBean<newsPic> page =new pageBean<>(list, totalCount, currPage, pageSize);
		return page;
	}
	/**
	 * ��ȡ���ŵ���ϸ��Ϣ
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public newsPic getById(int id) throws SQLException {
		newsPicDao nd = new newsPicDao();
		return nd.getById(id);
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
	
}
