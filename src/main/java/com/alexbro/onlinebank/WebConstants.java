package com.alexbro.onlinebank;

public interface WebConstants {

    interface Mapping{
        String HOME_PAGE_MAPPING = "/";
        String LOGIN_PAGE_MAPPING = "/login";
        String USER_PAGE_MAPPING = "/user";
        String AUTH_MAPPING = "/auth";
    }

    interface Attributes {
        String USER_ATTRIBUTE = "user";
        String AUTH_DATA_ATTRIBUTE = "authData";
        String AUTH_REQUEST_ATTRIBUTE = "authRequest";
    }

    interface Pages{
        String HOME_PAGE = "pages/homePage";
        String LOGIN_PAGE = "pages/loginPage";
        String USER_PAGE = "pages/userPage";
    }

    interface Util{
        String REDIRECT = "redirect:";
    }
}
