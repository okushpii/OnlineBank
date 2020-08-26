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
        String EXCHANGE = "/exchange";
        String EXCHANGE_STEP_ONE = "/exchangeStepOne";
        String EXCHANGE_STEP_TWO = "/exchangeStepTwo";
        String EXCHANGE_STEP_THREE = "/exchangeStepThree";
        String REGISTRATION = "/registration";
    }

    interface ModelAttributes {
        String USER = "user";
        String ACCOUNTS = "accounts";
        String CURRENCIES = "currencies";
        String AUTH_REQUEST = "authRequest";
        String ACCOUNTS_FROM = "accountsFrom";
        String ACCOUNTS_TO = "accountsTo";
        String CURRENCY_FROM = "currencyFrom";
        String CURRENCY_TO = "currencyTo";
        String EXCHANGE = "exchange";
        String REGISTER = "registerData";
        String TRANSFER_REQUEST_DATA = "transferRequestData";
        String EXCHANGE_REQUEST_DATA = "exchangeRequestData";
        String TRANSFER_BINDING_RESULT = "org.springframework.validation.BindingResult.transferRequestData";
        String EXCHANGE_BINDING_RESULT = "org.springframework.validation.BindingResult.exchangeRequestData";
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
        String EXCHANGE_STEP_THREE = "pages/exchangeStepThreePage";
        String REGISTRATION = "pages/registrationPage";
    }

    interface Util {
        String REDIRECT = "redirect:";
    }

    interface Messages {
        String CURRENCIES_MATCHES_EXCEPTION_MESSAGE = "currencies.matches";
        String AUTH_NOT_FOUND_EXCEPTION = "Auth data is not found";
    }
}
