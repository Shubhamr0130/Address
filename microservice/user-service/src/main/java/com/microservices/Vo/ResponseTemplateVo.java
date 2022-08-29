package com.microservices.Vo;

import com.microservices.entity.User;

public class ResponseTemplateVo {
private User user;
private Department department;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Department getDepartment() {
	return department;
}
public void setDepartment(Department department) {
	this.department = department;
}
public ResponseTemplateVo(User user, Department department) {
	super();
	this.user = user;
	this.department = department;
}
public ResponseTemplateVo() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "ResponseTemplateVo [user=" + user + ", department=" + department + "]";
}


}
