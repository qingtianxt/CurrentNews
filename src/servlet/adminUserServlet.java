package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.adminUser;
import domain.pageBean;
import service.adminUserService;
import utils.DateUtils;
import utils.MD5Utils;
import utils.StringUtil;

/**
 * Servlet implementation class adminUserServlet
 */
public class adminUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
/**
 * ��ת������Ա�û����ҳ��
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/adminUser/add.jsp";
	}
/**
 * ��ӻ����޸Ĺ���Ա��Ϣ
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateId = request.getParameter("updateId");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		adminUser au =new adminUser();
		au.setUsername(username);
		au.setPassword(password);
		
		adminUserService as =new adminUserService();
		if("".equals(updateId)){//��Ӳ���
			try {
				adminUser a =as.getByUserName(username);
				if(null!=a){
					request.setAttribute("msg", "ע��ʧ�ܣ����û����ѱ�ע��");
				}else{
					String salt = StringUtil.getRandomString(10);
					au.setSalt(salt);
					au.setPassword(MD5Utils.md5(MD5Utils.md5(password)+salt));
					au.setCreate_date(DateUtils.getDate());
					as.add(au);
					request.setAttribute("msg", "ע��ɹ�");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "ע��ʧ��");
			}
			
			
		}else{//�޸Ĳ���
			au.setId(StringUtil.StringToInt(updateId));
			try {
				as.update(au);
				request.setAttribute("msg", "�޸ĳɹ�");
				return "/adminUser?method=listByPage&currPage=1";
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�޸�ʧ��");
			}
			
		}
		
		return "/admin/adminUser/add.jsp";
	}
	
	/**
	 * ��ҳ��ʾ����Ա��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = (String)request.getAttribute("msg");
		
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		adminUserService as =new adminUserService();
		try {
			pageBean<adminUser> page =as.getByPage(currPage);
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/adminUser?method=listByPage");
			request.setAttribute(constant.ADMINUSER_LIST, page.getList());
			request.setAttribute(constant.ADMINUSER_PAGINGBEAN, page);
			if(!"".equals(msg)){
				request.setAttribute("msg", msg);
			}
			System.out.println("��ѯ�ɹ�");
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		return "/admin/adminUser/listUsers.jsp";
	}
	/**
	 * Я������Ա����Ϣ��ת�����ҳ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = StringUtil.StringToInt(request.getParameter("id"));
		 adminUserService as = new adminUserService();
		 try {
			adminUser au = as.getById(id);
			request.setAttribute(constant.ADMINUSERBEAN,au);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/admin/adminUser/add.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		adminUserService as = new adminUserService();
		try {
			as.delete(id);
			request.setAttribute("msg", "ɾ���ɹ�");
			
			System.out.println("ɾ���ɹ�");
		} catch (SQLException e) {
			System.out.println("ɾ��ʧ��");
			e.printStackTrace();
		}
		return "/adminUser?method=listByPage&currPage=1";
	}
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		adminUserService as = new adminUserService();
		try {
			String msg =as.login(username,password);
			try {
				adminUser adminUser = as.getByUserName(username);
				
				if("��½�ɹ�".equals(msg)){
					request.setAttribute("msg", msg);
					request.getSession().setAttribute(constant.SESSION_ADMINUSER, adminUser);
					System.out.println(msg);
					return "/admin/main.jsp";
				}else{
					request.setAttribute("msg", msg);
					return "/admin/adminUser/login.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��ȡ�û�����������");
			}
			
		} catch (SQLException e) {
			System.out.println("��½����");
			e.printStackTrace();
		}
		return null;
	}
	public String end(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		return "/admin/adminUser/login.jsp";
	}
	
}
