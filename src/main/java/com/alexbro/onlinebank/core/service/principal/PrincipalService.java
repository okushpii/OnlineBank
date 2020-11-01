package com.alexbro.onlinebank.core.service.principal;

import com.alexbro.onlinebank.core.entity.Principal;

import java.util.Optional;

public interface PrincipalService {

    Optional<Principal> findByUsername(String username);

    Optional<Principal> findByCode(String code);
}
