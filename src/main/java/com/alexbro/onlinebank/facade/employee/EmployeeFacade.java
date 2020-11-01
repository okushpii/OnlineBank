package com.alexbro.onlinebank.facade.employee;

import com.alexbro.onlinebank.facade.data.employee.EmployeeData;

import java.util.Optional;

public interface EmployeeFacade {

    Optional<EmployeeData> findByCode(String code);
}
