package com.ifour.employeeservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    void getEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> output = employeeRepository.findAll();
        assertEquals(employeeList,output);
    }

    @Test
    void getEmployeeById(){
        Optional<Employee> employeeList = Optional.of(employee);
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(employeeList);
        Optional<Employee> output = employeeService.getEmployeeById(1);
        assertEquals(employeeList,output);
    }

    @Test
    void addNewEmployee(){
        employee.setName("amit");
        employee.setDeptId(201);
        employee.setId(1);
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        Employee output = employeeService.addNewEmployee(employee);
        assertEquals(employee,output);
    }

    @Test
    void deleteEmployee(){
        EmployeeService employeeService1 = Mockito.mock(EmployeeService.class);

        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.existsById(1)).thenReturn(true);
        employeeService1.deleteEmployee(1);
        Mockito.verify(employeeService1,Mockito.times(1)).deleteEmployee(1);
    }

    @Test
    void updateEmployee(){
        employee.setName("karan");
        employee.setDeptId(201);
        employee.setId(2);
        employee.setSalary(5000);
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        Employee output = employeeService.updateEmployee(2,"karan","201",5000);
        assertEquals(employee,output);
    }

    @Test
    void findAllByDeptId(){

        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);

        Mockito.when(employeeRepository.findAllByDeptId(Mockito.anyInt())).thenReturn(employeesList);
        List<Employee> output = employeeService.findAllByDeptId(101);

        assertEquals(employeesList,output);
    }

    @Test
    void getEmployeeByName() {
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);

        Mockito.when(employeeRepository.findAllByName(Mockito.any())).thenReturn(employeesList);
        List<Employee> output = employeeService.getEmployeeByName("yash");

        assertEquals(employeesList,output);
    }

    @Test
    void getEmployeeIn() {
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);

        Mockito.when(employeeRepository.findByIdIn(Mockito.any())).thenReturn(employeesList);
        List<Employee> output = employeeService.getEmployeeIn(List.of(101,102));

        assertEquals(employeesList,output);
    }
}