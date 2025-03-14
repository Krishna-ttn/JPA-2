package com.krishna.jpa_2.Repository;

import com.krishna.jpa_2.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Get first name and last name of employees with salary > average salary, ordered by age ASC, salary DESC
    @Query("SELECT e.firstName, e.lastName FROM Employee e " +
            "WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2) " +
            "ORDER BY e.age ASC, e.salary DESC")
    List<Object[]> findEmployeesWithSalaryGreaterThanAverage();

    //Update salary of all employees by a salary passed as a parameter whose
    //existing salary is less than the average salary.
    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double findAverageSalary();

    @Modifying
    @Query("UPDATE Employee e SET e.salary = :newSalary WHERE e.salary < :avgSalary")
        //@Param not necessary here
    void updateSalaries(@Param("newSalary") Double newSalary, @Param("avgSalary") Double avgSalary);


    //Delete all employees with minimum salary.
    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.salary = (SELECT MIN(e2.salary) FROM Employee e2)")
    int deleteEmployeesWithMinSalary();

    //Get id, first name, age of employees where last name ends with "singh"
    @Query(value = "SELECT empId, empFirstName, empAge FROM employeeTable WHERE empLastName LIKE '%singh'", nativeQuery = true)
    List<Object[]> findEmployeesWithLastNameSingh();

    //Delete employees with age > given age
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employeeTable WHERE empAge > :age", nativeQuery = true)
    int deleteEmployeesWithAgeGreaterThan(int age);
}
