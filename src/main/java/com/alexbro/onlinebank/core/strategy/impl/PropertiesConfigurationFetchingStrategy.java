package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.strategy.ConfigurationFetchingStrategy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class PropertiesConfigurationFetchingStrategy implements ConfigurationFetchingStrategy {

    @Resource
    private Environment environment;

    @Override
    public Optional<String> fetch(String key) {
        return Optional.ofNullable(environment.getProperty(key));
    }
}
