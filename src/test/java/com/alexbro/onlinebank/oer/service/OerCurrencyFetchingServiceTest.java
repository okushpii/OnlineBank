package com.alexbro.onlinebank.oer.service;

import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.oer.exception.OerException;
import com.alexbro.onlinebank.oer.util.UriBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OerCurrencyFetchingServiceTest {

    private static final String OER_URL = "https://openexchangerates.org/api/latest.json";
    private static final String OER_API_KEY = "8ea75d4a72d440b3947228f569b8c525";
    private static final String RESPONSE_BODY = "{\"rates\": { \"USD\" : 0.1}}";

    @InjectMocks
    private OerCurrencyFetchingService testedEntry;

    @Mock
    private UriBuilder uriBuilder;
    @Mock
    private ConfigurationService configurationService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<String> response;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private TypeReference<Map<String, Object>> typeReference;

    private URI uri;

    private Map<String, Object> currencyMap;

    @Before
    public void setUp() throws URISyntaxException, JsonProcessingException {
        currencyMap = getCurrencies();
        uri = new URIBuilder().build();
        when(configurationService.findRequired("oer.url")).thenReturn(OER_URL);
        when(configurationService.findRequired("oer.api.key")).thenReturn(OER_API_KEY);
        when(uriBuilder.build(OER_URL, OER_API_KEY)).thenReturn(uri);
        when(restTemplate.getForEntity(uri, String.class)).thenReturn(response);
        when(response.getBody()).thenReturn(RESPONSE_BODY);
        when(objectMapper.readValue(RESPONSE_BODY, typeReference)).thenReturn(currencyMap);
    }

    @Test
    public void shouldFetch() {
        Map<String, Object> result = testedEntry.fetch();

        assertEquals(currencyMap, result);
    }

    public Map<String, Object> getCurrencies() {
        Map<String, Object> currencyMap = new HashMap<>();
        HashMap<Object, Object> rates = new HashMap<>();
        rates.put("USD", 0.1);
        currencyMap.put("rates", rates);
        return currencyMap;
    }

    @Test(expected = OerException.class)
    public void shouldFetchWhenHasError() throws JsonProcessingException {
        when(objectMapper.readValue(RESPONSE_BODY, typeReference)).thenThrow(JsonProcessingException.class);

        testedEntry.fetch();
    }
}
