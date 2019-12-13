package domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>,Serializable{
	//定义相关字段
	private Integer id;
	private String username;
	private String password;
	private Date loginTime;
	private Teacher teacher;
	//定义默认构造器
	public User(){}
	//定义构造器
	public User(String username, String password,
				Teacher teacher) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		//this.loginTime = loginTime;
		this.teacher = teacher;
	}
    //定义相关方法返回对应字段
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
}
