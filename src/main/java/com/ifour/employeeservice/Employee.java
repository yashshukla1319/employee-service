package com.ifour.employeeservice;

import javax.persistence.*;

@Entity
@Table
public class Employee {
    @Id
    private int id;
    private String name;
    private int salary;
    private int deptId;
    private String deptName;

    public Employee() {
    }

    public Employee(int id, String name, int salary,int deptId,String deptName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Employee(String name, int salary, int deptId) {
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Employee(Employee allByDeptId) {
    }

    public int getId() {
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public  int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
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



