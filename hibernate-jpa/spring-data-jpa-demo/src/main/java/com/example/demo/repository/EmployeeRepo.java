package com.example.demo.repository;

import com.example.demo.dto.DepartmentCountDto;
import com.example.demo.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    // @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    // List<Employee> findBySalaryGreaterThan(double salary);

    // @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    // List<Employee> findByNameLike(String name);

    // @Query("SELECT e FROM Employee e WHERE e.department = :department")
    // List<Employee> findByDepartment(String department);

    // @Query("SELECT e FROM Employee e WHERE e.age > :age")
    // List<Employee> findByAgeGreaterThan(int age);

    // @Query("SELECT e FROM Employee e WHERE e.age < :age")
    // List<Employee> findByAgeLessThan(int age);

    // @Query("SELECT e FROM Employee e WHERE e.age >= :age")
    // List<Employee> findByAgeGreaterThanOrEqual(int age);

    // @Query("SELECT e FROM Employee e WHERE e.age <= :age")
    // List<Employee> findByAgeLessThanOrEqual(int age);
    
    // @Query("SELECT e FROM Employee e WHERE e.age BETWEEN :minAge AND :maxAge")
    // List<Employee> findByAgeBetween(int minAge, int maxAge);

    // @Query("SELECT e FROM Employee e WHERE e.age NOT BETWEEN :minAge AND :maxAge")
    // List<Employee> findByAgeNotBetween(int minAge, int maxAge);

    // @Query("SELECT e FROM Employee e WHERE e.age IS NULL")
    // List<Employee> findByAgeIsNull();
    
    // @Query("SELECT e FROM Employee e WHERE e.age IS NOT NULL")
    // List<Employee> findByAgeIsNotNull();

    // @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.department = :department")
    // List<Employee> findByNameAndDepartment(String name, String department);

    // @Query("SELECT e FROM Employee e WHERE e.name = :name OR e.department = :department")
    // List<Employee> findByNameOrDepartment(String name, String department);
    
    @Query("""
            select new com.example.demo.dto.DepartmentCountDto(e.department, count(e))
            from Employee e
            group by e.department
            """)
    List<DepartmentCountDto> findDepartmentAndCount();
}
