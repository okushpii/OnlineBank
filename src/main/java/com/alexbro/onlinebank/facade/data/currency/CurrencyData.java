package com.alexbro.onlinebank.facade.data.currency;


import java.math.BigDecimal;

public class CurrencyData {

    private String code;
    private String name;
    private BigDecimal rate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
