package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CacheDAO {

    @Autowired
    RepoImplementation repoImplementation;

    HashMap<Integer,Employee> hashMap = new HashMap<>();

    public void addEmployeeInCache(Integer employeeId, Employee employee) {
        hashMap.put(employeeId, employee);
    }

    public Employee getEmployeeFromCache(Integer id) throws SQLException {
        Employee output = null;
        if (hashMap.containsKey(id)) {
            output = (Employee) hashMap.get(id);
        }
        else {
            return repoImplementation.getEmployeeById(id);
        }
        return output;
    }

    public void updateEmployeeInCache(Integer employeeId, Employee employee) throws SQLException {
        if (hashMap.containsKey(employeeId)) {
            hashMap.replace(employeeId, employee);
        }
        else{
            System.out.println("Does not exist");
        }
    }

    public void deleteEmployeeInCache(Integer employeeId) {
        if (hashMap.containsKey(employeeId)) {
            hashMap.remove(employeeId);
        }
        else{
            System.out.println("Does not exist");
        }
    }
    public List<Employee> findByIdIn(List<Integer> id) throws SQLException{
        List<Employee> exist = new ArrayList<>();
        for(Integer i :id){
            exist.add(hashMap.get(id));
        }
        return exist;
    }
}
