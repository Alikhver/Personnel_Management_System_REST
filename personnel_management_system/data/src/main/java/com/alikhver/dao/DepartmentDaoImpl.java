package com.alikhver.dao;

import com.alikhver.model.Department;
import com.alikhver.model.Employee;
import javassist.NotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    private final SessionFactory sessionFactory;

    private final EmployeeDao employeeDao;

    public DepartmentDaoImpl(SessionFactory sessionFactory, EmployeeDao employeeDao) {
        this.sessionFactory = sessionFactory;
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public List<Department> getAllDepartments() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Department", Department.class)
                .list();
    }

    @Override
    @Transactional
    public Department getDepartment(int id) {
        return sessionFactory.getCurrentSession().get(Department.class, id);
    }

    @Override
    @Transactional
    public void createDepartment(Department department) {
        sessionFactory.getCurrentSession().save(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) throws NotFoundException {
        Department department = getDepartment(id);
        if (department == null) {
            throw new NotFoundException("Department with Id=" + id + " was not found");
        }
        sessionFactory.getCurrentSession().delete(department);
    }

    @Override
    @Transactional
    public void deleteEmployeeFromDepartment(String employeeId) {
        Employee employee = employeeDao.getEmployee(employeeId);
        employee.setDepartment(null);
        employeeDao.save(employee);
    }
}
