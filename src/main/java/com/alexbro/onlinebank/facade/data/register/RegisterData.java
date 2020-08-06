package com.alexbro.onlinebank.facade.data.register;

import com.alexbro.onlinebank.facade.validation.ValidationConstants;
import com.alexbro.onlinebank.facade.validation.costraint.UsernameUnique;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class RegisterData {

    @Nullable
    private String code;
    @Pattern(regexp = ValidationConstants.Regexp.NAME_REGEXP, message = ValidationConstants.Messages.VALID_NAME)
    private String name;
    @Email(regexp = ValidationConstants.Regexp.EMAIL_REGEXP, message = ValidationConstants.Messages.VALID_EMAIL)
    private String email;
    @Pattern(regexp = ValidationConstants.Regexp.USERNAME_REGEXP, message = ValidationConstants.Messages.VALID_USERNAME)
    @UsernameUnique(message = ValidationConstants.Messages.VALID_USERNAME_UNIQUE)
    private String username;
    @Pattern(regexp = ValidationConstants.Regexp.PASSWORD_REGEXP, message = ValidationConstants.Messages.VALID_PASSWORD)
    private String password;

    private String passwordConfirmation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
