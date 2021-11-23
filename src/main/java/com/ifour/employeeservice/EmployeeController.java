package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @RequestMapping(path = "/getAllEmployees")
    public List<Employee> getEmployee() throws SQLException {
        return employeeService.getEmployee();
    }

    @RequestMapping(path = "/getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable("id")Integer id) throws SQLException {
        System.out.println(id);
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(path = "/getEmployeeByListId/{id}")
    public List<Employee> getEmployeeIn(@PathVariable("id") String id) throws SQLException {
        List<String> ids = Arrays.asList((id.split(",")));
        List<Integer> intIds =  ids.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return employeeService.getEmployeeIn(intIds);
    }

    @RequestMapping(path = "/getEmployeeByName/{name}")
    public List<Employee> getEmployeeByName(@PathVariable("name")String name) throws SQLException {
        return employeeService.getEmployeeByName(name);
    }

    @RequestMapping(path="/addNewEmployee", method = RequestMethod.POST)
    public void addNewEmployee(@RequestBody Employee employee) throws SQLException {
        employeeService.addNewEmployee(employee);
    }

    @RequestMapping(path = "/deleteEmployeeById/{employeeId}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("employeeId")Integer id) throws SQLException {
        employeeService.deleteEmployee(id);
    }

    @RequestMapping(path = "/updateExistingEmployeeById/{employeeId}", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody Employee employee,@PathVariable("employeeId") Integer employeeId) throws SQLException {
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(path = "/getEmployeeByDeptId/{deptId}",method = RequestMethod.GET)
    public List<Employee> findAllByDeptId(@PathVariable("deptId")Integer deptId) throws SQLException {
        return employeeService.findAllByDeptId(deptId);
    }
}


