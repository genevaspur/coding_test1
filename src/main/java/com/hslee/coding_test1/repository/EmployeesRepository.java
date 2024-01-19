package com.hslee.coding_test1.repository;

import com.hslee.coding_test1.dto.EmployeesDTO;
import com.hslee.coding_test1.repository.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    @Query("SELECT new com.hslee.coding_test1.dto.EmployeesDTO(" +
            "e.empNo, " +
            "e.firstName, " +
            "e.lastName, " +
            "e.gender, " +
            "e.hireDate, " +
            "d.deptName, " +
            "t.title, " +
            "(SELECT MAX(s.salary) FROM Salaries s WHERE s.employees = e)) " +
            "FROM Employees e " +
            "JOIN e.deptEmp de " +
            "JOIN de.departments d " +
            "JOIN e.titles t " +
            "WHERE e.hireDate >= :hireDate " +
            "GROUP BY e.empNo, d.deptName, t.title")
    List<EmployeesDTO> findEmployeeDetailsAfterHireDate(@Param("hireDate") LocalDate hireDate);

}

