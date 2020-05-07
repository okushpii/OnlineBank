package com.alexbro.onlinebank.core.service.authentication;

public interface AuthenticationService {

    boolean passwordMatches (String userPassword, String databasePassword);
}
