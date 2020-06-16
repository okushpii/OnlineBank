package com.alexbro.onlinebank.core.context;

import com.alexbro.onlinebank.core.strategy.ConfigurationFetchingStrategy;
import com.alexbro.onlinebank.core.strategy.SumValidationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CoreBeanContext {

    @Resource
    private ConfigurationFetchingStrategy databaseConfigurationFetchingStrategy;
    @Resource
    private ConfigurationFetchingStrategy propertiesConfigurationFetchingStrategy;
    @Resource
    private SumValidationStrategy biggerZeroSumValidationStrategy;
    @Resource
    private SumValidationStrategy lessLimitSumValidationStrategy;

    @Bean
    public List<ConfigurationFetchingStrategy> configurationFetchingStrategies() {
        List<ConfigurationFetchingStrategy> strategies = new ArrayList<>();
        strategies.add(databaseConfigurationFetchingStrategy);
        strategies.add(propertiesConfigurationFetchingStrategy);
        return strategies;
    }

    @Bean
    public List<SumValidationStrategy> sumValidationStrategies() {
        List<SumValidationStrategy> strategies = new ArrayList<>();
        strategies.add(biggerZeroSumValidationStrategy);
        strategies.add(lessLimitSumValidationStrategy);
        return strategies;
    }
}
