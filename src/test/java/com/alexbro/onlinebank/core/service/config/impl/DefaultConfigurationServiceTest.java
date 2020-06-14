package com.alexbro.onlinebank.core.service.config.impl;

import com.alexbro.onlinebank.core.exception.ConfigurationException;
import com.alexbro.onlinebank.core.strategy.ConfigurationFetchingStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultConfigurationServiceTest {

    private static final String CONFIG_KEY = "key";
    private static final String CONFIG_VALUE = "value";

    @Spy
    private DefaultConfigurationService testedInstance;

    @Mock
    private ConfigurationFetchingStrategy firstStrategy;
    @Mock
    private ConfigurationFetchingStrategy secondStrategy;

    @Before
    public void setUp() {
        testedInstance.setConfigurationFetchingStrategies(List.of(firstStrategy, secondStrategy));
    }

    @Test
    public void shouldFindByFirstStrategy() {
        when(firstStrategy.fetch(CONFIG_KEY)).thenReturn(Optional.of(CONFIG_VALUE));

        Optional<String> result = testedInstance.find(CONFIG_KEY);

        assertEquals(Optional.of(CONFIG_VALUE), result);
        verify(secondStrategy, never()).fetch(CONFIG_KEY);
    }

    @Test
    public void shouldFindBySecondStrategy() {
        when(secondStrategy.fetch(CONFIG_KEY)).thenReturn(Optional.of(CONFIG_VALUE));

        Optional<String> result = testedInstance.find(CONFIG_KEY);

        assertEquals(Optional.of(CONFIG_VALUE), result);
        verify(firstStrategy).fetch(CONFIG_KEY);
    }

    @Test
    public void shouldGetRequiredValue(){
        when(testedInstance.find(CONFIG_KEY)).thenReturn(Optional.of(CONFIG_VALUE));

        String result = testedInstance.findRequired(CONFIG_KEY);

        assertEquals(CONFIG_VALUE, result);
    }

    @Test(expected = ConfigurationException.class)
    public void shouldGetRequiredValueWhenConfigurationValueIsAbsent(){
        String result = testedInstance.findRequired(CONFIG_KEY);

        assertEquals(CONFIG_VALUE, result);
    }
}
