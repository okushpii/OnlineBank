package com.alexbro.onlinebank.facade.exception;

public class AccountsOperationException extends RuntimeException {

    public AccountsOperationException(String message) {
        super(message);
    }
}
