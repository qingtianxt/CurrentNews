package service;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import constant.constant;
import dao.CollectDao;
import dao.userDao;
import domain.Collect;
import domain.pageBean;
import domain.user;
import utils.MailUtils;

public class userService {
/**
 * ע���û�
 * @param u
 * @throws SQLException
 * @throws MessagingException 
 * @throws AddressException 
 */
	public void add(user u) throws SQLException, AddressException, MessagingException {
		userDao ud = new userDao();
		ud.add(u);

		//�����ʼ�
		String msg = "��ӭ��ע������ǵ�һԱ,<a href='http://localhost:8080/CurrentNews/user?method=active&code="+u.getCode()+"'>��˼���</a>";
		MailUtils.sendMail(u.getEmail(), msg);
	}
/**
 * �����û�
 * @param code
 * @return
 * @throws SQLException 
 */
	public user active(String code) throws SQLException {
		userDao ud = new userDao();
		user u =ud.getByCode(code);
		if(u==null){
			return null;
		}

		u.setStatus(1);
		u.setCode(null);
		ud.update(u);
		return u; 
	}
	/**
	 * ͨ���û�����ȡ�û���Ϣ
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public user getByUserName(String username) throws SQLException {
		userDao ud = new userDao();
		
		user u = ud.getByUserName(username);
		if(u!=null){
			CollectDao cd =new CollectDao();
			List<Collect> list =cd.getCollectByUser(u.getId());
			u.setCollect(list);
		}
		return u;
	}
	/**
	 * ��ҳ��ȡ�û���Ϣ
	 * @param currPage
	 * @return
	 * @throws SQLException 
	 */
	public pageBean<user> getByPage(int currPage) throws SQLException {
		userDao ud  = new userDao();
		int totalCount =ud.count();
		int pageSize = constant.USER_PAGESIZE;
		List<user> list = ud.getByPage(currPage,pageSize);
		pageBean<user> page =new pageBean<>(list, totalCount, currPage, pageSize);
		return page;
	}
	/**
	 * ����id��ȡ�û���Ϣ
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public user getById(int id) throws SQLException {
		userDao ud = new userDao();
		return ud.getById(id);
	}
	/**
	 * �޸��û�״̬
	 * @param u
	 * @throws SQLException 
	 */
	public void update(user u) throws SQLException {
		userDao ud = new userDao();
		ud.update(u);
	}

}
