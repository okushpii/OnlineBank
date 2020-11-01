package com.alexbro.onlinebank.core.service.employee;

import com.alexbro.onlinebank.core.dao.employee.EmployeeDao;
import com.alexbro.onlinebank.core.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public Optional<Employee> findByCode(String code) {
        return employeeDao.findByCode(code);
    }
}
