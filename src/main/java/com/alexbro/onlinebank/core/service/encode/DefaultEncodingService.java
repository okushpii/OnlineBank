package com.alexbro.onlinebank.core.service.encode;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class DefaultEncodingService implements EncodingService {

    @Override
    public String encode(String encodedElement, String typeOfEncode) {
        try {
            MessageDigest md = MessageDigest.getInstance(typeOfEncode);
            md.update(encodedElement.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
 }

