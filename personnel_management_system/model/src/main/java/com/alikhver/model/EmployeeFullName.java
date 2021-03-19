package com.alikhver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeeFullName {

    @Column(name = "E_NAME")
    private String name;

    @Column(name = "E_SURNAME")
    private String Surname;

    @Column(name = "E_MIDDLE_NAME")
    private String middleName;

}
