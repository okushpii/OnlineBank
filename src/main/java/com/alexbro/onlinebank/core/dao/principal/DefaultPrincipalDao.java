package com.alexbro.onlinebank.core.dao.principal;

import com.alexbro.onlinebank.core.dao.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.Principal;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class DefaultPrincipalDao implements PrincipalDao {

    private static final String FIND_PRINCIPLE_BY_USERNAME = "SELECT p FROM Principal p WHERE p.username = :username";
    private static final String FIND_PRINCIPLE_BY_CODE = "SELECT p FROM  Principal p WHERE p.code = :code";

    @Resource
    private SessionProvider sessionProvider;

    @Override
    public Optional<Principal> findByUsername(String username) {
        return sessionProvider.getSession().createQuery(FIND_PRINCIPLE_BY_USERNAME, Principal.class).
                setParameter("username", username).uniqueResultOptional();
    }

    @Override
    public Optional<Principal> findByCode(String code) {
        return sessionProvider.getSession().createQuery(FIND_PRINCIPLE_BY_CODE, Principal.class).
                setParameter("code", code).uniqueResultOptional();
    }
}
