package com.alexbro.onlinebank.auth.model.service;

import com.alexbro.onlinebank.core.service.encode.EncodingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthTokenService {

    @Resource
    private EncodingService encodingService;

    public String generateToken(String userName, String password) {
        String auth = userName + password;
        return encodingService.encode(auth, "SHA-1");
    }
}
