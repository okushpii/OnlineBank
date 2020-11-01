package com.alexbro.onlinebank.facade.converter.employee;

import com.alexbro.onlinebank.core.entity.Employee;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeConverterTest {

    @InjectMocks
    private EmployeeConverter testedEntry;

    @Mock
    private Employee employee;

    @Mock
    private Populator<Employee, EmployeeData> employeePopulator;

    @Test
    public void shouldConvert() {
        EmployeeData result = testedEntry.convert(employee);

        verify(employeePopulator).populate(employee, result);
    }
}
