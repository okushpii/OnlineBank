package com.alexbro.onlinebank.facade.employee;

import com.alexbro.onlinebank.core.entity.Employee;
import com.alexbro.onlinebank.core.service.employee.EmployeeService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DefaultEmployeeFacade implements EmployeeFacade{

    @Resource
    private EmployeeService employeeService;

    @Resource
    private Converter<Employee, EmployeeData> employeeConverter;

    @Override
    public Optional<EmployeeData> findByCode(String code) {
        return employeeService.findByCode(code).map(e ->  employeeConverter.convert(e));
    }
}
