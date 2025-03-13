package com.krishna.jpa_2.Repository;

import com.krishna.jpa_2.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    // 1) Get first name and last name of employees with salary > average salary, ordered by age ASC, salary DESC
    @Query("SELECT e.firstName, e.lastName FROM Employee e " +
            "WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2) " +
            "ORDER BY e.age ASC, e.salary DESC")
    List<Object[]> findEmployeesWithSalaryGreaterThanAverage();

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.salary = :newSalary " +
            "WHERE e.salary < (SELECT AVG(e2.salary) FROM Employee e2)")
    int updateSalaryForEmployeesBelowAverage(double newSalary);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.salary = (SELECT MIN(e2.salary) FROM Employee e2)")
    int deleteEmployeesWithMinSalary();

    // 1) Get id, first name, age of employees where last name ends with "singh"
    @Query(value = "SELECT empId, empFirstName, empAge FROM employeeTable WHERE empLastName LIKE '%singh'", nativeQuery = true)
    List<Object[]> findEmployeesWithLastNameSingh();

    // 2) Delete employees with age > given age
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employeeTable WHERE empAge > :age", nativeQuery = true)
    int deleteEmployeesWithAgeGreaterThan(int age);
}
