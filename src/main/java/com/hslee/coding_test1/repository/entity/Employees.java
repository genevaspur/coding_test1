package com.hslee.coding_test1.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employees {
    @Id
    @Column(name = "emp_no")
    private Long empNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employees")
    private Set<Salaries> salaries;

    @OneToMany(mappedBy = "employees")
    private Set<DeptEmp> deptEmp;

    @OneToMany(mappedBy = "employees")
    private Set<Titles> titles;
}
