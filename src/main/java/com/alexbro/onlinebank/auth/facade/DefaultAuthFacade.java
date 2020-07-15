package com.alexbro.onlinebank.auth.facade;

import com.alexbro.onlinebank.auth.AuthConstants;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.factory.AuthDataFactory;
import com.alexbro.onlinebank.auth.model.service.AuthService;
import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.auth.model.service.AuthTokenService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component(value = AuthConstants.AUTH_FACADE)
public class DefaultAuthFacade implements AuthFacade {

    @Resource
    private UserService userService;

    @Resource
    private AuthService authService;

    @Resource
    private AuthTokenService authTokenService;

    @Resource
    private AuthDataFactory authDataFactory;

    @Resource
    private EncodePasswordService encodePasswordService;

    @Override
    public AuthData authorize(String username, String password) {
        Optional<User> user = userService.findByUsername(username);
        return user.filter(u -> isUserValid(u, password))
                .map(this::createAuthenticationData)
                .orElseThrow(() -> new AuthException(AuthConstants.ERROR_MESSAGE));
    }

    private boolean isUserValid(User user, String password) {
        String encodedPassword = encodePasswordService.encodePassword(password);
        return authService.passwordMatches(encodedPassword, user.getPassword());
    }

    private AuthData createAuthenticationData(User user) {
        String token = authTokenService.generateToken(user.getUsername(),
                user.getPassword());
        return authDataFactory.create(user,
                token);
    }

    @Override
    public boolean isAuthorized(AuthData authData) {
      return Optional.ofNullable(authData)
              .flatMap(ad -> userService.findByUsername(ad.getUsername()))
              .map(u -> isTokensEqual(authData, u.getPassword()))
              .orElse(false);
    }

    private boolean isTokensEqual(AuthData authData, String password){
        String generateToken = authTokenService.generateToken(authData.getUsername(), password);
        return authData.getToken().equals(generateToken);
    }

}
