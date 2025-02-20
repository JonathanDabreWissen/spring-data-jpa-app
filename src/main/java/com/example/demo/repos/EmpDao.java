package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Employee;

public interface EmpDao extends JpaRepository<Employee, Integer> {
	
	public List<Employee> getByDesignation(String desig);
	public List<Employee> findByAgeGreaterThan(int age);
	public List<Employee> findByAgeLessThan(int age);
	@Query("from Employee where designation=?1 order by age desc")
	public List<Employee> myCustomQuery(String desig);
}
