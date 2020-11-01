package com.alexbro.onlinebank.core.dao.principal;

import com.alexbro.onlinebank.core.entity.Principal;

import java.util.Optional;

public interface PrincipalDao {

    Optional<Principal> findByUsername(String username);

    Optional<Principal> findByCode(String code);
}
