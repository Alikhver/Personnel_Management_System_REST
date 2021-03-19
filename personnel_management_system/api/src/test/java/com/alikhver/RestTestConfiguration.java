//package com.alikhver;
//
//
//import com.alikhver.exception.MyIllegalArgumentException;
//import com.alikhver.model.Department;
//import com.alikhver.model.Employee;
//import com.alikhver.service.DepartmentService;
//import com.alikhver.service.EmployeeService;
//import javassist.NotFoundException;
//import org.mockito.Mockito;
//import org.springframework.context.annotation.*;
//
//
//import java.util.List;
//
//@Configuration
//@ComponentScan(basePackages = "com.alikhver")
//@Profile("test")
//public class RestTestConfiguration {
//
//    @Bean
//    @Primary
//    public DepartmentService departmentService() throws NotFoundException{
//        System.out.println("Call mock productService()");
//        DepartmentService departmentService =
//                Mockito.mock(DepartmentService.class);
//
//        Mockito.when(departmentService.getAllDepartments())
//                .thenReturn(List.of(new Department(), new Department()));
//
//        Mockito.when(departmentService.getDepartment(1))
//                .thenReturn(new Department());
//        Mockito.when(departmentService.getDepartment(2))
//                .thenThrow(NotFoundException.class);
//        Mockito.when(departmentService.getDepartment(null))
//                .thenThrow(IllegalArgumentException.class);
//
//        return departmentService;
//    }
//    @Bean
//    @Primary
//    public EmployeeService employeeService() {
//        System.out.println("Call mock productService()");
//        EmployeeService employeeService =
//                Mockito.mock(EmployeeService.class);
//
//        Mockito.when(employeeService.getAllEmployeeWithoutDepartment())
//                .thenReturn(List.of(new Employee(), new Employee()));
//        Mockito.when(employeeService.getAllEmployeeWithoutDepartment())
//                .thenReturn(List.of(new Employee(), new Employee()));
//        return employeeService;
//    }
//
//}
