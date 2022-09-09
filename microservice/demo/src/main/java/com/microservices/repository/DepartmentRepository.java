package com.microservices.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservices.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentId(Long departmentId);

	//Optional<Department> FindDepartmentById(Long departmentId);

//	Optional<Department>findDepatmentById(Long departmentId);

//	Department findDepartmentById(Long departmentId);

}
