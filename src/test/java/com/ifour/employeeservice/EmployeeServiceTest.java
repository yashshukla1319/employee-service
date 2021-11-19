package com.ifour.employeeservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;
    @MockBean
    EmployeeRepository employeeRepository;

    Employee employee = new Employee();

    @Test
    void getEmployee() throws SQLException {
        List<Employee> employeeList = new  ArrayList<>();
        employeeList.add(employee);
        Mockito.when(employeeRepository.getEmployee()).thenReturn(employeeList);
        List<Employee> output = employeeRepository.getEmployee();
        assertEquals(employeeList,output);
    }

    @Test
    void getEmployeeById() throws SQLException {
        Employee employeeList = new Employee();
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(employeeList);
        Employee output = employeeService.getEmployeeById(1);
        assertEquals(employeeList,output);
    }

    @Test
    void addNewEmployee() throws SQLException {
        employee.setName("amit");
        employee.setDeptId(201);
        employee.setId(1);
        Mockito.when(employeeRepository.addNewEmployee(Mockito.any())).thenReturn(employee);
        Employee output = employeeService.addNewEmployee(employee);
        assertEquals(employee,output);
    }

    @Test
    void deleteEmployee() throws SQLException {
        EmployeeService employeeService1 = Mockito.mock(EmployeeService.class);
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn((employee));
        employeeService1.deleteEmployee(1);
        Mockito.verify(employeeService1,Mockito.times(1)).deleteEmployee(1);
    }

    @Test
    void updateEmployee() throws SQLException {
        employee.setName("karan");
        employee.setDeptId(201);
        employee.setId(2);
        employee.setSalary(5000);
        Mockito.when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn((employee));
        Mockito.when(employeeRepository.addNewEmployee(Mockito.any())).thenReturn(employee);
        Employee output = employeeService.updateEmployee(employee);
        assertEquals(employee,output);
    }

    @Test
    void findAllByDeptId() throws SQLException {

        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);
        Mockito.when(employeeRepository.findAllByDeptId(Mockito.anyInt())).thenReturn(employeesList);
        List<Employee> output = employeeService.findAllByDeptId(101);
        assertEquals(employeesList,output);
    }

    @Test
    void getEmployeeByName() throws SQLException {
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);
        Mockito.when(employeeRepository.getEmployeeByName(Mockito.any())).thenReturn(employeesList);
        List<Employee> output = employeeService.getEmployeeByName("yash");
        assertEquals(employeesList,output);
    }

    @Test
    void getEmployeeIn() throws SQLException {
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);
        Mockito.when(employeeRepository.findByIdIn(Mockito.any())).thenReturn(employeesList);
        List<Employee> output = employeeService.getEmployeeIn(List.of(101,102));
        assertEquals(employeesList,output);
    }
}