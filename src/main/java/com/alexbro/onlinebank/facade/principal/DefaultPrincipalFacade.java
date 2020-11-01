package com.alexbro.onlinebank.facade.principal;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.core.service.principal.PrincipalService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DefaultPrincipalFacade implements PrincipalFacade {

    @Resource
    private PrincipalService principalService;

    @Resource
    private Converter<Principal, PrincipalData> principleConverter;

    @Override
    public Optional<PrincipalData> findByCode(String code) {
        return principalService.findByCode(code).map(p -> principleConverter.convert(p));
    }
}
