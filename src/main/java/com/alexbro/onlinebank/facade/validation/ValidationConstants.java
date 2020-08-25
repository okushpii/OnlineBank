package com.alexbro.onlinebank.facade.validation;

public interface ValidationConstants {

    interface Regexp {
        String NAME_REGEXP = "[a-zA-Z]+";
        String EMAIL_REGEXP = "[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+";
        String USERNAME_REGEXP = "^.*(?=.{5,})[a-zA-Z]+[a-zA-Z0-9]+";
        String PASSWORD_REGEXP = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$";
        String CARD_NUMBER_REGEXP = "^[0-9]{6}$";
    }

    interface Messages {
        String VALID_NAME = "{valid.name}";
        String VALID_EMAIL = "{valid.email}";
        String VALID_USERNAME = "{valid.username}";
        String VALID_USERNAME_UNIQUE = "{valid.username.unique}";
        String VALID_PASSWORD = "{valid.password}";
        String PASSWORD_NOT_MATCHES = "password.not.matches";
        String VALID_SUM = "{invalid.sum.message}";
        String VALID_CARD_NUMBER = "{valid.card.number}";
        String SUM_IS_BIGGER_THEN_LIMIT_MESSAGE = "{sum.is.bigger.then.limit.message}";
    }
}
