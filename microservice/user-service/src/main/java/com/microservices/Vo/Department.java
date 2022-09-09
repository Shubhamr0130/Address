package com.microservices.Vo;

public class Department {
	
	private Long departmentId;
	private String departmentName;
	private String DepartmentAddress;
	private String departmentCode;
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentAddress() {
		return DepartmentAddress;
	}
	public void setDepartmentAddress(String departmentAddress) {
		DepartmentAddress = departmentAddress;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		DepartmentAddress = departmentAddress;
		this.departmentCode = departmentCode;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Depatment [departmentId=" + departmentId + ", departmentName=" + departmentName + ", DepartmentAddress="
				+ DepartmentAddress + ", departmentCode=" + departmentCode + "]";
	}

}
