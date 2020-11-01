package com.alexbro.onlinebank.facade.converter.employee;

import com.alexbro.onlinebank.core.entity.Employee;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmployeeConverter extends AbstractConverter<Employee, EmployeeData> {

    @Resource
    private Populator<Employee, EmployeeData> employeePopulator;

    @Override
    public EmployeeData convert(Employee employee) {
        EmployeeData employeeData = new EmployeeData();
        employeePopulator.populate(employee, employeeData);
        return employeeData;
    }
}
