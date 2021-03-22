package com.alikhver.dao;


import com.alikhver.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeeWithoutDepartment() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Employee where D_ID is null", Employee.class)
                .list();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
       sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(String id) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, id);
        sessionFactory.getCurrentSession().delete(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(String id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

}
