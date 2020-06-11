package com.alexbro.onlinebank.core.service.i18service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

@Service
public class Default18Service implements I18Service {

    @Resource
    private MessageSource messageSource;

    @Override
    public String getLocalizedValue(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, new Object[0], locale);
    }
}
