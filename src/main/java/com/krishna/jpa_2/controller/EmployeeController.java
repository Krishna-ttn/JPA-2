package com.krishna.jpa_2.controller;

import com.krishna.jpa_2.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/high-salary")
    public ResponseEntity<List<Object[]>> getEmployeesWithHighSalary() {
        List<Object[]> employees = employeeRepository.findEmployeesWithSalaryGreaterThanAverage();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/update-salaries")
    @Transactional
    public ResponseEntity<String> updateEmployeeSalaries(@RequestParam Double newSalary) {
        Double avgSalary = employeeRepository.findAverageSalary();
        employeeRepository.updateSalaries(newSalary, avgSalary);
        return ResponseEntity.ok("Salaries updated successfully");
    }

    @DeleteMapping("/delete-min-salary")
    @Transactional
    public ResponseEntity<String> deleteEmployeesWithMinSalary() {
        Double minSalary = employeeRepository.findMinSalary();
        if (minSalary == null) {
            return ResponseEntity.ok("No employees found to delete");
        }
        int deletedCount = employeeRepository.deleteEmployeesWithMinSalary(minSalary);
        return ResponseEntity.ok(deletedCount + " employees deleted with minimum salary");
    }

    @GetMapping("/last-name-singh")
    public ResponseEntity<List<Object[]>> getEmployeesWithLastNameSingh() {
        List<Object[]> employees = employeeRepository.findEmployeesWithLastNameSingh();
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/delete-by-age/{age}")
    @Transactional
    public ResponseEntity<String> deleteEmployeesWithAgeGreaterThan(@PathVariable int age) {
        int deletedCount = employeeRepository.deleteEmployeesWithAgeGreaterThan(age);
        return ResponseEntity.ok(deletedCount + " employees deleted with age greater than " + age);
    }
}

