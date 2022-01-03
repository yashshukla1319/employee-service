package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public PersistenceLayer persistenceLayer;

    public List<Employee> getEmployee() throws SQLException {
        return persistenceLayer.getEmployee();
    }

    public Employee getEmployeeById(Integer id) throws SQLException {
        Employee exist = persistenceLayer.getEmployeeById(id);
        if (exist == null){
            System.out.println("Employee Does not Exist...!");
        }
        return persistenceLayer.getEmployeeById(id);
    }

    public Employee addNewEmployee(Employee employee) throws SQLException {
        Employee search = new Employee();
        Employee getEmployeeById = persistenceLayer.getEmployeeById(employee.getId());
        if (getEmployeeById.getId() != null) {
            throw new IllegalStateException("Id already present.");
        }
        return persistenceLayer.addNewEmployee(employee);
    }

    public void deleteEmployee(Integer id) throws SQLException {
        Employee exist = persistenceLayer.getEmployeeById(id);
        if (exist == null) {
            throw new IllegalStateException("Employee with Id" + id + "does not exists");
        }
        persistenceLayer.deleteEmployee(id);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) throws SQLException {
        Employee exists = persistenceLayer.getEmployeeById(employee.getId());
        if (exists == null) {
            throw new IllegalStateException("Employee with this id does not exist");
        }
        return persistenceLayer.updateEmployee(employee);
    }

    public List<Employee> findAllByDeptId(Integer deptId) throws SQLException {
        return persistenceLayer.findAllByDeptId(deptId);

    }

    public List<Employee> getEmployeeByName(String name) throws SQLException {
        return persistenceLayer.getEmployeeByName(name);
    }

    public List<Employee> getEmployeeIn(List<Integer> id) throws SQLException {
        return persistenceLayer.findByIdIn(id);
    }
}


