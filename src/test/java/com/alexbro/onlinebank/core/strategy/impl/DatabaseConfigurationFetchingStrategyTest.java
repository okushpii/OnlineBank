package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.dao.config.ConfigurationDao;
import com.alexbro.onlinebank.core.entity.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseConfigurationFetchingStrategyTest {

    private static final String CONFIG_KEY = "key";
    private static final String CONFIG_VALUE = "value";

    @InjectMocks
    private DatabaseConfigurationFetchingStrategy testedEntry;

    @Mock
    private ConfigurationDao configurationDao;

    @Mock
    private Configuration configuration;

    @Test
    public void shouldFetchConfiguration(){
        when(configurationDao.find(CONFIG_KEY)).thenReturn(Optional.of(configuration));
        when(configuration.getValue()).thenReturn(CONFIG_VALUE);

        Optional<String> result = testedEntry.fetch(CONFIG_KEY);

        assertEquals(Optional.of(CONFIG_VALUE), result);
    }
}
