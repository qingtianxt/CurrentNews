package tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import domain.pageBean;


public class PageTag extends SimpleTagSupport {

	private pageBean pagingBean;
	private HttpServletRequest request;

	public pageBean getPagingBean() {
		return pagingBean;
	}


	public void setPagingBean(pageBean pagingBean) {
		this.pagingBean = pagingBean;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer buffer = new StringBuffer();
		if(pagingBean != null){
			buffer.append("<nav><ul class='pagination'>");
			//处理首页和上一页
			if(pagingBean.getCurrPage() <=1){
				//当前页为第一页
				//则首页按钮和上一页按钮为不可用状态
				buffer.append("<li class='disabled'><a>首页</a></li>");
				buffer.append("<li class='disabled'><a href='#'>上一页</a></li>");
				
			}else{
				//当前页不是第一页，则首页和上一页可以点击
				buffer.append("<li><a href='").append(pagingBean.getPrefixUrl()).append("&currPage=1")
				.append("'aria-label='Previous'><span aria-hidde='true'>首页</span></a></li>");
				buffer.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "&" : "?")//选择运算符，当isand（）为真时用&，否则用？
				
				.append("currPage=").append(pagingBean.getCurrPage() - 1).append("'>上一页</a></li>");
			}
			
			//处理下一个和尾页
			if(pagingBean.getCurrPage() == pagingBean.getTotalPage() ){
				//当前页是最后一页
				//下一页和尾页按钮不可用
				buffer.append("<li class='disabled'><a>下一页</a></li>");
				buffer.append("<li class='disabled'><a>尾页</a></li>");
			}else{
				//当前页不是最后一页
				buffer.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "&" : "?")
				.append("currPage=").append(pagingBean.getCurrPage() + 1).append("'>下一页</a></li>");
				buffer.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "&" : "?")
				.append("currPage=").append(pagingBean.getTotalPage()).append("'>尾页</a></li>");
			}
			buffer.append("<li><a href='#'");
			buffer.append("<span>");
			buffer.append(pagingBean.getCurrPage());
			buffer.append("/");
			buffer.append(pagingBean.getTotalPage());
			buffer.append("</span>");
			buffer.append("</a></li>");
			buffer.append("</ul></nav>");
			getJspContext().getOut().write(buffer.toString());
		}
	}
	

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
