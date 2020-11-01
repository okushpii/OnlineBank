package com.alexbro.onlinebank.facade.converter.principal;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PrincipalConverter extends AbstractConverter<Principal, PrincipalData> {

    @Resource
    private Populator<Principal, PrincipalData> principlePopulator;

    @Override
    public PrincipalData convert(Principal principal) {
        PrincipalData principalData = new PrincipalData();
        principlePopulator.populate(principal, principalData);
        return principalData;
    }
}
