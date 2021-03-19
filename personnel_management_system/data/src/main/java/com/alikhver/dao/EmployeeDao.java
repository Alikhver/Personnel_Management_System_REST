package com.alikhver.dao;


import com.alikhver.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployeeWithoutDepartment();

    Employee getEmployee(String id);

    void save(Employee employee);

    void deleteEmployee(String id);
}
