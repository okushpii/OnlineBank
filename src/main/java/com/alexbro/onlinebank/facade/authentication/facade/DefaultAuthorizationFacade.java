package com.alexbro.onlinebank.facade.authentication.facade;

import com.alexbro.onlinebank.core.exception.LoginException;
import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.core.service.authentication.AuthenticationService;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.core.service.encode.token.AuthenticationTokenService;
import com.alexbro.onlinebank.facade.authentication.data.factory.AuthenticationDataFactory;
import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DefaultAuthorizationFacade implements AuthorizationFacade {

    private static final String ERROR_MESSAGE = "User is not found by this login and password!";

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @Resource
    private AuthenticationTokenService authenticationTokenService;

    @Resource
    private AuthenticationDataFactory authenticationDataFactory;

    @Resource
    private EncodePasswordService encodePasswordService;

    @Override
    public AuthenticationData authorize(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.filter(u -> isUserValid(u, password))
                .map(this::createAuthenticationData)
                .orElseThrow(() -> new LoginException(ERROR_MESSAGE));
    }

    private boolean isUserValid(User user, String password) {
        String encodedPassword = encodePasswordService.encodePassword(password);
        return authenticationService.passwordMatches(encodedPassword, user.getPassword());
    }

    private AuthenticationData createAuthenticationData(User user) {
        String token = authenticationTokenService.generateToken(user.getUsername(),
                user.getPassword());
        return authenticationDataFactory.createAuthenticationData(user,
                token);
    }

    @Override
    public boolean isAuthorized(AuthenticationData authenticationData) {
      return Optional.ofNullable(authenticationData)
              .flatMap(ad -> userService.getUserByUsername(ad.getUsername()))
              .map(u -> isTokensEqual(authenticationData, u.getPassword()))
              .orElse(false);
    }

    private boolean isTokensEqual(AuthenticationData authenticationData, String password){
        String generateToken = authenticationTokenService.generateToken(authenticationData.getUsername(), password);
        return authenticationData.getToken().equals(generateToken);
    }

}
