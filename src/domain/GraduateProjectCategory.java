package domain;

import java.io.Serializable;

public final class GraduateProjectCategory implements Comparable<GraduateProjectCategory>,Serializable{
	//定义相关字段
	private Integer id;
	private String description;
	private String no;
	private String remarks;
	//定义构造器
	public GraduateProjectCategory(Integer id, String description, String no,
                                   String remarks) {
		super();
		this.id = id;
		this.description = description;
		this.no = no;
		this.remarks = remarks;
	}
	//定义字所需的方法
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
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
	public int compareTo(GraduateProjectCategory o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
}
