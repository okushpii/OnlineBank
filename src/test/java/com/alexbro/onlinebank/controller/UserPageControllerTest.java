package com.alexbro.onlinebank.controller;

import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String USER_PAGE = "pages/userPage";

    @InjectMocks
    private UserPageController testedEntry;

    @Mock
    private UserFacade userFacade;

    @Mock
    private Model model;

    @Test
    public void shouldGetUserPage(){
        UserData userData = new UserData();
        when(userFacade.getByCode(USER_CODE)).thenReturn(Optional.of(userData));

        String result = testedEntry.getUserPage(USER_CODE, model);

        assertEquals(USER_PAGE, result);
    }
}
