package com.alikhver.dao;

import com.alikhver.model.Department;
import com.alikhver.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    SessionFactory sessionFactory;

    private final EmployeeDao employeeDao;

    public DepartmentDaoImpl(SessionFactory sessionFactory, EmployeeDao employeeDao) {
        this.sessionFactory = sessionFactory;
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Department> getAllDepartments() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Department", Department.class)
                .list();
    }

    @Override
    public Department getDepartment(int id) {
        return sessionFactory.getCurrentSession().get(Department.class, id);
    }

    @Override
    public String createDepartment(Department department) {
        Serializable save = sessionFactory.getCurrentSession().save(department);
        return String.valueOf(save);
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = sessionFactory
                .getCurrentSession()
                .get(Department.class, id);
        sessionFactory.getCurrentSession().delete(department);
    }

    @Override
    public void deleteEmployeeFromDepartment(String employeeId) {
        Employee employee = employeeDao.getEmployee(employeeId);
        employee.setDepartment(null);
        employeeDao.save(employee);
    }
}
