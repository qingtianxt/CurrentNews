package service;

import java.sql.SQLException;
import java.util.List;

import dao.newsTypeDao;
import domain.newsType;

public class newsTypeService {
	/**
	 * 添加分类
	 * @param n
	 * @throws SQLException 
	 */
	public void add(newsType n) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		nd.add(n);
	}

	public List<newsType> getByParentId(int i) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		return nd.getByParentId(i);
	}
/**
 * 获取一个分类和他的所有一级子类
 * @param id
 * @return
 * @throws SQLException
 */
	public newsType getList(int id) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		newsType  n= new newsType();
		if(id!=0){
			n= nd.getById(id);
		}
		List<newsType> list = nd.getByParentId(id);
		for (newsType l : list) {
			System.out.println(l.toString());
			
		}
		n.setChildBeans(list);
		return n;
	}

}
