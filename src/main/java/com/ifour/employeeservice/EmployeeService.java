package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(int no, int size)
    {
        Pageable pageable = PageRequest.of(no,size);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.getContent();
    }

    public Optional<Employee> getEmployeeById(Integer id)
    {
        return employeeRepository.findById(id);
    }

    public Employee addNewEmployee(Employee employee) {
        Optional<Employee> getEmployeeById =  employeeRepository.findById(employee.getId());
        if(getEmployeeById != null && getEmployeeById.isPresent())
        {
            throw new IllegalStateException("Id already present.");
        }
        return employeeRepository.save(employee);
    }

    public String deleteEmployee(Integer id) {
        boolean exist = employeeRepository.existsById(id);
        if(!exist)
        {
            throw new IllegalStateException("Employee with Id"+id+"does not exists");
        }
        employeeRepository.deleteById(id);
        return"Employee with id"+id;
    }

    @Transactional
    public Employee updateEmployee(Integer id, String name, String deptId, Integer salary)
    {
        System.out.println(id);
        System.out.println(name);
        System.out.println(deptId);
        System.out.println(salary);
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new IllegalStateException("Employee with Id"+id+"is not present"));

        if(name !=null &&  !Objects.equals(employee.getName(),name))
        {
            employee.setName(name);
        }

        if(salary !=null && salary>0 && !Objects.equals(employee.getSalary(),salary))
        {
            employee.setSalary(Math.toIntExact(salary));
        }

        return employeeRepository.save(employee);
    }

    public List<Employee> findAllByDeptId(Integer deptId) {
        return employeeRepository.findAllByDeptId(deptId);

    }

    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findAllByName(name);
    }

    public List<Employee> getEmployeeIn(List<Integer> id) {
        return employeeRepository.findByIdIn(id);
    }
}


