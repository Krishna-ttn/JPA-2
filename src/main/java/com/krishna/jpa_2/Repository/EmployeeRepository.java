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
    @Query("SELECT MIN(e.salary) FROM Employee e")
    Double findMinSalary();

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.salary = :minSalary")
    int deleteEmployeesWithMinSalary(@Param("minSalary") Double minSalary);

    //Get id, first name, age of employees where last name ends with "singh"
    @Query(value = "SELECT emp_id, emp_first_name, emp_age FROM employeetable WHERE emp_last_name LIKE '%singh'", nativeQuery = true)
    List<Object[]> findEmployeesWithLastNameSingh();

    //Delete employees with age > given age
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employeetable WHERE emp_age > :age", nativeQuery = true)
    int deleteEmployeesWithAgeGreaterThan(int age);
}
