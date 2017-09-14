package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.newsPic;
import domain.pageBean;
import service.FrontNewsService;
import utils.StringUtil;

/**
 * Servlet implementation class FrontNewsServlet
 */
public class FrontNewsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

/**
 * 根据分类分页获取新闻信息
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String getTypeNewsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typeId = StringUtil.StringToInt(request.getParameter("id")) ;
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		
		FrontNewsService fs = new FrontNewsService();
		try {
			pageBean<newsPic>page =fs.getTypeNewsByPage(typeId,currPage);
			
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/frontNews?method=getTypeNewsByPage&id="+typeId);
			request.setAttribute(constant.NEWSPIC_PAGEBEAN, page);
			request.setAttribute(constant.NEWSPIC_BEANS, page.getList());

			List<newsPic> list = fs.getWorldNewstf();
			List<newsPic> list1 = fs.getTodayNewstf();
			
			request.setAttribute(constant.WORLDNEWS, list);
			request.setAttribute(constant.TODAYNEWS, list1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/front/index.jsp";
	}
	public String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		int currPage = StringUtil.StringToInt( request.getParameter("currPage")) ;
		FrontNewsService fs = new FrontNewsService();
		try {
			pageBean<newsPic>page =fs.search(title,currPage);
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/frontNews?method=search");
			request.setAttribute(constant.NEWSPIC_PAGEBEAN, page);
			request.setAttribute(constant.NEWSPIC_BEANS, page.getList());
			
			List<newsPic> list = fs.getWorldNewstf();
			List<newsPic> list1 = fs.getTodayNewstf();
			
			request.setAttribute(constant.WORLDNEWS, list);
			request.setAttribute(constant.TODAYNEWS, list1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/front/index.jsp";
	}
	
}
