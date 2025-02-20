package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.repos.EmpDao;

@RestController
public class EmpController {
	
	@Autowired
	EmpDao dao;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return dao.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int id) {
		return dao.findById(id);
	}
	
	@PostMapping("/employees")
	public String addEmployees(@RequestBody Employee e) {
		if(dao.existsById(e.getEid())) {
			return "Sorry! Already the employee exist with given ID";
		}
		dao.save(e);
		return "Added employee record successfully...";
	}
	
	@RequestMapping(path ="/employees/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public String updateEmployees(@RequestBody Employee e, @PathVariable int id) {
		if(!dao.existsById(id)) {
			return "Sorry! no employee with the given id";
		}
		
		dao.save(e);
		return "Updated employee record successfully...";
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployees(@PathVariable int id) {
		if(dao.existsById(id)) {
			return "Sorry! Already the employee exist with given ID";
		}
		dao.deleteById(id);
		return "Deleted employee record successfully...";
	}
	
	@GetMapping("/employees/role")
	public List<Employee> getEmployeeByDesig(String desig) {
		return dao.getByDesignation(desig);
	}
	
	
	@GetMapping("/employees/age")
	public List<Employee> findByAgeGreaterThan(int age) {
		return dao.findByAgeGreaterThan(age);
	}
	
	@GetMapping("/employees/custom")
	public List<Employee> findByAgeGreaterThan(String desig) {
		return dao.myCustomQuery(desig);
	}
	
	
}
