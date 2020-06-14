package com.alexbro.onlinebank.core.strategy.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesConfigurationFetchingStrategyTest {

    private static final String CONFIG_KEY = "key";
    private static final String CONFIG_VALUE = "value";

    @InjectMocks
    private PropertiesConfigurationFetchingStrategy testedEntry;

    @Mock
    private Environment environment;

    @Test
    public void shouldFetchConfiguration() {
        when(environment.getProperty(CONFIG_KEY)).thenReturn(CONFIG_VALUE);

        Optional<String> result = testedEntry.fetch(CONFIG_KEY);

        assertEquals(Optional.of(CONFIG_VALUE), result);
    }
}
