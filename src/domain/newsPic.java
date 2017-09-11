package domain;

import java.io.Serializable;

public class newsPic implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String publisher;
	
	private String pic;
	private String create_date;
	private String username;
	
	private String info;
	private String newsType_id;
	private String publish_date;
	public newsPic() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "newsPic [id=" + id + ", title=" + title + ", publisher=" + publisher + ", pic=" + pic + ", create_date="
				+ create_date + ", username=" + username + ", info=" + info + ", newsType_id=" + newsType_id
				+ ", publish_date=" + publish_date + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public String getNewsType_id() {
		return newsType_id;
	}

	public void setNewsType_id(String newsType_id) {
		this.newsType_id = newsType_id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
