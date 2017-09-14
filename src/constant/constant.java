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
	/**
	 * 图片新闻pagingBean
	 */
	String NEWSPIC_PAGEBEAN = "newsPic_pageBean";
	/**
	 * 图片新闻 Beans
	 */
	String NEWSPIC_BEANS = "newsPic_beans";
	/**
	 * 图片新闻 Bean
	 */
	String NEWSPIC_BEAN = "newsPic_bean";
	/**
	 * session域中的user
	 */
	String SESSION_USER = "session_user";
	/**
	 * 用户分页，每页条数
	 */
	int USER_PAGESIZE =3;
	/**
	 * 用户分页pagingBean
	 */
	String USER_PAGINGBEAN = "user_pagingBean";
	/**
	 * 用户beans
	 */
	String USER_BEANS = "user_Beans";
	
	/**
	 * 单个用户信息bean
	 */
	String USER_BEAN ="user_bean";
	/**
	 * 图片新闻根据分类分页获取时每页的条数
	 */
	int NEWS_TYPE_PAGESIZE =4;
	/**
	 * 今日新闻前四条
	 */
	String TODAYNEWS ="todayNews";
	/**
	 * 国际新闻前四条
	 */
	String WORLDNEWS ="worldNews";
}
