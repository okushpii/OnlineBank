package com.alexbro.onlinebank.core.dao.employee;

import com.alexbro.onlinebank.core.entity.Employee;

import java.util.Optional;

public interface EmployeeDao {

    Optional<Employee> findByCode(String code);
}
