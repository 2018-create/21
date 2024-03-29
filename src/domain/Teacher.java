package domain;
import java.io.Serializable;
import java.util.Set;

public final class Teacher implements Comparable<Teacher>,Serializable {
	//定义所需的字段
	private Integer id;
	private String no;
	private String name;
	private ProfTitle profTitle;
	private Degree degree;
	private Department department;
	//定义构造器
	public Teacher(Integer id,
				   String no,
				   String name,
				   ProfTitle title,
				   Degree degree,
                   Department department) {
		this(no,name, title, degree, department);
		this.id = id;
	}
	//定义构造器
	public Teacher(
			       String no,
				   String name,
				   ProfTitle title,
				   Degree degree,
				   Department department) {
		super();
		this.no = no;
		this.name = name;
		this.profTitle = title;
		this.degree = degree;
		this.department = department;
	}
	//定义构造器
	public Teacher(
			String name) {
		super();
		this.name = name;
	}
    //定义所需的方法，返回对应字段
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public String getNo() {
		return no;
	}

	public ProfTitle getTitle() {
		return this.profTitle;
	}

	public Degree getDegree() {
		return degree;
	}

	public Department getDepartment() {
		return department;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTitle(ProfTitle title) {
		this.profTitle = title;
	}
	@Override
	public int compareTo(Teacher o) {
		// TODO Auto-generated method stub
		return this.id-o.getId();
	}
}
