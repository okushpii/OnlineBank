package com.alexbro.onlinebank.webfront;

public interface WebConstants {

    interface Mapping {
        String HOME = "/";
        String LOGIN = "/login";
        String USER = "/user";
        String AUTH = "/auth";
        String LOGOUT = "/logout";
        String ACCOUNT = "/account";
        String TRANSFER = "/transfer";
        String EXCHANGE_STEP_ONE = "/exchangeStepOne";
        String EXCHANGE_STEP_TWO = "/exchangeStepTwo";
    }

    interface RequestAttributes {
        String USER = "user";
        String ACCOUNTS = "accounts";
        String CURRENCIES = "currencies";
        String AUTH_REQUEST = "authRequest";
    }

    interface SessionAttributes {
        String AUTH_DATA = "authData";
    }

    interface Pages {
        String HOME = "pages/homePage";
        String LOGIN = "pages/loginPage";
        String USER = "pages/userPage";
        String TRANSFER = "pages/transferPage";
        String EXCHANGE_STEP_ONE = "pages/exchangeStepOnePage";
        String EXCHANGE_STEP_TWO = "pages/exchangeStepTwoPage";
    }

    interface Util {
        String REDIRECT = "redirect:";
    }
}
