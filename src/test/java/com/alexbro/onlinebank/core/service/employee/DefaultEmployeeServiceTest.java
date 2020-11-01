package com.alexbro.onlinebank.core.service.employee;

import com.alexbro.onlinebank.core.dao.employee.EmployeeDao;
import com.alexbro.onlinebank.core.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEmployeeServiceTest {

    private static final String CODE = "a1";

    @InjectMocks
    private DefaultEmployeeService testedEntry;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private Employee employee;

    @Test
    public void shouldFindByCode() {
        when(employeeDao.findByCode(CODE)).thenReturn(Optional.of(employee));

        Optional<Employee> result = testedEntry.findByCode(CODE);

        assertEquals(Optional.of(employee), result);
    }
}
