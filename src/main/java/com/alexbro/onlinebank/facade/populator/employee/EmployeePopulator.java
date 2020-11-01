package com.alexbro.onlinebank.facade.populator.employee;

import com.alexbro.onlinebank.core.entity.Employee;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

@Component
public class EmployeePopulator implements Populator<Employee, EmployeeData> {

    @Override
    public void populate(Employee employee, EmployeeData employeeData) {
        employeeData.setCode(employee.getCode());
        employeeData.setName(employee.getName());
        employeeData.setEmail(employee.getEmail());
        employeeData.setPassword(employee.getPassword());
        employeeData.setRole(employee.getRole());
    }
}
