package com.ifour.employeeservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAllByDeptId(Integer deptId) throws SQLException;

    List<Employee> findByIdIn(List<Integer> id) throws SQLException;

    List<Employee> getEmployee() throws SQLException;

    Employee getEmployeeById(Integer id) throws SQLException;

    Employee addNewEmployee(Employee employee) throws SQLException;

    void deleteEmployee(Integer id) throws SQLException;

    Employee updateEmployee(Employee employee) throws SQLException;

    List<Employee> getEmployeeByName(String name) throws SQLException;
}