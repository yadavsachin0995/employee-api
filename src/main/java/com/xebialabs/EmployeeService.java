package com.xebialabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    // Saves an employee in the database
    public String createEmployee(Employee employee){
        employeeRepo.save(employee);
        return "Created";
    }

    // Gets an ArrayList of all the employees stored in the database
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employeeRepo.findAll()){
            employeeList.add(employee);
        }
        return employeeList;
    }


    // Gets an employee whose empId matches to the one which is passed to the method
    public Optional<Employee> getEmployee(String empId){
        return employeeRepo.findById(empId);
    }


    /* Gets a list of Employees whose Designation matches and Salary is greater than or
     equal to the one that is passed
     */
    public List<Employee> getFilteredEmployees(String empDesignation, int empSalary){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employeeRepo.findAll()){
            if (employee.getEmpDesignation().equalsIgnoreCase(empDesignation) && employee.getEmpSalary() >= empSalary){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    // Update a record if it exists in the database
    public String updateEmployee(Employee employee){
        if (!employeeRepo.existsById(employee.getEmpId())){
            throw new RuntimeException("Record does not exist, try POST method to save a new Record");
        }
        employeeRepo.save(employee);
        return "Updated";
    }

    // Delete a record of an Employee with ID equals to empId
    public String removeEmployee(String empId){
        employeeRepo.deleteById(empId);
        return "Deleted";
    }
}