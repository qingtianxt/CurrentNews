package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import constant.constant;
import domain.Collect;
import domain.pageBean;
import domain.user;
import service.CollectService;
import utils.DateUtils;
import utils.StringUtil;

/**
 * �ղ�ģ��
 */
public class CollectServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * �����ղص�״̬
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(1);
		int news_id = StringUtil.StringToInt(request.getParameter("news_id"))  ;
		int user_id = StringUtil.StringToInt(request.getParameter("user_id"));
		int status = StringUtil.StringToInt(request.getParameter("status"));
		System.out.println(news_id + ", " + user_id + "," + status);
		Collect c= new Collect();
		c.setNews_id(news_id);
		c.setUser_id(user_id);
		c.setCollect_date(DateUtils.getDate());
		
		
		CollectService cs =new CollectService();
		if(status==1){//�ղظ�����
			
			try {
				cs.add(c);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			status=0;
		}
		else{//ȡ���ղأ�ɾ�����ղأ�
			try {
				cs.delete(c);
				status=1;
				System.out.println("ɾ���ղسɹ�");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ɾ���ղ�ʧ��");
			}
		}
		response.setCharacterEncoding("utf-8");
		String data =""+news_id+"#"+user_id+"#"+status+"";
		PrintWriter out =response.getWriter();
		out.println(JSONArray.toJSONString(data));
		return null;
	}
	/**
	 * չʾ�ղؼ�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listCollectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user u =(user) request.getSession().getAttribute(constant.SESSION_USER );
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
			if(u!=null){
				CollectService cs =  new CollectService();
				try {
					pageBean<Collect> page =cs.getCollectByPage(u.getId(),currPage);
					page.setAnd(true);
					page.setPrefixUrl(request.getContextPath()+"/collect?method=listCollectByPage");
					request.setAttribute(constant.COLLECT_PAGINGBEAN, page);
					request.setAttribute(constant.COLLECT_BEAN, page.getList());
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		return "/front/user/list.jsp";
	}
	/**
	 * ȡ���ղ�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		CollectService cs =new CollectService();
		System.out.println("id:"+id);
		try {
			cs.deleteById(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/collect?method=listCollectByPage&currPage=1");
		return null;
	}
}
