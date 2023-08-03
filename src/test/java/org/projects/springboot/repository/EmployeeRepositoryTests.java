package org.projects.springboot.repository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.projects.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	// junit test for save employee
	@Test
	public void saveEmployeeTest() {
		 Employee employee = Employee.builder()
				 .firstName("Yushua")
				 .lastName("Senju")
				 .email("Ysenju@yahoo.com").build();
		 
		 employeeRepo.save(employee);
		 
		 Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	
	

}
