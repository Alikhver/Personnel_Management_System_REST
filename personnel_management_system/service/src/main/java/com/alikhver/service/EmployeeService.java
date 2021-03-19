package com.alikhver.service;

import com.alikhver.dao.EmployeeDao;
import com.alikhver.model.Employee;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    
    @Transactional(readOnly = true)
    public Employee getEmployee(String id) throws NotFoundException {
        Employee employee = employeeDao.getEmployee(id);
        if (employee == null) {
            throw new NotFoundException("Employee with Id=" + id + " does not exist");
        }
        return employee;
    }

    @Transactional
    public void createEmployee(Employee employee) throws IllegalArgumentException {
        if (employee == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        employeeDao.save(employee);
    }

    @Transactional
    public void deleteEmployee(String id) throws NotFoundException {
        Employee employee = employeeDao.getEmployee(id);
        if (employee == null) {
            throw new NotFoundException("Employee with id=" + id + " does not exist");
        }
        employeeDao.deleteEmployee(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeeWithoutDepartment() {
        return employeeDao.getAllEmployeeWithoutDepartment();
    }

}
