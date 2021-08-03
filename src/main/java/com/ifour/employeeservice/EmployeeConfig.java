package com.ifour.employeeservice;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.List;

@Configurable
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository)
    {
        return args -> {
            Employee yash = new Employee("Yash",15000,101);
            Employee hemanshu = new Employee("Himanshu",15000,102);
            Employee ishan = new Employee("Ishaan",15000,103);
            repository.saveAll(List.of(yash,hemanshu,ishan));
        };

    }
}
