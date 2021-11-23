package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RepoImplementation implements EmployeeRepository {

    Connection con;
    ResultSet rs;
    HashMap<Integer,Employee> hashMap = new HashMap();
    RepoImplementation repoImplementation;

    @Autowired
    private ConnectionPoolClass connectionPoolClass;

    @Override
    public List<Employee> findAllByDeptId(Integer deptId) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Select * from Employee where deptId=?");
        stmt.setInt(1,deptId);
        rs = stmt.executeQuery();
        while (rs.next() && rs!=null) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getInt("salary"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
            employees.add(employee);
        }
        connectionPoolClass.releaseConnection();
        return employees;
    }

    @Override
    public List<Employee> getEmployeeByName(String name) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Select * from Employee where name=?");
        stmt.setString(1,name);
        rs = stmt.executeQuery();
        while (rs.next() && rs!=null) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
            employee.setSalary(rs.getInt("salary"));
            employees.add(employee);
        }
        connectionPoolClass.releaseConnection();
        return employees;
    }

    @Override
    public List<Employee> findByIdIn(List<Integer> id) throws SQLException {
        int i=0;
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList<>();
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Select * from Employee where id IN(?)");
        stmt.setObject(1,id);
        rs = stmt.executeQuery();
        while (rs.next() && rs!=null) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
            employee.setSalary(rs.getInt("salary"));
            employees.add(employee);
        }
        connectionPoolClass.releaseConnection();
        return employees;
    }

    @Override
    public List<Employee> getEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Select * from Employee");
        rs = stmt.executeQuery();
        while (rs.next() && rs!=null) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
            employee.setSalary(rs.getInt("salary"));
            employees.add(employee);
        }
        connectionPoolClass.releaseConnection();
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer id) throws SQLException {
        Employee employee = new Employee();
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Select * from Employee where id=?");
        stmt.setInt(1,id);
        rs = stmt.executeQuery();
        while (rs.next() && rs!=null) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
            employee.setSalary(rs.getInt("salary"));
        }
        connectionPoolClass.releaseConnection();
        return employee;
    }

    @Override
    public Employee addNewEmployee(Employee employee) throws SQLException {
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Insert into Employee(id,name,salary,deptId,deptName) values(?,?,?,?,?)");
        stmt.setInt(1,employee.getId());
        stmt.setString(2,employee.getName());
        stmt.setInt(3,employee.getSalary());
        stmt.setInt(4,employee.getDeptId());
        stmt.setString(5,employee.getDeptName());

        int add = stmt.executeUpdate();
        while (add != 0) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getInt("salary"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
        }
        connectionPoolClass.releaseConnection();
        return employee;
    }

    @Override
    public void deleteEmployee(Integer id) throws SQLException {
        con = connectionPoolClass.getConnection();
        PreparedStatement statement = con.prepareStatement("Delete from Employee where id=?");
        statement.setInt(1,id);
        int delete = statement.executeUpdate();
        System.out.println("Successfully Deleted...!!");
        connectionPoolClass.releaseConnection();
    }

    @Override
    public Employee updateEmployee(Employee employee) throws SQLException {
        con = connectionPoolClass.getConnection();
        PreparedStatement stmt = con.prepareStatement("Update Employee set id=?, name=?, salary=?, deptId=?, deptName=? where id=?");
        stmt.setInt(1,employee.getId());
        stmt.setString(2,employee.getName());
        stmt.setInt(3,employee.getSalary());
        stmt.setInt(4,employee.getDeptId());
        stmt.setString(5,employee.getDeptName());
        stmt.setInt(6,employee.getId());
        rs = stmt.executeQuery();
        while (rs.next() && rs!=null) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDeptId(rs.getInt("deptId"));
            employee.setDeptName(rs.getString("deptName"));
            employee.setSalary(rs.getInt("salary"));
        }
        connectionPoolClass.releaseConnection();
        return employee;
    }
}