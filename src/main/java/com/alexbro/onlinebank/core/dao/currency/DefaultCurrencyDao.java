package com.alexbro.onlinebank.core.dao.currency;

import com.alexbro.onlinebank.core.dao.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.Currency;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DefaultCurrencyDao implements CurrencyDao {

    private static final String FIND_ALL_CURRENCY_BY_USER_CODE_QUERY = "SELECT DISTINCT c FROM Currency c INNER JOIN c.accounts a " +
            "INNER JOIN a.user u WHERE u.code =:userCode";

    @Resource
    private SessionProvider sessionProvider;

    @Override
    public List<Currency> findAllByUser(String userCode) {
        return sessionProvider.getSession().createQuery(FIND_ALL_CURRENCY_BY_USER_CODE_QUERY, Currency.class).
                setParameter("userCode", userCode).list();
    }
}
