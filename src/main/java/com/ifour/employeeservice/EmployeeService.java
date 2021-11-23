package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    HashMap hashMap = new HashMap();

    public List<Employee> getEmployee() throws SQLException {
        //Pageable pageable = PageRequest.of(no,size);
        //Page<Employee> employeePage = employeeRepository.getEmployee(pageable);
        List<Employee> employeePage = new ArrayList<>();
        return employeeRepository.getEmployee();
    }

    public Employee getEmployeeById(Integer id) throws SQLException {
        return employeeRepository.getEmployeeById(id);
    }

    public Employee addNewEmployee(Employee employee) throws SQLException {
        Employee getEmployeeById = employeeRepository.getEmployeeById(employee.getId());
        if (getEmployeeById != null) {
            throw new IllegalStateException("Id already present.");
        }
        return employeeRepository.addNewEmployee(employee);
    }

    public void deleteEmployee(Integer id) throws SQLException {
        Employee exist = employeeRepository.getEmployeeById(id);
        if (exist == null) {
            throw new IllegalStateException("Employee with Id" + id + "does not exists");
        }
        employeeRepository.deleteEmployee(id);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) throws SQLException {
        Employee exists = employeeRepository.getEmployeeById(employee.getId());
        if (exists == null) {
            throw new IllegalStateException("Employee with this id does not exist");
        }
        return employeeRepository.updateEmployee(employee);
    }

    public List<Employee> findAllByDeptId(Integer deptId) throws SQLException {
        return employeeRepository.findAllByDeptId(deptId);

    }

    public List<Employee> getEmployeeByName(String name) throws SQLException {
        return employeeRepository.getEmployeeByName(name);
    }

    public List<Employee> getEmployeeIn(List<Integer> id) throws SQLException {
        return employeeRepository.findByIdIn(id);
    }
}


