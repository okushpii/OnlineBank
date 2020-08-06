package com.alexbro.onlinebank.webfront.controller.pages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationPageControllerTest {

    private static final String REGISTRATION_PAGE = "pages/registrationPage";

    @InjectMocks
    private RegistrationPageController testedEntry;

    @Mock
    private Model model;

    @Test
    public void shouldGetRegistrationPage() {
        String result = testedEntry.getRegistrationPage(model);

        assertEquals(REGISTRATION_PAGE, result);
    }
}
