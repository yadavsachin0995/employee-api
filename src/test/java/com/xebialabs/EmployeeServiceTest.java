package com.xebialabs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void testCreateEmployee(){
        Employee employee1 = new Employee();
        employee1.setEmpName("Sachin");
        employee1.setEmpId("0995");

        when(employeeRepo.save(employee1)).thenReturn(employee1);
        assertEquals("Created", employeeService.createEmployee(employee1));
    }

    @Test
    public void testGetAllEmployees(){
        Employee employee1 = new Employee();
        employee1.setEmpName("Sachin");
        employee1.setEmpId("0995");

        Employee employee2 = new Employee();
        employee2.setEmpId("0996");
        employee2.setEmpName("Abhinav");

        when(employeeRepo.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        assertEquals(Arrays.asList(employee1, employee2), employeeService.getAllEmployees());
    }

    @Test
    public void testGetEmployee(){
        Employee employee1 = new Employee();
        employee1.setEmpName("Sachin");
        employee1.setEmpId("0995");

        when(employeeRepo.findById("0995")).thenReturn(Optional.ofNullable(employee1));

        assertNull(employeeService.getEmployee("0996"));
        assertEquals(Optional.ofNullable(employee1), employeeService.getEmployee("0995"));
    }

    @Test
    public void testGetFilteredEmployees(){
        Employee employee1 = new Employee();
        employee1.setEmpName("Sachin");
        employee1.setEmpId("0995");
        employee1.setEmpSalary(122);
        employee1.setEmpDesignation("Trainee");

        Employee employee2 = new Employee();
        employee2.setEmpName("Abhinav");
        employee2.setEmpId("0996");
        employee2.setEmpSalary(123);
        employee2.setEmpDesignation("Trainee");

        when(employeeRepo.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        assertEquals(Arrays.asList(employee1, employee2), employeeService.getFilteredEmployees("Trainee",
                122));
        assertEquals(Arrays.asList(employee2), employeeService.getFilteredEmployees("Trainee", 123));
        assertEquals(Arrays.asList(), employeeService.getFilteredEmployees("Trainee", 124));
    }

    @Test
    public void testUpdateEmployee(){
        Employee employee = new Employee();

        when(employeeRepo.existsById(employee.getEmpId())).thenReturn(true);
        assertEquals("Updated", employeeService.updateEmployee(employee));
    }

    @Test
    public void testRemoveEmployee(){

    }
}
