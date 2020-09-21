package com.alexbro.onlinebank.core.service.currency;

import com.alexbro.onlinebank.core.dao.currency.CurrencyDao;
import com.alexbro.onlinebank.core.entity.Currency;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCurrencyService implements CurrencyService {

    @Resource
    private CurrencyDao currencyDao;

    @Override
    public List<Currency> findAllByUser(String userCode) {
        return currencyDao.findAllByUser(userCode);
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        return currencyDao.findByCode(code);
    }

    @Override
    public void save(List<Currency> currencies) {
        currencyDao.save(currencies);
    }
}
