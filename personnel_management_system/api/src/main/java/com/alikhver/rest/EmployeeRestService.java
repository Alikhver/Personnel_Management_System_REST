package com.alikhver.rest;

import com.alikhver.model.Employee;
import com.alikhver.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/employees")
public class EmployeeRestService {

    private final EmployeeService employeeService;

    public EmployeeRestService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Get employee with Id specified")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) throws NotFoundException {
        final Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    @ApiOperation("Get all employees with department unspecified")
    public ResponseEntity<List<Employee>> getAllEmployeeWithoutDepartment() {
        List<Employee> employees = employeeService.getAllEmployeeWithoutDepartment();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Delete employee with Id specified")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id) throws NotFoundException {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/")
    @ApiOperation("Create employee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) throws IllegalArgumentException {
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(employee.getId(), HttpStatus.CREATED);
    }

}
