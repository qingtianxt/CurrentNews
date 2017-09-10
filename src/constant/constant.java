package constant;

public interface constant {
	/**
	 * 管理员用户分页每页多少条
	 */
	int ADMINUSER_PAGESIZE = 3;
	/**
	 * 管理员的分页信息
	 */
	String ADMINUSER_LIST = "adminUser_list";
	/**
	 * 管理员分页的pagingBean
	 */
	String ADMINUSER_PAGINGBEAN= "adminUser_pagingBean";
	/**
	 * 单个管理员信息
	 */
	String ADMINUSERBEAN = "adminUserBean";
	
	/**
	 *登录的管理员
	 */
	String SESSION_ADMINUSER = "session_adminUser";
	
	/**
	 * 新闻分类的list集合
	 */
	String NEWSTYPE_LIST = "newsType_list";
	
	/**
	 * 新闻分类的bean，包括这个分类和他的一级子类
	 */
	String NEWSTYPEBEAN = "newsTypeBean";
	/**
	 * 图片新闻的每页条数
	 */
	int NEWSPIC_PAGESIZE =5;
}
