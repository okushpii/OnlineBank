package com.alexbro.onlinebank.facade.converter.user;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter testedInstance;

    @Mock
    private Populator<User, UserData> userPopulator;

    @Mock
    private User user;

    @Test
    public void shouldConvert(){
        UserData userData = testedInstance.convert(user);

        verify(userPopulator).populate(user, userData);
    }
}
