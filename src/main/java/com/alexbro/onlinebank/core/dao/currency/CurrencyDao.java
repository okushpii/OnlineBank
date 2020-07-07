package com.alexbro.onlinebank.core.dao.currency;

import com.alexbro.onlinebank.core.entity.Currency;

import java.util.List;

public interface CurrencyDao {

    List<Currency> findAllByUser(String userCode);
}
