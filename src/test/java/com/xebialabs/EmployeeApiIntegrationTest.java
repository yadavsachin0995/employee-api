package com.xebialabs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeApiIntegrationTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private Employee employee;

    @Before
    public void setup(){
        employee = new Employee();
        employee.setEmpId("0995");
        employee.setEmpName("Sachin");
        employee.setEmpDesignation("Trainee");
        employee.setEmpSalary(1234);
        employeeRepo.save(employee);
    }

    @Test
    public void createEmployeeIntegrationTest(){
        assertTrue(employeeController.createEmployee(employee).getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getEmployeeIntegrationTest() throws IOException {
        /*String testString = employeeController.getEmployee("0995").toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employeeTest = objectMapper.readValue(employeeController.getEmployee("0995"));
        assertEquals("Sachin", employeeTest.getEmpName());*/
    }

    @Test
    public void getEmployeesIntegrationTest() throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        employeeList = (List<Employee>) employeeController.getAllEmployees().getBody();
        //ObjectMapper objectMapper = new ObjectMapper();
        int countActual = 0;
        for (Employee employee : employeeList){
            countActual++;
        }
        assertEquals(1, countActual);
    }

    @Test
    public void getFilteredEmployeesIntegrationTest(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList = (List<Employee>) employeeController.getFilteredEmployees("Trainee", 0).getBody();
        //ObjectMapper objectMapper = new ObjectMapper();
        int countActual = 0;
        for (Employee employee : employeeList){
            countActual++;
        }
        assertEquals(1, countActual);
    }

    @Test
    public void putEmployeeIntegrationTest(){
        assertTrue(employeeController.putEmployee(employee).getStatusCode().is2xxSuccessful());
    }

    @Test
    public void removeEmployeeIntegrationTest(){
        assertTrue(employeeController.removeEmployee("0995").getStatusCode().is2xxSuccessful());
    }
}
