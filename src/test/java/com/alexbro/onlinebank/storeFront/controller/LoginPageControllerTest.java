package com.alexbro.onlinebank.storeFront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.webfront.controller.pages.LoginPageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageControllerTest {

    private static final String LOGIN_PAGE = "pages/common/loginPage";

    @InjectMocks
    private LoginPageController testedEntry;

    @Mock
    private Model model;

    @Test
    public void shouldReturnLoginPage(){
        String result = testedEntry.getLoginPage(model);

        verify(model).addAttribute(anyString(), any(AuthRequest.class));
        assertEquals(LOGIN_PAGE, result);
    }
}
