package com.alexbro.onlinebank.facade.converter.register;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserReverseConverterTest {

    @InjectMocks
    private UserReverseConverter testedInstance;

    @Mock
    private Populator<RegisterData, User> userReversePopulator;

    @Mock
    private RegisterData registerData;

    @Test
    public void shouldConvert() {
        User result = testedInstance.convert(registerData);

        verify(userReversePopulator).populate(registerData, result);
    }
}
