package com.alexbro.onlinebank.core.service.currency;

import com.alexbro.onlinebank.core.dao.currency.CurrencyDao;
import com.alexbro.onlinebank.core.entity.Currency;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultCurrencyService implements CurrencyService {

    @Resource
    private CurrencyDao currencyDao;

    @Override
    public List<Currency> findAllByUser(String userCode) {
        return currencyDao.findAllByUser(userCode);
    }
}
