package com.alexbro.onlinebank.core.service.encode.password;

import com.alexbro.onlinebank.core.service.encode.EncodingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EncodePasswordService {

    @Resource
    private EncodingService encodingService;

    public String encodePassword(String password) {
        return encodingService.encode(password, "MD5");
    }
}
