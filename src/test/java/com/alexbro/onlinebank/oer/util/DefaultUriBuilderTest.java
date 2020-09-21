package com.alexbro.onlinebank.oer.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUriBuilderTest {

    private static final String OER_URL = "https://openexchangerates.org/api/latest.json";
    private static final String OER_API_KEY = "8ea75d4a72d440b3947228f569b8c525";
    private static final String RESPONSE_BODY = "https://openexchangerates.org/api/latest.json?app_id=8ea75d4a72d440b3947228f569b8c525";

    @Spy
    private DefaultUriBuilder testedEntry;

    @Test
    public void shouldBuild() throws URISyntaxException {
        URI result = testedEntry.build(OER_URL, OER_API_KEY);

        assertEquals(RESPONSE_BODY, result.toString());
    }
}
