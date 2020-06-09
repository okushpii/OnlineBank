package com.alexbro.onlinebank.webfront;

public interface WebConstants {

    interface Mapping {
        String HOME = "/";
        String LOGIN = "/login";
        String USER = "/user";
        String AUTH = "/auth";
        String LOGOUT = "/logout";
        String ACCOUNT = "/account";
    }

    interface RequestAttributes {
        String USER = "user";
        String AUTH_REQUEST = "authRequest";
    }

    interface SessionAttributes {
        String AUTH_DATA = "authData";
    }

    interface Pages {
        String HOME = "pages/homePage";
        String LOGIN = "pages/loginPage";
        String USER = "pages/userPage";
    }

    interface Util {
        String REDIRECT = "redirect:";
    }
}
