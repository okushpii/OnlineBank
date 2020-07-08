package com.alexbro.onlinebank.core.dao.currency;

import com.alexbro.onlinebank.core.dao.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.Currency;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultCurrencyDao implements CurrencyDao {

    private static final String FIND_ALL_CURRENCY_BY_USER_CODE_QUERY = "SELECT DISTINCT c FROM Currency c INNER JOIN c.accounts a " +
            "INNER JOIN a.user u WHERE u.code =:userCode";
    private static final String GET_BY_CODE_QUERY = "SELECT c FROM Currency c WHERE c.code =:code";

    @Resource
    private SessionProvider sessionProvider;

    @Override
    public List<Currency> findAllByUser(String userCode) {
        return sessionProvider.getSession().createQuery(FIND_ALL_CURRENCY_BY_USER_CODE_QUERY, Currency.class).
                setParameter("userCode", userCode).list();
    }

    @Override
    public Optional<Currency> getByCode(String code) {
        return sessionProvider.getSession().createQuery(GET_BY_CODE_QUERY, Currency.class).
                setParameter("code", code).uniqueResultOptional();
    }
}
