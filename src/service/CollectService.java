package service;

import java.sql.SQLException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import constant.constant;
import dao.CollectDao;
import dao.newsPicDao;
import domain.Collect;
import domain.pageBean;

public class CollectService {
/**
 * ����ղ�
 * @param c
 * @throws SQLException 
 */
	public void add(Collect c) throws SQLException {
		CollectDao cd =new CollectDao();
		cd.add(c);
	}
/**
 * ɾ���ղ�
 * @param c
 * @throws SQLException 
 */
	public void delete(Collect c) throws SQLException {
		CollectDao cd =new CollectDao();
		cd.delete(c);
	}
	/**
	 * �����û�id��ȡ�û��ղص�����
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Collect> getCollect(int id) throws SQLException {
		CollectDao cd =new CollectDao();
		List<Collect> collectByUser = cd.getCollectByUser(id);
		return collectByUser;
		}
	/**
	 * �����û�id�͵�ǰҳ��ҳ��ȡ�ղص�����
	 * @param id
	 * @param currPage
	 * @return
	 * @throws SQLException 
	 */
	public pageBean<Collect> getCollectByPage(int id, int currPage) throws SQLException {
		CollectDao cd =new CollectDao();
		newsPicDao nd =new newsPicDao();
		int totalCount =cd.count(id);
		int pageSize = constant.COLLECT_PAGESIZE;
		List<Collect> list =cd.getByPage(currPage,pageSize,id);
		for (Collect c : list) {
			c.setNewsPic(nd.getById(c.getNews_id()));
		}
		pageBean<Collect> page= new pageBean<>(list, totalCount, currPage, pageSize);
		return page;
	}
	public void deleteById(int id) throws SQLException {
		CollectDao cd =new CollectDao();
		cd.deleteById(id);
	}

}
