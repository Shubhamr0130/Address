package com.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.Vo.Department;
import com.microservices.Vo.ResponseTemplateVo;
import com.microservices.entity.User;
import com.microservices.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;


	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public ResponseTemplateVo getUserWithDepartment(Long userID) {
		// TODO Auto-generated method stub
		ResponseTemplateVo vo =new ResponseTemplateVo();
		User user= userRepository.findByUserId(userID);
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
	vo.setUser(user);
	vo.setDepartment(department);
	return vo;
	}

}
