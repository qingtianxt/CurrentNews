package domain;

import java.io.Serializable;

public class Collect implements Serializable{
	private int id;
	private int user_id;//用户id
	private int news_id;//新闻id
	private String collect_date;//收藏时间
	private newsPic newsPic;
	public newsPic getNewsPic() {
		return newsPic;
	}
	public void setNewsPic(newsPic newsPic) {
		this.newsPic = newsPic;
	}
	public int getId() {
		return id;
	}
	public String getCollect_date() {
		return collect_date;
	}
	@Override
	public String toString() {
		return "Collect [id=" + id + ", user_id=" + user_id + ", news_id=" + news_id + ", collect_date=" + collect_date
				+ "]";
	}
	public void setCollect_date(String collect_date) {
		this.collect_date = collect_date;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
