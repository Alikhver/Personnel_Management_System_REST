package com.alikhver.dao;

import com.alikhver.model.Department;
import com.alikhver.model.Employee;
import javassist.NotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = DaoConfigurationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    EmployeeDao employeeDao;

    private int departmentIdCounter = 1;

//    @BeforeClass
//    public static void setUp() {
//        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DaoConfigurationTest.class);
//
//        Department department = new Department();
//        department.setDescription("testDepartment");
//        department.setName("testDepartment");
//
//        DepartmentDao departmentDao = context.getBean(DepartmentDao.class);
//
//        departmentDao.createDepartment(department);
//        departmentDao.createDepartment(department);
//        departmentDao.createDepartment(department);
//
//        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);
//
//        Employee employee = new Employee();
//        employeeDao.save(employee);
//
//        context.close();
//    }

//    @AfterClass
//    public static void clearTable() {
//        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DaoConfigurationTest.class);
//
//        DepartmentDao departmentDao = context.getBean(DepartmentDao.class);
//        departmentDao.getAllDepartments().forEach(
//                item -> {
//                    try {
//                        departmentDao.deleteDepartment(item.getId());
//                    } catch (NotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
//        context.close();
//    }

    @Test
    public void createDepartment() {
        //Given
        Department department = new Department();
        department.setDescription("testDepartment1");
        department.setName("testDepartment1");

        //When
        departmentDao.createDepartment(department);

        //Then
        assertEquals(1, department.getId());
    }

    @Test
    public void deleteDepartment() throws NotFoundException {
        Department department;

        departmentDao.deleteDepartment(1);
        department = departmentDao.getDepartment(1);
        assertNull(department);
    }

}
