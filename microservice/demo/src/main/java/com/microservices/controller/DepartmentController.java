package com.microservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.entity.Department;
import com.microservices.service.DepartmentService;



@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
//	log.info("INside saveDepartment method of DepartmentController");
		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/{id}")
	public Department FindDepartmentById(@PathVariable("id") Long departmentId) {
//		log.info("INside getDepartment method of DepartmentController");
		Department department=this.departmentService.findByDepartmentId(departmentId);
		return department;
	}
}
