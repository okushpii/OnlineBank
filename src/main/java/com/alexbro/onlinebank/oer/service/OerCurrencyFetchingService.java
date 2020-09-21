package com.alexbro.onlinebank.oer.service;

import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.core.service.currency.CurrencyFetchingService;
import com.alexbro.onlinebank.oer.OerConstants;
import com.alexbro.onlinebank.oer.exception.OerException;
import com.alexbro.onlinebank.oer.util.UriBuilder;
import com.alexbro.onlinebank.webfront.controller.pages.ExchangeStepTwoPageController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

@Service
public class OerCurrencyFetchingService implements CurrencyFetchingService {

    @Resource
    private UriBuilder uriBuilder;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private TypeReference<Map<String, Object>> typeReference;

    @Resource
    private ConfigurationService configurationService;

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @Override
    public Map<String, Object> fetch() {
        URI uri = uriBuilder.build(configurationService.findRequired("oer.url"), configurationService.findRequired("oer.api.key"));
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        try {
            return objectMapper.readValue(response.getBody(), typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        LOG.info(OerConstants.CURRENCIES_FETCHING_FAILED_MESSAGE);
        throw new OerException(OerConstants.CURRENCIES_FETCHING_FAILED_MESSAGE);
    }
}
