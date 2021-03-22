package com.alikhver.dao;


import com.alikhver.model.Department;
import javassist.NotFoundException;

import java.util.List;

public interface DepartmentDao {
    List<Department> getAllDepartments();

    Department getDepartment(int id);

    void deleteDepartment(int id) throws NotFoundException;

    void createDepartment(Department department);

    void deleteEmployeeFromDepartment(String employeeId);
}
