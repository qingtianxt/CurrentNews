package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import constant.constant;
import domain.pageBean;
import domain.user;
import service.userService;
import utils.DateUtils;
import utils.MD5Utils;
import utils.StringUtil;
import utils.UUIDUtils;

/**
 * 前台用户模块
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注册用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user u = new user();

		userService us = new userService();
		try {
			BeanUtils.populate(u, request.getParameterMap());
			System.out.println("封装数据成功");
			user u1 = us.getByUserName(u.getUsername());
			if (u1 != null) {
				request.setAttribute("msg1", "该用户已被注册");
				System.out.println(u.getUsername() + "该用户已备注册");
				return "/front/register.jsp";
			} else {
				String salt = StringUtil.getRandomString(10);

				u.setSalt(salt);
				u.setPassword(MD5Utils.md5(MD5Utils.md5(u.getPassword()) + salt));
				u.setCreate_date(DateUtils.getDate());
				u.setCode(UUIDUtils.getCode());
				try {
					us.add(u);
					System.out.println("添加用户成功,请去邮箱激活");
					request.setAttribute("msg", "添加用户成功,请去邮箱激活");

				} catch (SQLException e) {
					System.out.println("添加用户失败");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("封装数据失败");
			e.printStackTrace();
		}

		return "/front/register.jsp";
	}

	/**
	 * 激活用户，用户状态设置为1，激活码置为空
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		userService us = new userService();
		user u = null;
		try {
			u = us.active(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String msg = "";
		if (u == null) {
			msg = "激活失败，请重新注册";
			return "/front/register.jsp";
		} else {
			msg = "激活成功";

		}
		return "/front/login.jsp";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userService us = new userService();
		try {
			user user = us.getByUserName(username);
			if (user != null) {// 有该用户
				System.out.println("该用户存在");
				if (user.getPassword().equals(MD5Utils.md5(MD5Utils.md5(password) + user.getSalt()))) {
					System.out.println("登陆成功");
					request.getSession().setAttribute(constant.SESSION_USER, user);

				} else {
					request.setAttribute("msg", "用户名或密码错误");
					System.out.println("用户名或密码错误");
				}
			} else {
				request.setAttribute("msg", "不存在这个用户，请重新输入或注册");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("根据用户名获取用户信息失败");
		}

		return "/front/index.jsp";
	}
	/**
	 * 分页获取用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status1 = request.getParameter("status1");
		int currPage = StringUtil.StringToInt(request.getParameter("currPage")) ;
		userService us = new userService();
		try {
			pageBean<user> page =us.getByPage(currPage);
			System.out.println("获取第"+currPage+"数据成功");
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/user?method=listByPage");
			request.setAttribute(constant.USER_PAGINGBEAN, page);
			request.setAttribute(constant.USER_BEANS, page.getList());
			
			if(!"".equals(status1)){
				if("1".equals(status1)){
					request.setAttribute("msg", "修改成功");
				}else{
					request.setAttribute("msg", "修改失败");
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取第"+currPage+"数据失败");
		}
		
		return "/admin/user/listUsers.jsp";
	}
	/**
	 * 展示一个用户的详细信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status1 = request.getParameter("status1");
		String username = request.getParameter("username");
		userService us = new userService();
		try {
			user u =us.getByUserName(username);
			System.out.println("获取用户信息成功,用户名是"+username);
			request.setAttribute(constant.USER_BEAN, u);
			if(!"".equals(status1)){
				if("1".equals(status1)){
					request.setAttribute("msg", "修改成功");
				}else{
					request.setAttribute("msg", "修改失败");
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取用户信息失败,用户名是"+username);
		}
		
		return "/admin/user/blockUser.jsp";
	}
	/**
	 * 哦从左侧菜单栏跳转到冻结用户界面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String blockUserUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/admin/user/blockUser.jsp";
	}
	/**
	 * 修改用户的状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = StringUtil.StringToInt(request.getParameter("id"));
			int status = StringUtil.StringToInt(request.getParameter("status"));
			int flag = StringUtil.StringToInt( request.getParameter("flag"));
			
			userService us = new userService();
			try {
				user user = us.getById(id);
				System.out.println("获取用户信息成功，用户名是"+user.getUsername());
				user.setStatus(status);
				try {
					us.update(user);
					System.out.println("修改用户状态成功，用户名是"+user.getUsername());
					if(flag==0){
						response.sendRedirect(request.getContextPath()+"/user?method=listByPage&currPage=1&status1=1");
					}else{
						response.sendRedirect(request.getContextPath()+"/user?method=getByUserName&status1=1&username="+user.getUsername());
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("修改用户状态失败，用户名是"+user.getUsername());
					
					if(flag==0){
						response.sendRedirect(request.getContextPath()+"/user?method=listByPage&currPage=1&status1=0");
					}else{
						response.sendRedirect(request.getContextPath()+"/user?method=getByUserName&status1=0&username="+user.getUsername());
					}
				}
				
			} catch (SQLException e) {
				System.out.println("获取用户信息失败，用户id是"+id);
				e.printStackTrace();
			}
			
			return null;
		}
	/**
	 * 注销
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/hello");
		return null;
	}
	
}
