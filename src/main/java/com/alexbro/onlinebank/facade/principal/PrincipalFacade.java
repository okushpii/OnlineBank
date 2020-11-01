package com.alexbro.onlinebank.facade.principal;

import com.alexbro.onlinebank.facade.data.principal.PrincipalData;

import java.util.Optional;

public interface PrincipalFacade {

    Optional<PrincipalData> findByCode(String code);
}
