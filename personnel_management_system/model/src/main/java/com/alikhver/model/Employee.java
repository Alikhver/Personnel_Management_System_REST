package com.alikhver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_EMPLOYEE")
@Entity
@Builder
@JsonPropertyOrder({"id", "fullName", "position", "email", "phoneNumber", "hiringDate", "birthDate"})
public class Employee {

    @Id
    @Column(name = "E_ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Embedded
    private EmployeeFullName fullName;

    @Column(name = "E_BIRTH_DATE")
    private Date dateOfBirth;

    @Column(name = "E_PHONE")
    private String phoneNumber;

    @Column(name = "E_MAIL")
    private String email;

    @Column(name = "E_POSITION")
    private String position;

    @Column(name = "E_HIRING_DATE")
    private Date hiringDate;

    @ManyToOne
    @JoinColumn(name = "D_ID")
    @JsonIgnore
    private Department department;
}





