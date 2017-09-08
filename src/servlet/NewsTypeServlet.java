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
import domain.newsType;
import service.newsTypeService;
import utils.DateUtils;
import utils.StringUtil;

/**
 * ���ŷ���
 */
public class NewsTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

/**
 * �����˵�����ת���������ҳ��
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		newsTypeService ns = new newsTypeService();
		String msg =(String)request.getAttribute("msg");
		List<newsType> list = null;
		try {
			list = ns.getByParentId(0);
			System.out.println("��ѯ����ɹ�����ѯ�ĸ�����id��"+0);
			request.setAttribute(constant.NEWSTYPE_LIST, list);
			if(!"".equals(msg)){
				request.setAttribute("msg", msg);
			}
		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
			e.printStackTrace();
		}
		return "/admin/type/add.jsp";
	}
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String[] parId = request.getParameterValues("parentId");
		int parentId =0;
		for (String s : parId) {
			parentId= Math.max(parentId, StringUtil.StringToInt(s));
		}
		String intro = request.getParameter("intro");
		newsType n = new newsType();
		n.setName(name);
		n.setIntro(intro);
		n.setParentId(parentId);
		newsTypeService ns = new newsTypeService();
		
		if("".equals(id)){//��ӷ���
			n.setCreate_date(DateUtils.getDate());
			try {
				ns.add(n);
				request.setAttribute("msg", "��ӳɹ�");
				System.out.println("��ӷ���ɹ�");
//				request.getRequestDispatcher("/newsType?method=addUI").forward(request, response);
				
				
			} catch (SQLException e) {
				System.out.println("���ʧ��");
				e.printStackTrace();
			}
		}else{//�޸ķ���
			
		}
		return "/admin/type/add.jsp";
	}
	/**
	 * ��ȡһ��������ӷ���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		System.out.println(id);
		newsTypeService ns = new newsTypeService();
		try {
			List<newsType> list = ns.getByParentId(id);
			for (newsType l : list) {
				System.out.println(l.toString());
				
			}
			System.out.println("��ȡ�ķ���idΪ"+id+"������ɹ�");
			response.setCharacterEncoding("utf-8");
			PrintWriter out= response.getWriter();
			out.println(JSONArray.toJSONString(list));
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡ����idΪ"+id+"������ʧ��");
		}
		return null;
	}
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		newsTypeService ns = new newsTypeService();
		try {
			newsType n = ns.getList(id);//��ȡһ�����������һ������
			System.out.println("��ȡ�ɹ���"+"��Ҫ��ȡ��id��"+id);
			request.setAttribute(constant.NEWSTYPEBEAN, n);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡʧ��");
		}
		return "/admin/type/list.jsp";
	}
}
