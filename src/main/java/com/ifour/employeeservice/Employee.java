package com.ifour.employeeservice;

//import javax.persistence.*;

//@Entity
//@Table
public class Employee {
    //@Id
    private Integer id;
    private String name;
    private Integer salary;
    private Integer deptId;
    private String deptName;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer salary,Integer deptId,String deptName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Employee(String name, Integer salary, Integer deptId) {
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Employee(Employee allByDeptId) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public  Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", deptId=" + deptId +
                ", deptName=" + deptName +
                '}';
    }
}



