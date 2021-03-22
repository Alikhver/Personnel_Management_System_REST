package com.alikhver.service;


import com.alikhver.dao.DepartmentDao;
import com.alikhver.dao.EmployeeDao;
import com.alikhver.model.Department;
import com.alikhver.model.Employee;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;

    private final EmployeeDao employeeDao;

    public DepartmentService(DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    public Department getDepartment(Integer id) throws NotFoundException {
        final Department department = departmentDao.getDepartment(id);
        if (department == null) {
            throw new NotFoundException("Department with Id=" + id + " does not exist");
        }
        return department;
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    public void deleteDepartment(int id) throws IllegalArgumentException, NotFoundException {
        Department department = this.getDepartment(id);
        department.getEmployeeList().
                forEach(employee -> departmentDao.deleteEmployeeFromDepartment(employee.getId()));
        departmentDao.deleteDepartment(id);
    }

    public void createDepartment(Department department) throws IllegalArgumentException {
        if (department == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        departmentDao.createDepartment(department);
    }

    public void addEmployee(int departmentId, String employeeId) throws NotFoundException {
        Employee employee = employeeDao.getEmployee(employeeId);
        if (employee == null) {
            throw new NotFoundException("No employee with Id=" + employeeId + " found");
        }
        Department department = departmentDao.getDepartment(departmentId);
        employee.setDepartment(department);
        employeeDao.save(employee);
    }

    public void deleteEmployeeFromDepartment(String employeeId) throws NotFoundException {
        Employee employee = employeeDao.getEmployee(employeeId);
        if (employee == null) {
            throw new NotFoundException("No employee with Id=" + employeeId + " found");
        }
        employee.setDepartment(null);
        employeeDao.save(employee);
    }

    public List<Employee> getAllEmployeesOfDepartment(Integer departmentId) throws NotFoundException {
        Department department;
        department = departmentDao.getDepartment(departmentId);

        if (department == null) {
            throw new NotFoundException("No department with Id=" + departmentId + " found");
        }
        return department.getEmployeeList();
    }
}
