package com.alexbro.onlinebank.facade.principal;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.core.service.principal.PrincipalService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPrincipalFacadeTest {

    private static final String CODE = "p1";

    @InjectMocks
    private DefaultPrincipalFacade testedEntry;
    @Mock
    private PrincipalService principalService;
    @Mock
    private Principal principal;
    @Mock
    private PrincipalData principalData;
    @Mock
    private Converter<Principal, PrincipalData> principleConverter;

    @Test
    public void shouldFindByCode() {
        when(principalService.findByCode(CODE)).thenReturn(Optional.of(principal));
        when(principleConverter.convert(principal)).thenReturn(principalData);

        Optional<PrincipalData> result = testedEntry.findByCode(CODE);

        assertEquals(Optional.of(principalData), result);
    }
}
