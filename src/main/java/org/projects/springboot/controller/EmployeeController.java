package org.projects.springboot.controller;

import java.util.List;

import org.projects.springboot.entity.Employee;
import org.projects.springboot.exception.ResourceNotFoundException;
import org.projects.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/V1/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees for display
	@GetMapping()
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	// sort all employees 
	@GetMapping("/sort")
	public List<Employee> getAllSortedEmployees(){
		
		return employeeRepository.findAll(Sort.by("firstName"));
		
	}
	
	// sort all employees 
	@GetMapping("/sortByLastName")
	public List<Employee> getAllSortedEmployeesByLastName(){
		
		return employeeRepository.findAll(Sort.by("lastName"));
		
	}
	
	// sort all employees descending order!
	@GetMapping("/sort/desc")
	public List<Employee> getAllSortedEmployeesDown() {

		return employeeRepository.findAll(Sort.by("firstName").descending());

	}
	
	// Sort Employee by Id
	@GetMapping("/sortById")
	public List<Employee> getAllSortedEmployeesById(){
		return employeeRepository.findAll(Sort.by("id"));
	}
	
	// lives at http://localhost:8080/api/V1 for post testing
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	// get employee by ID 
	// test just by using no BRACKETS! http://localhost:8080/api/V1/3 ex.
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));// import
																										// Resourcenotfound
		return ResponseEntity.ok(employee);
	}
	
	// update employee REST API, PUT = UPDATE
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateStudent(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Employee employeeUpdate = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " does not exist"));

		// update employee with data
		employeeUpdate.setFirstName(employeeDetails.getFirstName());
		employeeUpdate.setLastName(employeeDetails.getLastName());
		employeeUpdate.setEmail(employeeDetails.getEmail());

		// call the repo to save the updated employee data
		employeeRepository.save(employeeUpdate);

		// return data to client
		return ResponseEntity.ok(employeeUpdate);
	}
	
	// delete employee rest api
		@DeleteMapping("{id}")
		public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee with the id " + id + " does not exist"));

			employeeRepository.delete(employee);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

}
