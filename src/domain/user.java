package domain;

import java.io.Serializable;

public class user implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	
	private String salt;
	private int status;//0表示冻结，1表示激活
	private int sex;//0代表男 1代表女
	
	private String nickname;
	private String email;
	private String code;//邮箱激活码
	
	private String create_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", status="
				+ status + ", sex=" + sex + ", nickname=" + nickname + ", email=" + email + ", code=" + code
				+ ", create_date=" + create_date + "]";
	}

	
}
