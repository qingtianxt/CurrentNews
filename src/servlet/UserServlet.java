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
 * ǰ̨�û�ģ��
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ע���û�
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
			System.out.println("��װ���ݳɹ�");
			user u1 = us.getByUserName(u.getUsername());
			if (u1 != null) {
				request.setAttribute("msg1", "���û��ѱ�ע��");
				System.out.println(u.getUsername() + "���û��ѱ�ע��");
				return "/front/register.jsp";
			} else {
				String salt = StringUtil.getRandomString(10);

				u.setSalt(salt);
				u.setPassword(MD5Utils.md5(MD5Utils.md5(u.getPassword()) + salt));
				u.setCreate_date(DateUtils.getDate());
				u.setCode(UUIDUtils.getCode());
				try {
					us.add(u);
					System.out.println("����û��ɹ�,��ȥ���伤��");
					request.setAttribute("msg", "����û��ɹ�,��ȥ���伤��");

				} catch (SQLException e) {
					System.out.println("����û�ʧ��");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("��װ����ʧ��");
			e.printStackTrace();
		}

		return "/front/register.jsp";
	}

	/**
	 * �����û����û�״̬����Ϊ1����������Ϊ��
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
			msg = "����ʧ�ܣ�������ע��";
			return "/front/register.jsp";
		} else {
			msg = "����ɹ�";

		}
		return "/front/login.jsp";
	}

	/**
	 * ��¼
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
			if (user != null) {// �и��û�
				System.out.println("���û�����");
				if (user.getPassword().equals(MD5Utils.md5(MD5Utils.md5(password) + user.getSalt()))) {
					System.out.println("��½�ɹ�");
					request.getSession().setAttribute(constant.SESSION_USER, user);

				} else {
					request.setAttribute("msg", "�û������������");
					System.out.println("�û������������");
				}
			} else {
				request.setAttribute("msg", "����������û��������������ע��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�����û�����ȡ�û���Ϣʧ��");
		}

		return "/front/index.jsp";
	}
	/**
	 * ��ҳ��ȡ�û���Ϣ
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
			System.out.println("��ȡ��"+currPage+"���ݳɹ�");
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/user?method=listByPage");
			request.setAttribute(constant.USER_PAGINGBEAN, page);
			request.setAttribute(constant.USER_BEANS, page.getList());
			
			if(!"".equals(status1)){
				if("1".equals(status1)){
					request.setAttribute("msg", "�޸ĳɹ�");
				}else{
					request.setAttribute("msg", "�޸�ʧ��");
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡ��"+currPage+"����ʧ��");
		}
		
		return "/admin/user/listUsers.jsp";
	}
	/**
	 * չʾһ���û�����ϸ��Ϣ
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
			System.out.println("��ȡ�û���Ϣ�ɹ�,�û�����"+username);
			request.setAttribute(constant.USER_BEAN, u);
			if(!"".equals(status1)){
				if("1".equals(status1)){
					request.setAttribute("msg", "�޸ĳɹ�");
				}else{
					request.setAttribute("msg", "�޸�ʧ��");
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡ�û���Ϣʧ��,�û�����"+username);
		}
		
		return "/admin/user/blockUser.jsp";
	}
	/**
	 * Ŷ�����˵�����ת�������û�����
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
	 * �޸��û���״̬
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
				System.out.println("��ȡ�û���Ϣ�ɹ����û�����"+user.getUsername());
				user.setStatus(status);
				try {
					us.update(user);
					System.out.println("�޸��û�״̬�ɹ����û�����"+user.getUsername());
					if(flag==0){
						response.sendRedirect(request.getContextPath()+"/user?method=listByPage&currPage=1&status1=1");
					}else{
						response.sendRedirect(request.getContextPath()+"/user?method=getByUserName&status1=1&username="+user.getUsername());
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("�޸��û�״̬ʧ�ܣ��û�����"+user.getUsername());
					
					if(flag==0){
						response.sendRedirect(request.getContextPath()+"/user?method=listByPage&currPage=1&status1=0");
					}else{
						response.sendRedirect(request.getContextPath()+"/user?method=getByUserName&status1=0&username="+user.getUsername());
					}
				}
				
			} catch (SQLException e) {
				System.out.println("��ȡ�û���Ϣʧ�ܣ��û�id��"+id);
				e.printStackTrace();
			}
			
			return null;
		}
	/**
	 * ע��
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
