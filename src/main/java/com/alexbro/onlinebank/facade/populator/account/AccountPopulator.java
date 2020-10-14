package com.alexbro.onlinebank.facade.populator.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.operation.OperationData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountPopulator implements Populator<Account, AccountData> {

    @Resource
    private Converter<Currency, CurrencyData> currencyConverter;

    @Resource
    private Converter<Operation, OperationData> operationConverter;

    @Override
    public void populate(Account account, AccountData accountData) {
        accountData.setCode(account.getCode());
        accountData.setCardNumber(account.getCardNumber());
        accountData.setMoney(account.getMoney());
        accountData.setCurrency(currencyConverter.convert(account.getCurrency()));
        accountData.setOperations(operationConverter.convertAll(account.getOperations()));
    }
}
