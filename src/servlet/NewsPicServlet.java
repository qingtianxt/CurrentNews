package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * ͼƬ���Ź���
 */
public class NewsPicServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ת��ͼƬ�������ҳ��
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
	 * ��ӻ����޸�������Ϣ
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
				if (fi.isFormField()) {// �ж��Ǳ����ݻ���ͼƬ����
					if(fi.getFieldName().equals("newsType_id")){//�ж��ǲ��Ƿ���
						
						//�ж�map��ȡ��newsType_id������
						Integer value =0;
						Object object = map.get("newsType_id");
						if (object instanceof Integer) { 
							value = (Integer) object;
						}
						
						if(null==value){//��û�з���
							map.put(fi.getFieldName(), fi.getString("utf-8"));
						}else{//�з����ˣ�ѡȡid���ķ���
							map.put(fi.getFieldName(), Math.max(StringUtil.StringToInt(fi.getString("utf-8")),value));
							System.out.println("newsType_id:"+value);
						}
					}else{
						map.put(fi.getFieldName(), fi.getString("utf-8"));
					}
				} else {

					String name = fi.getName();// ��ȡ�ļ���
					String realName = UploadUtils.getRealName(name);// ��ȡ�ļ�����ʵ����
					String dateName = DateUtils.getDateStr() + DateUtils.getTimeStr() + realName;// ��д����
					String path = this.getServletContext().getRealPath("news/1");
					File file = new File(path);
					file.mkdirs();
					InputStream is = fi.getInputStream();
					FileOutputStream os = new FileOutputStream(path + dateName);
					// ʹ�öԿ���
					IOUtils.copy(is, os);
					os.close();
					is.close();
					fi.delete();// ɾ����ʱ�ļ�
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
				System.out.println("��װ���ݳɹ�");
				if(n.getId()==0){
					ns.add(n);
					System.out.println("������ݳɹ�");
					request.setAttribute("msg", "��ӳɹ�");
				}else{
					ns.update(n);
					System.out.println("�޸����ݳɹ�");
					response.sendRedirect(request.getContextPath()+"/newsPic?method=listByPage&currPage=1");
					return null;
				}
			} catch (Exception e) {
				System.out.println("��װ����ʧ��");
				e.printStackTrace();
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return "/admin/newsPic/add.jsp";
	}

	/**
	 * ��ҳ��ȡͼƬ������Ϣ,չʾʱ����ʱ�䵹��չʾ
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
/**
 * չʾһ�����ŵ���ϸ��Ϣ �������ŵķ���
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String listDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		int id = StringUtil.StringToInt( request.getParameter("id"));
		newsPicService ns = new newsPicService();
		try {
			newsPic n= ns.getById(id);
			System.out.println("��ȡͼƬ������ϸ��Ϣ�ɹ���idΪ"+id);
			request.setAttribute(constant.NEWSPIC_BEAN, n);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡͼƬ������ϸ��Ϣʧ��");
		}
		if("1".equals(status)){
			return "/admin/newsPic/details.jsp";
		}else{
			return "/admin/newsPic/details.jsp";
		}
	}
	/**
	 * Я����Ҫ�޸ĵ�ͼƬ���ŵ���Ϣ��ת�����ҳ��
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response){
		int id = StringUtil.StringToInt( request.getParameter("id"));
		newsPicService ns = new newsPicService();
		try {
			newsPic n= ns.getById(id);
			System.out.println("��ȡͼƬ������ϸ��Ϣ�ɹ���idΪ"+id);
			request.setAttribute(constant.NEWSPIC_BEAN, n);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡͼƬ������ϸ��Ϣʧ��");
		}
		return "/admin/newsPic/add.jsp";
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = StringUtil.StringToInt( request.getParameter("id"));
		newsPicService ns = new newsPicService();
		try {
			ns.delete(id);
			System.out.println("ɾ��ͼƬ������Ϣ�ɹ���ɾ�������ŵ�id��"+id);
			response.sendRedirect(request.getContextPath()+"/newsPic?method=listByPage&currPage=1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ɾ��ͼƬ������Ϣʧ��");
		}
		return null;
	
	}
	
}
