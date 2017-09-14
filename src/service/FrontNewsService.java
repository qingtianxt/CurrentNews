package service;

import java.sql.SQLException;
import java.util.List;

import constant.constant;
import dao.FrontnewsDao;
import domain.newsPic;
import domain.pageBean;

public class FrontNewsService {
/**
 * ���ݷ����ҳ��ȡ������Ϣ
 * @param typeId
 * @param currPage
 * @return
 * @throws SQLException 
 */
	public pageBean<newsPic> getTypeNewsByPage(int typeId, int currPage) throws SQLException {
		FrontnewsDao fd = new FrontnewsDao();
		int totalCount=fd.countType(typeId);
		int pageSize = constant.NEWS_TYPE_PAGESIZE;
		List<newsPic> list = fd.getTypeNewsByPage(typeId,currPage,pageSize);
		pageBean<newsPic> page =new pageBean<>(list, totalCount, currPage, pageSize);
		return page;
	}
/**
 * ��ȡ��������ǰ����
 * @return
 * @throws SQLException 
 */
	public List<newsPic> getTodayNewstf() throws SQLException {
		FrontnewsDao fd =new FrontnewsDao();
		return fd.getTodayNewstf();
	}
/**
 * ��ȡ��������ǰ����	
 * @return
 * @throws SQLException 
 */
	public List<newsPic> getWorldNewstf() throws SQLException {
		FrontnewsDao fd =new FrontnewsDao();
		return fd.getWorldNewstf();
	}
	/**
	 * ��������
	 * @param title
	 * @param currPage
	 * @return
	 * @throws SQLException 
	 */
	public pageBean<newsPic> search(String title, int currPage) throws SQLException {
		FrontnewsDao fd =new FrontnewsDao();
		int pageSize = constant.NEWSPIC_PAGESIZE;
		System.out.println(currPage+"   "+pageSize);
		List<newsPic> list =fd.search(title,currPage,pageSize);
		int totalCount=0;
		System.out.println(list);
		for (newsPic newsPic : list) {
			totalCount++;
		}
		pageBean<newsPic> page =new pageBean<>(list, totalCount, currPage,pageSize);
		return page;
	}

}
