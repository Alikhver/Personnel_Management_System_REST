package com.alikhver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_DEPARTMENT")
@Builder
public class Department {

    @Id
    @Column(name = "D_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "D_NAME")
    private String name;

    @Column(name = "D_PHONE")
    private String phoneNumber;

    @Column(name = "D_FOUNDATION_DATE")
    private Date foundationDate;
    @Column(name = "D_DESC")
    private String description;


    @OneToMany (mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Employee> employeeList;

}