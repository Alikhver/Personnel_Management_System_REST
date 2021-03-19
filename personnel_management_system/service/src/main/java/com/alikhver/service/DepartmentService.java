package com.alikhver.service;


import com.alikhver.dao.DepartmentDao;
import com.alikhver.dao.EmployeeDao;
import com.alikhver.model.Department;
import com.alikhver.model.Employee;
import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;

    private final EmployeeDao employeeDao;

    public DepartmentService(DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    @Transactional(readOnly = true)
    public Department getDepartment(Integer id) throws NotFoundException {
        final Department department = departmentDao.getDepartment(id);
        if (department == null) {
            throw new NotFoundException("Department with Id=" + id + " does not exist");
        }
        return department;
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Transactional
    public void deleteDepartment(int id) throws IllegalArgumentException, NotFoundException {
        Department department = this.getDepartment(id);
        department.getEmployeeList().
                forEach(employee -> departmentDao.deleteEmployeeFromDepartment(employee.getId()));
        departmentDao.deleteDepartment(id);
    }

    @Transactional
    public String createDepartment(Department department) throws IllegalArgumentException {
        if (department == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return departmentDao.createDepartment(department);
    }

    @Transactional
    public void addEmployee(int departmentId, String employeeId) throws NotFoundException {
        Employee employee = employeeDao.getEmployee(employeeId);
        if (employee.getId().isEmpty()) {
            throw new NotFoundException("No employee with Id=" + employeeId + " found");
        }
        Department department = departmentDao.getDepartment(departmentId);
        employee.setDepartment(department);
        employeeDao.save(employee);
    }

    @Transactional
    public void deleteEmployeeFromDepartment(String employeeId) throws NotFoundException {
        Employee employee = employeeDao.getEmployee(employeeId);
        if (employee.getId().isEmpty()) {
            throw new NotFoundException("No employee with Id=" + employeeId + " found");
        }
        employee.setDepartment(null);
        employeeDao.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeesOfDepartment(Integer departmentId) throws NotFoundException {
        Department department;
        department = departmentDao.getDepartment(departmentId);

        if (department == null) {
            throw new NotFoundException("No department with Id=" + departmentId + " found");
        }
        return department.getEmployeeList();
    }
}
