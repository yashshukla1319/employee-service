package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.List;

@Component
public class PersistenceLayer {

    @Autowired
    public CacheDAO cacheDAO;
    @Autowired
    public RepoImplementation repoImplementation;

    public Employee getEmployeeById(Integer id) throws SQLException {
        Employee exist = null;
        if (cacheDAO.getEmployeeFromCache(id) != null){
            exist = cacheDAO.getEmployeeFromCache(id);
        }
        repoImplementation.getEmployeeById(id);
        return exist;
    }

    public List<Employee> findAllByDeptId(Integer deptId) throws SQLException{
        return repoImplementation.findAllByDeptId(deptId);
    }

    public List<Employee> getEmployeeByName(String name) throws SQLException{
        return repoImplementation.getEmployeeByName(name);
    }

    public List<Employee> findByIdIn(List<Integer> id) throws SQLException{
        return repoImplementation.findByIdIn(id);
    }

    public List<Employee> getEmployee() throws SQLException{
        List<Employee> employees = repoImplementation.getEmployee();
        return employees;
    }

    public Employee addNewEmployee(Employee employee) throws SQLException{
        cacheDAO.addEmployeeInCache(employee.getId(), employee);
        repoImplementation.addNewEmployee(employee);
        Employee exist = cacheDAO.getEmployeeFromCache(employee.getId());
        if (exist != null){
            exist = cacheDAO.getEmployeeFromCache(employee.getId());
        }
        else{
            repoImplementation.getEmployeeById(employee.getId());
        }
        return exist;
    }
    public void deleteEmployee(Integer id) throws SQLException{
        cacheDAO.deleteEmployeeInCache(id);
        repoImplementation.deleteEmployee(id);
    }
    public Employee updateEmployee(Employee employee) throws SQLException{
        cacheDAO.updateEmployeeInCache(employee.getId(),employee);
        repoImplementation.updateEmployee(employee);
        Employee exist = cacheDAO.getEmployeeFromCache(employee.getId());
        if (exist != null){
            exist = cacheDAO.getEmployeeFromCache(employee.getId());
        }
        else{
            repoImplementation.getEmployeeById(employee.getId());
        }
        return exist;
    }
}
