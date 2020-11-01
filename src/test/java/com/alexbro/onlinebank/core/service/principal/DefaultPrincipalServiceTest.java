package com.alexbro.onlinebank.core.service.principal;

import com.alexbro.onlinebank.core.dao.principal.PrincipalDao;
import com.alexbro.onlinebank.core.entity.Principal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPrincipalServiceTest {

    private static final String CODE = "p1";
    private static final String USERNAME = "alex123";

    @InjectMocks
    private DefaultPrincipalService testedEntry;

    @Mock
    private PrincipalDao principalDao;

    @Mock
    private Principal principal;

    @Test
    public void shouldFindByCode() {
        when(principalDao.findByCode(CODE)).thenReturn(Optional.of(principal));

        Optional<Principal> result = testedEntry.findByCode(CODE);

        assertEquals(Optional.of(principal), result);
    }

    @Test
    public void shouldFindByUsername() {
        when(principalDao.findByUsername(USERNAME)).thenReturn(Optional.of(principal));

        Optional<Principal> result = testedEntry.findByUsername(USERNAME);

        assertEquals(Optional.of(principal), result);
    }
}
