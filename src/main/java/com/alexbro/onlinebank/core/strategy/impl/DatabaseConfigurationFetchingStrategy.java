package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.dao.config.ConfigurationDao;
import com.alexbro.onlinebank.core.entity.config.Configuration;
import com.alexbro.onlinebank.core.strategy.ConfigurationFetchingStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DatabaseConfigurationFetchingStrategy implements ConfigurationFetchingStrategy {

    @Resource
    private ConfigurationDao configurationDao;

    @Override
    public Optional<String> fetch(String key) {
        return configurationDao.find(key)
                .map(Configuration::getValue);
    }
}
