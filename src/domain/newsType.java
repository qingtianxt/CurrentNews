package domain;

import java.io.Serializable;
import java.util.List;

public class newsType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int parentId;
	private String intro;
	private String create_date;
	
	private List<newsType> childBeans;
	
	
	public int getId() {
		return id;
	}
	public List<newsType> getChildBeans() {
		return childBeans;
	}
	public void setChildBeans(List<newsType> childBeans) {
		this.childBeans = childBeans;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public newsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "newsType [id=" + id + ", name=" + name + ", parentId=" + parentId + ", intro=" + intro
				+ ", create_date=" + create_date + "]";
	}
	
}
