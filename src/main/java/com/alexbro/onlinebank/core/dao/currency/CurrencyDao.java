package com.alexbro.onlinebank.core.dao.currency;

import com.alexbro.onlinebank.core.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {

    List<Currency> findAllByUser(String userCode);

    Optional<Currency> findByCode(String code);

    void save(List<Currency> currencies);
}
