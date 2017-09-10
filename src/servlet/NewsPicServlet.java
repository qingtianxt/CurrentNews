package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
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
				if(fi.isFormField()){//�ж��Ǳ����ݻ���ͼƬ����
					map.put(fi.getFieldName(),fi.getString("utf-8"));
				}else{
					
					String name = fi.getName();//��ȡ�ļ���
					String realName = UploadUtils.getRealName(name);//��ȡ�ļ�����ʵ����
					String dateName =DateUtils.getDateStr()+"/"+DateUtils.getTimeStr()+"/"+ realName;//��д����
					String path =this.getServletContext().getRealPath("news/1");
					System.out.println(path);
					File file = new File(path+DateUtils.getDateStr()+"/"+DateUtils.getTimeStr());
					file.mkdirs();
					InputStream is = fi.getInputStream(); 
					FileOutputStream os = new FileOutputStream(path+dateName);
					//ʹ�öԿ���
					IOUtils.copy(is, os);
					os.close();
					is.close();
					fi.delete();//ɾ����ʱ�ļ�
					map.put(fi.getFieldName(), "news/1"+DateUtils.getDateStr()+"/"+DateUtils.getTimeStr());
				}
				
			}
			map.put("create_date", DateUtils.getDate());
			adminUser a = (adminUser) request.getSession().getAttribute("username");
			if(a==null){
				map.put("username","aaaaa");
			}else{
				map.put("username", a.getUsername());
			}
			
			newsPic n = new newsPic();
			try {
				BeanUtils.populate(n, map);
				System.out.println("��װ���ݳɹ�");
				newsPicService ns = new newsPicService();
				ns.add(n);
				request.setAttribute("msg", "��ӳɹ�");
				
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
	 * ��ҳ��ȡͼƬ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		newsPicService ns =new newsPicService();
		pageBean<newsPic> page =ns.listByPage(currPage);
		return "/admin/newsPic/add.jsp";
	}
	
}
