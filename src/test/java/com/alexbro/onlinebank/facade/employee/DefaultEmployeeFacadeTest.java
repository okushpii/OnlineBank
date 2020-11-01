package com.alexbro.onlinebank.facade.employee;

import com.alexbro.onlinebank.core.entity.Employee;
import com.alexbro.onlinebank.core.service.employee.EmployeeService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEmployeeFacadeTest {

    private static final String CODE = "a1";

    @InjectMocks
    private DefaultEmployeeFacade testedEntry;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private Employee employee;
    @Mock
    private EmployeeData employeeData;
    @Mock
    private Converter<Employee, EmployeeData> employeeConverter;

    @Test
    public void shouldFindByCode() {
        when(employeeService.findByCode(CODE)).thenReturn(Optional.of(employee));
        when(employeeConverter.convert(employee)).thenReturn(employeeData);

        Optional<EmployeeData> result = testedEntry.findByCode(CODE);

        assertEquals(Optional.of(employeeData), result);
    }
}
