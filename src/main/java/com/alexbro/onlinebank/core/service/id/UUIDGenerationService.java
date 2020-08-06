package com.alexbro.onlinebank.core.service.id;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDGenerationService implements IdGenerationService {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
