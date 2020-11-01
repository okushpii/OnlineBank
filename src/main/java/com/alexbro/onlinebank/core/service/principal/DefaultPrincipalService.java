package com.alexbro.onlinebank.core.service.principal;

import com.alexbro.onlinebank.core.dao.principal.PrincipalDao;
import com.alexbro.onlinebank.core.entity.Principal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultPrincipalService implements PrincipalService {

    @Resource
    private PrincipalDao principalDao;

    @Override
    public Optional<Principal> findByUsername(String username) {
        return principalDao.findByUsername(username);
    }

    @Override
    public Optional<Principal> findByCode(String code) {
        return principalDao.findByCode(code);
    }
}
