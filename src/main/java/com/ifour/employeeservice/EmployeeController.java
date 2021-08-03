package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping(path = "/getAllEmployees")
    public List<Employee> getEmployee(@RequestParam("no") int no,@RequestParam("size") int size)
    {
        return employeeService.getEmployee(no,size);
    }

    @RequestMapping(path = "/getEmployeeById/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id")Integer id)
    {
        System.out.println(id);
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(path = "/getEmployeeByListId")
    public List<Employee> getEmployeeIn(@RequestParam("id") String id)
    {
        List<String> ids = Arrays.asList((id.split(",")));
        List<Integer> intIds =  ids.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return employeeService.getEmployeeIn(intIds);
    }

    @RequestMapping(path = "/getEmployeeByName")
    public List<Employee> getEmployeeByName(@RequestParam("name")String name)
    {
        return employeeService.getEmployeeByName(name);
    }

    @RequestMapping(path="/addNewEmployee", method = RequestMethod.POST)
    public void addNewEmployee(@RequestBody Employee employee)
    {
        employeeService.addNewEmployee(employee);
    }

    @RequestMapping(path = "/deleteEmployeeById/{employeeId}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("employeeId")Integer id)
    {
        employeeService.deleteEmployee(id);
    }


    @RequestMapping(path = "/updateExistingEmployeeById/{employeeId}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("employeeId") Integer employeeId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String deptId,
                               @RequestParam(required = false) Integer salary)
    {
        employeeService.updateEmployee(employeeId,name,deptId,salary);
    }
    @RequestMapping(path = "/getEmployeeByDeptId/{deptId}",method = RequestMethod.GET)
    public List<Employee> findAllByDeptId(@PathVariable("deptId")Integer deptId) {
        return employeeService.findAllByDeptId(deptId);//.stream().toList();

    }
}


