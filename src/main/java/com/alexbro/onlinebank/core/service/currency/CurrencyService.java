package com.alexbro.onlinebank.core.service.currency;

import com.alexbro.onlinebank.core.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAllByUser(String userCode);
}
