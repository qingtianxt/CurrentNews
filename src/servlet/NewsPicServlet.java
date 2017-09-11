package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import constant.constant;
import domain.adminUser;
import domain.newsPic;
import domain.pageBean;
import service.newsPicService;
import utils.DateUtils;
import utils.StringUtil;
import utils.UploadUtils;

/**
 * 图片新闻管理
 */
public class NewsPicServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到图片新闻添加页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/admin/newsPic/add.jsp";
	}

	/**
	 * 添加或者修改新闻信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<>();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fi : list) {
				if (fi.isFormField()) {// 判断是表单内容还是图片内容
					if(fi.getFieldName().equals("newsType_id")){//判断是不是分类
						Integer newsType_id=(Integer)map.get("newsType_id");//获取
						if(null==newsType_id){//还没有分类
							map.put(fi.getFieldName(), fi.getString("utf-8"));
						}else{//有分类了，选取id最大的分类
							map.put(fi.getFieldName(), Math.max(StringUtil.StringToInt(fi.getString("utf-8")), newsType_id));
							System.out.println("newsType_id:"+newsType_id);
						}
					}else{
						map.put(fi.getFieldName(), fi.getString("utf-8"));
					}
				} else {

					String name = fi.getName();// 获取文件名
					String realName = UploadUtils.getRealName(name);// 获取文件的真实姓名
					String dateName = DateUtils.getDateStr() + DateUtils.getTimeStr() + realName;// 重写名字
					String path = this.getServletContext().getRealPath("news/1");
					File file = new File(path);
					file.mkdirs();
					InputStream is = fi.getInputStream();
					FileOutputStream os = new FileOutputStream(path + dateName);
					// 使用对拷流
					IOUtils.copy(is, os);
					os.close();
					is.close();
					fi.delete();// 删除临时文件
					map.put(fi.getFieldName(), "news/1"+dateName);
				}

			}
			map.put("create_date", DateUtils.getDate());
			adminUser a = (adminUser) request.getSession().getAttribute("username");
			if (a == null) {
				map.put("username", "aaaaa");
			} else {
				map.put("username", a.getUsername());
			}

			newsPic n = new newsPic();
			newsPicService ns = new newsPicService();
			try {
				BeanUtils.populate(n, map);
				System.out.println("封装数据成功");
				System.out.println(n.getId());
				if(n.getId()==0){
					ns.add(n);
					System.out.println("添加数据成功");
					request.setAttribute("msg", "添加成功");
				}else{
					System.out.println(n.toString());
					ns.update(n);
					System.out.println("修改数据成功");
					response.sendRedirect(request.getContextPath()+"/newsPic?method=listByPage&currPage=1");
					return null;
				}
			} catch (Exception e) {
				System.out.println("封装数据失败");
				e.printStackTrace();
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return "/admin/newsPic/add.jsp";
	}

	/**
	 * 分页获取图片新闻信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		newsPicService ns = new newsPicService();
		try {
			pageBean<newsPic> page = ns.listByPage(currPage);
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath() + "/newsPic?method=listByPage");
			request.setAttribute(constant.NEWSPIC_PAGEBEAN, page);
			request.setAttribute(constant.NEWSPIC_BEANS, page.getList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/admin/newsPic/list.jsp";
	}

	public String listDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = StringUtil.StringToInt( request.getParameter("id"));
		newsPicService ns = new newsPicService();
		try {
			newsPic n= ns.getById(id);
			System.out.println("获取图片新闻详细信息成功，id为"+id);
			request.setAttribute(constant.NEWSPIC_BEAN, n);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取图片新闻详细信息失败");
		}
		
		return "/admin/newsPic/details.jsp";
	}
	/**
	 * 携带需要修改的图片新闻的信息跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response){
		int id = StringUtil.StringToInt( request.getParameter("id"));
		newsPicService ns = new newsPicService();
		try {
			newsPic n= ns.getById(id);
			System.out.println("获取图片新闻详细信息成功，id为"+id);
			request.setAttribute(constant.NEWSPIC_BEAN, n);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取图片新闻详细信息失败");
		}
		return "/admin/newsPic/add.jsp";
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = StringUtil.StringToInt( request.getParameter("id"));
		newsPicService ns = new newsPicService();
		try {
			ns.delete(id);
			System.out.println("删除图片新闻信息成功，删除的新闻的id是"+id);
			response.sendRedirect(request.getContextPath()+"/newsPic?method=listByPage&currPage=1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("删除图片新闻信息失败");
		}
		return null;
	
	}
}
