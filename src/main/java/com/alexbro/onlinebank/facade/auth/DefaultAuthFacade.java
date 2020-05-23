package com.alexbro.onlinebank.facade.auth;

import com.alexbro.onlinebank.core.exception.LoginException;
import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.core.service.authentication.AuthenticationService;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.core.service.encode.token.AuthenticationTokenService;
import com.alexbro.onlinebank.facade.data.factory.AuthDataFactory;
import com.alexbro.onlinebank.facade.data.auth.AuthData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component(value = "authFacade")
public class DefaultAuthFacade implements AuthFacade {

    private static final String ERROR_MESSAGE = "User is not found by this login and password!";

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @Resource
    private AuthenticationTokenService authenticationTokenService;

    @Resource
    private AuthDataFactory authDataFactory;

    @Resource
    private EncodePasswordService encodePasswordService;

    @Override
    public AuthData authorize(String username, String password) {
        Optional<User> user = userService.getByUsername(username);
        return user.filter(u -> isUserValid(u, password))
                .map(this::createAuthenticationData)
                .orElseThrow(() -> new LoginException(ERROR_MESSAGE));
    }

    private boolean isUserValid(User user, String password) {
        String encodedPassword = encodePasswordService.encodePassword(password);
        return authenticationService.passwordMatches(encodedPassword, user.getPassword());
    }

    private AuthData createAuthenticationData(User user) {
        String token = authenticationTokenService.generateToken(user.getUsername(),
                user.getPassword());
        return authDataFactory.create(user,
                token);
    }

    @Override
    public boolean isAuthorized(AuthData authData) {
      return Optional.ofNullable(authData)
              .flatMap(ad -> userService.getByUsername(ad.getUsername()))
              .map(u -> isTokensEqual(authData, u.getPassword()))
              .orElse(false);
    }

    private boolean isTokensEqual(AuthData authData, String password){
        String generateToken = authenticationTokenService.generateToken(authData.getUsername(), password);
        return authData.getToken().equals(generateToken);
    }

}
