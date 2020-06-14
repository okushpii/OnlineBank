package com.alexbro.onlinebank.core.service.config.impl;

import com.alexbro.onlinebank.core.exception.ConfigurationException;
import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.core.strategy.ConfigurationFetchingStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultConfigurationService implements ConfigurationService {

    @Resource
    private List<ConfigurationFetchingStrategy> configurationFetchingStrategies;

    @Override
    public Optional<String> find(String key) {
        return configurationFetchingStrategies.stream()
                .flatMap(st -> st.fetch(key).stream())
                .findFirst();
    }

    @Override
    public String findRequired(String key) {
        return find(key).orElseThrow(ConfigurationException::new);
    }

    public void setConfigurationFetchingStrategies(List<ConfigurationFetchingStrategy>
                                                           configurationFetchingStrategies) {
        this.configurationFetchingStrategies = configurationFetchingStrategies;
    }
}
