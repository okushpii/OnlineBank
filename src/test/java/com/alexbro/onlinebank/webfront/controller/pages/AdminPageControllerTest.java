package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.data.employee.EmployeeData;
import com.alexbro.onlinebank.facade.employee.EmployeeFacade;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminPageControllerTest {

    private static final String CODE = "a1";
    private static final String ADMIN_PAGE = "pages/admin/adminPage";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    @InjectMocks
    private AdminPageController testedEntry;
    @Mock
    private EmployeeFacade employeeFacade;
    @Mock
    private EmployeeData employeeData;
    @Mock
    private Model model;
    @Mock
    private HttpSession session;
    @Mock
    private AuthManager authManager;
    @Mock
    private AuthData authData;

    @Before
    public void setUp() {
        when(authManager.getAuthData(session)).thenReturn(authData);
    }

    @Test
    public void shouldGetAdminPage() {
        when(authData.getUserCode()).thenReturn(CODE);
        when(employeeFacade.findByCode(CODE)).thenReturn(Optional.of(employeeData));

        String result = testedEntry.getAdminPage(CODE, model, session);

        verify(model).addAttribute("admin", employeeData);
        assertEquals(ADMIN_PAGE, result);
    }

    @Test
    public void shouldGetLoginPage() {
        String result = testedEntry.getAdminPage(CODE, model, session);

        assertEquals(REDIRECT_LOGIN, result);
    }
}
