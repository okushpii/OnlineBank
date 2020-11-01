package com.alexbro.onlinebank.core.service.employee;

import com.alexbro.onlinebank.core.entity.Employee;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findByCode(String code);
}
