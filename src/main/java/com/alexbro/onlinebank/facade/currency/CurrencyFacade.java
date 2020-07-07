package com.alexbro.onlinebank.facade.currency;

import com.alexbro.onlinebank.facade.data.currency.CurrencyData;

import java.util.List;

public interface CurrencyFacade {

    List<CurrencyData> findAllByUser(String userCode);
}
