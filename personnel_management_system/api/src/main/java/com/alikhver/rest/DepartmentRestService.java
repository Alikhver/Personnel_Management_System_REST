package com.alikhver.rest;

import com.alikhver.model.Department;
import com.alikhver.model.Employee;
import com.alikhver.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentRestService {

    private final DepartmentService departmentService;

    public DepartmentRestService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/{departmentId}")
    @ApiOperation("Get Department by Id")
    public ResponseEntity<Department> getDepartment(@PathVariable int departmentId) throws NotFoundException {
        final Department department = departmentService.getDepartment(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    @ApiOperation("Get all Departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{departmentId}")
    @ApiOperation(value = "Remove department with specified Id")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Integer departmentId) throws NotFoundException {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/")
    @ApiOperation("Create department")
    public ResponseEntity<HttpStatus> createDepartment(@RequestBody Department department) throws IllegalArgumentException {
        departmentService.createDepartment(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/manageEmployees/{departmentId}")
    @ApiOperation("Add employee to department specified")
    public ResponseEntity<HttpStatus> addEmployee(@PathVariable int departmentId, @RequestBody String employeeId) throws NotFoundException {
        departmentService.addEmployee(departmentId, employeeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/manageEmployees/{employeeId}")
    @ApiOperation("Delete employee from department")
    public ResponseEntity<HttpStatus> deleteEmployeeFromDepartment(@PathVariable String employeeId) throws NotFoundException {
        departmentService.deleteEmployeeFromDepartment(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/manageEmployees/{departmentId}")
    @ApiOperation("Get all Employees from department specified")
    public ResponseEntity<List<Employee>> getAllEmployeesOfDepartment(@PathVariable Integer departmentId) throws NotFoundException {
        List<Employee> employees = departmentService.getAllEmployeesOfDepartment(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
