package com.alexbro.onlinebank.core.dao.employee;

import com.alexbro.onlinebank.core.dao.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class DefaultEmployeeDao implements EmployeeDao {

    private static final String FIND_EMPLOYEE_BY_CODE = "SELECT e FROM Employee e WHERE e.code = :code";

    @Resource
    private SessionProvider sessionProvider;

    @Override
    public Optional<Employee> findByCode(String code) {
        return sessionProvider.getSession().createQuery(FIND_EMPLOYEE_BY_CODE, Employee.class).
                setParameter("code", code).uniqueResultOptional();
    }
}
