package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的servlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		try{
			//获取子类
			Class clazz = this.getClass();
			
			//获取请求的方法
			String m = arg0.getParameter("method");
			if(m==null){
				m="index";
			}
			
			//获取方法对象
			Method method = clazz.getMethod(m, HttpServletRequest.class,HttpServletResponse.class);
			
			//让方法执行
			String s =(String) method.invoke(this,arg0, arg1);
			//判断s是否为空
			if(s!=null){
				arg0.getRequestDispatcher(s).forward(arg0, arg1);;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	public String index(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		return null;
	}
}
