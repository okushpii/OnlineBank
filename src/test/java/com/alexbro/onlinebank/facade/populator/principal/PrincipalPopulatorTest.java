package com.alexbro.onlinebank.facade.populator.principal;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrincipalPopulatorTest {

    private static final String CODE = "p1";
    private static final String NAME = "alex";
    private static final String EMAIL = "email@gmail.com";
    private static final String PASSWORD = "Password123";

    @InjectMocks
    private PrincipalPopulator testedInstance;

    @Mock
    private Principal principal;

    @Test
    public void shouldPopulate() {
        PrincipalData principalData = new PrincipalData();
        prepareFields();

        testedInstance.populate(principal, principalData);

        assertFields(principalData);
    }

    private void prepareFields() {
        when(principal.getCode()).thenReturn(CODE);
        when(principal.getName()).thenReturn(NAME);
        when(principal.getEmail()).thenReturn(EMAIL);
        when(principal.getPassword()).thenReturn(PASSWORD);
        when(principal.getRole()).thenReturn(Role.EMPLOYEE);
    }

    private void assertFields(PrincipalData principalData) {
        assertEquals(CODE, principalData.getCode());
        assertEquals(NAME, principalData.getName());
        assertEquals(EMAIL, principalData.getEmail());
        assertEquals(PASSWORD, principalData.getPassword());
        assertEquals(Role.EMPLOYEE, principalData.getRole());
    }
}
