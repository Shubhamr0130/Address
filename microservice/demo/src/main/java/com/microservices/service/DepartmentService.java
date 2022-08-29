package com.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.microservices.entity.Department;
import com.microservices.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
//		log.info("INside saveDepartment method of DepartmentController");
		return departmentRepository.save(department);
	}

	public Department findByDepartmentId(Long departmentId) {
		// TODO Auto-generated method stub
		return departmentRepository.findByDepartmentId(departmentId);
	}

//	public Optional<Department>FindDepartmentById(Long departmentId) {
//		// TODO Auto-generated method stub
//		return departmentRepository.FindDepartmentById(departmentId);
//	}

}
