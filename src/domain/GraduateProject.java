package domain;
import java.io.Serializable;

public final class GraduateProject implements Comparable<GraduateProject>,Serializable{
	//定义相关字段
	private Integer id;
	private String title;
	private GraduateProjectCategory graduateProjectCategory;
	private GraduateProjectType graduateProjectType;
	private Teacher teacher;
	//定义默认构造器
	public GraduateProject() {
		super();
	}
	//定义相关方法，返回对应字段值
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
	public GraduateProjectCategory getGraduateProjectCategory() {
		return graduateProjectCategory;
	}

	public void setGraduateProjectCategory(GraduateProjectCategory graduateProjectCategory) {
		this.graduateProjectCategory = graduateProjectCategory;
	}
	public GraduateProjectType getGraduateProjectType() {
		return graduateProjectType;
	}
	public void setGraduateProjectType(GraduateProjectType graduateProjectType) {
		this.graduateProjectType = graduateProjectType;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

    //定义构造器
	public GraduateProject(int id, String title,
						   GraduateProjectCategory graduateProjectCategory, GraduateProjectType graduateProjectType,
						  Teacher teacher) {
		this(title, graduateProjectCategory, graduateProjectType,teacher);
		this.id = id;
	}
	public GraduateProject(String title,
						   GraduateProjectCategory graduateProjectCategory,
						   GraduateProjectType graduateProjectType,
						   Teacher teacher) {
		super();
		this.title = title;
		this.graduateProjectCategory = graduateProjectCategory;
		this.graduateProjectType = graduateProjectType;
		this.teacher = teacher;
	}
	@Override
	public int compareTo(GraduateProject o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
}
