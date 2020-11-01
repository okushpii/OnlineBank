package com.alexbro.onlinebank.facade.populator.principal;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

@Component
public class PrincipalPopulator implements Populator<Principal, PrincipalData> {

    @Override
    public void populate(Principal principal, PrincipalData principalData) {
        principalData.setCode(principal.getCode());
        principalData.setName(principal.getName());
        principalData.setEmail(principal.getEmail());
        principalData.setPassword(principal.getPassword());
        principalData.setRole(principal.getRole());
    }
}
