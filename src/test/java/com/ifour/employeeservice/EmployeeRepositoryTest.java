package com.ifour.employeeservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {
    @MockBean
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    Employee employee = new Employee();

    @Test
    void findAllByDeptId() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeRepository.findAllByDeptId(Mockito.anyInt())).thenReturn(employees);
        List<Employee> employeeList = employeeService.findAllByDeptId(101);
        assertEquals(employees, employeeList);
    }
}