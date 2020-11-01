package com.alexbro.onlinebank.auth.facade;

import com.alexbro.onlinebank.auth.AuthConstants;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.factory.AuthDataFactory;
import com.alexbro.onlinebank.auth.model.service.AuthService;
import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.principal.PrincipalService;
import com.alexbro.onlinebank.auth.model.service.AuthTokenService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component(value = AuthConstants.AUTH_FACADE)
public class DefaultAuthFacade implements AuthFacade {

    @Resource
    private PrincipalService principalService;

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
        Optional<Principal> principle = principalService.findByUsername(username);
        return principle.filter(p -> isUserValid(p, password))
                .map(this::createAuthenticationData)
                .orElseThrow(() -> new AuthException(AuthConstants.ERROR_MESSAGE));
    }

    private boolean isUserValid(Principal principal, String password) {
        String encodedPassword = encodePasswordService.encodePassword(password);
        return authService.passwordMatches(encodedPassword, principal.getPassword());
    }

    private AuthData createAuthenticationData(Principal principal) {
        String token = authTokenService.generateToken(principal.getUsername(),
                principal.getPassword());
        return authDataFactory.create(principal,
                token);
    }

    @Override
    public boolean isAuthorized(AuthData authData) {
      return Optional.ofNullable(authData)
              .flatMap(ad -> principalService.findByUsername(ad.getUsername()))
              .map(p -> isTokensEqual(authData, p.getPassword()))
              .orElse(false);
    }

    private boolean isTokensEqual(AuthData authData, String password){
        String generateToken = authTokenService.generateToken(authData.getUsername(), password);
        return authData.getToken().equals(generateToken);
    }

}
