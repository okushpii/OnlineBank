package com.alexbro.onlinebank.facade.populator.employee;

import com.alexbro.onlinebank.core.entity.Employee;
import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeePopulatorTest {

    private static final String CODE = "a1";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @InjectMocks
    private EmployeePopulator testedInstance;

    @Mock
    private Employee employee;

    @Test
    public void shouldPopulate() {
        EmployeeData employeeData = new EmployeeData();
        prepareFields();

        testedInstance.populate(employee, employeeData);

        assertFields(employeeData);
    }

    private void prepareFields() {
        when(employee.getCode()).thenReturn(CODE);
        when(employee.getName()).thenReturn(NAME);
        when(employee.getEmail()).thenReturn(EMAIL);
        when(employee.getPassword()).thenReturn(PASSWORD);
        when(employee.getRole()).thenReturn(Role.EMPLOYEE);
    }

    private void assertFields(EmployeeData employeeData) {
        assertEquals(CODE, employeeData.getCode());
        assertEquals(NAME, employeeData.getName());
        assertEquals(EMAIL, employeeData.getEmail());
        assertEquals(PASSWORD, employeeData.getPassword());
        assertEquals(Role.EMPLOYEE, employeeData.getRole());
    }
}
