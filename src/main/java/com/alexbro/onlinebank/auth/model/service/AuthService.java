package com.alexbro.onlinebank.auth.model.service;

public interface AuthService {

    boolean passwordMatches (String userPassword, String databasePassword);
}
