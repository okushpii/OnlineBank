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
        String EXCHANGE_STEP_THREE = "/exchangeStepThree";
    }

    interface RequestAttributes {
        String USER = "user";
        String ACCOUNTS = "accounts";
        String CURRENCIES = "currencies";
        String AUTH_REQUEST = "authRequest";
        String ACCOUNTS_FROM = "accountsFrom";
        String ACCOUNTS_TO = "accountsTo";
        String CURRENCY_FROM = "currencyFrom";
        String CURRENCY_TO = "currencyTo";
        String ACCOUNT_FROM = "accountFrom";
        String ACCOUNT_TO = "accountTo";
        String SUM = "sum";
        String SUM_AFTER = "sumAfter";
        String BALANCE_FROM = "balanceFrom";
        String BALANCE_TO = "balanceTo";
        String BALANCE_AFTER_FROM = "balanceAfterFrom";
        String BALANCE_AFTER_TO = "balanceAfterTo";
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
    }

    interface Util {
        String REDIRECT = "redirect:";
    }

    interface Messages{
        String CURRENCIES_MATCHES_EXCEPTION_MESSAGE = "currencies.matches";
    }
}
