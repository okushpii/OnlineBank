package com.alexbro.onlinebank.core.service.encode.token;

import com.alexbro.onlinebank.core.service.encode.EncodingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthenticationTokenService {

    @Resource
    private EncodingService encodingService;

    //TODO Add token expiration
    public String generateToken(String userName, String password) {
        String auth = userName + password;
        return encodingService.encode(auth, "SHA-1");
    }
}
