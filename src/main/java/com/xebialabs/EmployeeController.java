package com.xebialabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Controller
@RequestMapping("/employees")
@Produces("application/json")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<Employee> getAllEmployees(){
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @RequestMapping(value = "{empId}", method = RequestMethod.GET)
    ResponseEntity<Employee> getEmployee(@PathVariable("empId") String empId){
        return new ResponseEntity(employeeService.getEmployee(empId), HttpStatus.OK);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    ResponseEntity<Employee> getFilteredEmployees(@QueryParam("empDesignation") String empDesignation, @QueryParam("empSalary")
            int empSalary){
        return new ResponseEntity(employeeService.getFilteredEmployees(empDesignation, empSalary), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<String> putEmployee(@RequestBody Employee employee){
        return new ResponseEntity(employeeService.updateEmployee(employee), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{empId}", method = RequestMethod.DELETE)
    ResponseEntity<String> removeEmployee(@PathVariable("empId") String empId){
        return new ResponseEntity(employeeService.removeEmployee(empId), HttpStatus.OK);
    }
}
