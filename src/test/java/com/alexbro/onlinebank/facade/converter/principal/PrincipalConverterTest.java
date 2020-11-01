package com.alexbro.onlinebank.facade.converter.principal;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrincipalConverterTest {

    @InjectMocks
    private PrincipalConverter testedEntry;

    @Mock
    private Principal principal;

    @Mock
    private Populator<Principal, PrincipalData> principlePopulator;

    @Test
    public void shouldConvert() {
        PrincipalData principalData = testedEntry.convert(principal);

        verify(principlePopulator).populate(principal, principalData);
    }
}
