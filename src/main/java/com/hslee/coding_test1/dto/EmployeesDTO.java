package com.hslee.coding_test1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmployeesDTO {
    private Long empNo;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate hireDate;
    private String deptName;
    private String title;
    private Integer maxSalary;
}

