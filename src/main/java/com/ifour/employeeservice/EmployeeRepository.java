package com.ifour.employeeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findAllByDeptId(Integer deptId);

    List<Employee> findAllByName(String name);

    List<Employee> findByIdIn(List<Integer> id);
}
