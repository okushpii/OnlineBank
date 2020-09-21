package com.alexbro.onlinebank.core.service.currency;

import com.alexbro.onlinebank.core.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> findAllByUser(String userCode);

    Optional<Currency> findByCode(String code);

    void save(List<Currency> currencies);
}
