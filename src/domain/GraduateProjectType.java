package domain;

import java.io.Serializable;

public final class GraduateProjectType implements Comparable<GraduateProjectType>,Serializable  {
	//定义所需的字段
	private Integer id;
	private String description;
	private String no;
	private String remarks;
	//定义构造器
	public GraduateProjectType(Integer id, String description, String no, String remarks) {
		super();
		this.id = id;
		this.description = description;
		this.no = no;
		this.remarks = remarks;
	}
   //定义所需的方法返回对应字段
	public Integer getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getNo() {
		return no;
	}
	public String getRemarks() {
		return remarks;
	}

	@Override
	public int compareTo(GraduateProjectType o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
}
