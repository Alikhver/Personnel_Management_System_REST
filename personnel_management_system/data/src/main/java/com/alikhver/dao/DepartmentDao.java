package com.alikhver.dao;


import com.alikhver.model.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> getAllDepartments();

    Department getDepartment(int id);

    void deleteDepartment(int id);

    String createDepartment(Department department);

    void deleteEmployeeFromDepartment(String employeeId);
}
