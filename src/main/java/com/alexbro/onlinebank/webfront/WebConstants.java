package com.alexbro.onlinebank.webfront;

public interface WebConstants {

    interface Mapping {
        String HOME = "/";
        String LOGIN = "/login";
        String USER = "/user";
        String ADMIN = "/admin";
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
        String ADMIN = "admin";
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
        String HOME = "pages/common/homePage";
        String LOGIN = "pages/common/loginPage";
        String USER = "pages/user/userPage";
        String ADMIN = "pages/admin/adminPage";
        String TRANSFER = "pages/user/transferPage";
        String EXCHANGE_STEP_ONE = "pages/user/exchangeStepOnePage";
        String EXCHANGE_STEP_TWO = "pages/user/exchangeStepTwoPage";
        String EXCHANGE_STEP_THREE = "pages/user/exchangeStepThreePage";
        String REGISTRATION = "pages/common/registrationPage";
    }

    interface Util {
        String REDIRECT = "redirect:";
    }

    interface Messages {
        String CURRENCIES_MATCHES_EXCEPTION_MESSAGE = "currencies.matches";
        String AUTH_NOT_FOUND_EXCEPTION = "Auth data is not found";
        String ACCOUNT_IS_NOT_FOUND_MESSAGE = "account.is.not.found.message";
        String CURRENCY_IS_NOT_FOUND_MESSAGE = "currency.is.not.found.message";
    }
}
