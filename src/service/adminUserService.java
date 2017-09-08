package service;

import java.sql.SQLException;
import java.util.List;

import constant.constant;
import dao.adminUserDao;
import domain.adminUser;
import domain.pageBean;
import utils.MD5Utils;
import utils.StringUtil;

public class adminUserService {

	public adminUser getByUserName(String username) throws Exception{
		adminUserDao ad = new adminUserDao();
		return ad.getByUserName(username);
	}

	public void add(adminUser au)throws Exception {
		adminUserDao ad = new adminUserDao();
		ad.add(au);
	}

	public pageBean<adminUser> getByPage(int currPage) throws SQLException {
		adminUserDao ad = new adminUserDao();
		int totalCount = ad.count();
		List<adminUser> list =ad.getByPage(currPage,constant.ADMINUSER_PAGESIZE);
		pageBean<adminUser> page =new pageBean<>(list, totalCount, currPage, constant.ADMINUSER_PAGESIZE);
		return page;
	}
/**
 * 通过id获取管理员信息。
 * @param id
 * @return
 * @throws SQLException 
 */
	public adminUser getById(int id) throws SQLException {
		adminUserDao ad = new adminUserDao();
		return ad.getById(id);
	}
/**
 * 修改管理员信息
 * @param au
 * @throws SQLException 
 */
	public void update(adminUser au) throws SQLException {
		adminUserDao ad =new adminUserDao();
		String salt = StringUtil.getRandomString(10);
		au.setPassword(MD5Utils.md5(MD5Utils.md5(au.getPassword())+salt));
		au.setSalt(salt);
		ad.update(au);
	}

	public void delete(int id) throws SQLException {
		adminUserDao ad = new adminUserDao();
		ad.delete(id);
	}

	public String login(String username, String password) throws SQLException {
		String msg ="";
		adminUserDao ad = new adminUserDao();
		adminUser adminUser = ad.getByUserName(username);
		if(adminUser.getUsername().equals(username)){
			if(adminUser.getPassword().equals(MD5Utils.md5(MD5Utils.md5(password)+adminUser.getSalt()))){
				msg= "登陆成功";
			}
			else{
				msg = "密码错误";
			}
		}else{
			msg = "不存在这个用户名";
			
		}
		return msg;
	}
	
}
