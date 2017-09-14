package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.newsPic;
import domain.pageBean;
import service.FrontNewsService;
import service.newsPicService;
import utils.StringUtil;

/**
 * ��ʼ�����ݵ�indexҳ��
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(1);
		int currPage = StringUtil.StringToInt( request.getParameter("currPage"));
		if(0==currPage){
			currPage=1;
		}
		newsPicService ns = new newsPicService();
		FrontNewsService fs =new FrontNewsService();
		try {
			pageBean<newsPic>page =ns.getAllByPage(currPage);
			page.setAnd(false);
			page.setPrefixUrl(request.getContextPath()+"/hello");
			request.setAttribute(constant.NEWSPIC_PAGEBEAN	, page);
			request.setAttribute(constant.NEWSPIC_BEANS, page.getList());
			
			
			List<newsPic> page1 =fs.getTodayNewstf();//��ȡ�������ŵ�ǰ����
			
			List<newsPic> page2 = fs.getWorldNewstf();//��ȡ�������ŵ�ǰ����
			
			request.setAttribute(constant.TODAYNEWS, page1);
			request.setAttribute(constant.WORLDNEWS, page2);
			
			
			request.getRequestDispatcher("/front/index.jsp").forward(request, response);
			System.out.println("��ȡ��ҳ���ݳɹ�����Ҫչʾ���ǵ�"+currPage+"ҳ");
		} catch (SQLException e) {
			System.out.println("��ȡ��ҳ����ʧ��");
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
