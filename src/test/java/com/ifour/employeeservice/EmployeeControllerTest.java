package com.ifour.employeeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    void getEmployee() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAllEmployees?no=1&size=1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetEmployee() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/getEmployeeById/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addNewEmployee() throws Exception{
        String uri = "/employee/addNewEmployee";
        Employee employee = new Employee();
        employee.setName("Yash");
        employee.setDeptId(101);
        employee.setId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(employee);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteEmployee() throws Exception{
        String uri = "/employee/deleteEmployeeById/1";
        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateEmployee() throws Exception{
        String uri = "/employee/updateExistingEmployeeById/1?name=Yash&dept=Temp";
        Employee employee = new Employee();
        employee.setName("sarthak");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(employee);
        this.mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findAllByDeptId() throws Exception{
        String uri = "/employee/getEmployeeByDeptId/101";
        this.mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getEmployeeIn() throws Exception {
        String uri = "/employee/getEmployeeByListId?id=101,102";
        this.mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getEmployeeByName() throws Exception {
        String uri = "/employee/getEmployeeByName?name=yash";
        this.mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}